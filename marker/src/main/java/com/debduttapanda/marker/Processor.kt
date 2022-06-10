package com.debduttapanda.marker

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeSpec
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement

@SupportedSourceVersion(SourceVersion.RELEASE_8) // 1
class Processor : AbstractProcessor() { // 2

 override fun getSupportedAnnotationTypes() = 
     mutableSetOf(Mark::class.java.canonicalName) // 3

 override fun process(annotations: MutableSet<out TypeElement>?,
     roundEnv: RoundEnvironment): Boolean { // 4
     val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]
         ?: return false
     val map = mutableMapOf<String,MutableList<String>>()
     roundEnv.getElementsAnnotatedWith(Mark::class.java) // 1
         .forEach { // 2
             val packageName = processingEnv.elementUtils.getPackageOf(it).toString()
             val name = it.simpleName.toString()

             if(map[packageName]==null){
                 map[packageName] = mutableListOf()
             }
             map[packageName]?.add(name)
         }
     map.keys.forEach {
         FileSpec.builder(it, "Markers")
             .addType(
                 TypeSpec.enumBuilder("Markers")
                     .apply {
                         map[it]?.forEach {
                             addEnumConstant(it.uppercase())
                         }
                     }
                     .build()
             )
             .build()
             .writeTo(File(kaptKotlinGeneratedDir))
     }
       // TODO
       return true // 5
     }

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }
}
package com.debduttapanda.marker

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.CLASS,
    AnnotationTarget.FILE,
)
@Retention(AnnotationRetention.SOURCE)
annotation class Mark
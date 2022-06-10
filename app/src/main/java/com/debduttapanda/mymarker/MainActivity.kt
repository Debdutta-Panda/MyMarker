package com.debduttapanda.mymarker

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.debduttapanda.marker.Mark
import com.debduttapanda.mymarker.ui.theme.MyMarkerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMarkerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val context = LocalContext.current
                    MainContent(context)
                }
            }
        }
    }
}

@Mark
@Composable
private fun MainContent(context: Context) {
    Box(
        contentAlignment = Alignment.Center
    ){
        Button(onClick = {
            Toast.makeText(context, "Hi", Toast.LENGTH_SHORT).show()
        }) {
            Text("Say Hi")
        }
    }
}
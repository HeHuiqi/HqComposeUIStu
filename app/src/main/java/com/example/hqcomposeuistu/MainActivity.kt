package com.example.hqcomposeuistu

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hqcomposeuistu.language.base.HqUser
import com.example.hqcomposeuistu.ui.theme.HqComposeUIStuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HqComposeUIStuTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    //只能放在最外层，获取 Context
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hello $name!", modifier = modifier
        )
        Button(
            onClick = {
                Log.i("hhq", "Greeting: ${HqUser.allSexInfo()}")
                val user = HqUser(name = "qiqi", age = 10, sex = HqUser.MEN)
                user.test()
                val info = user.study()
                Log.i("hhq", "Greeting: $info")

                Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show()
            }, modifier = Modifier
                .padding(top = 10.dp)
                .height(60.dp)
        ) {
            Text(text = "Click me")
        }

    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        HqComposeUIStuTheme {
            Greeting("Android")
        }
    }

}
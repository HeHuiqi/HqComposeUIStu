package com.example.hqcomposeuistu.codelab2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent


class HqBaseLayoutActivity : ComponentActivity() {

    companion object {
        fun actionStart(context: Context) {
               val intent = Intent(context, HqBaseLayoutActivity::class.java)
               context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MySootheApp()
        }
    }
}

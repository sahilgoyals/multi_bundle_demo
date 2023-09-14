package com.example.nativernintegration

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nativernintegration.ui.theme.NativeRNIntegrationTheme
import com.facebook.react.ReactInstanceManager




class PreLoginScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NativeRNIntegrationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RenderScreenContent()
                }
            }
        }
    }
}

@Composable
fun RenderHeader() {
    Column() {
        Text(text = "What are you looking for?", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        Text(text = "Start with this change, can be change later", modifier = Modifier.padding(top = 6.dp))
    }
}

fun onBtnClick() {
    Log.d("NEHA", "Btn Clicked")
}

@Composable
fun RenderSlot(heading: String, subheading: String, key: String) {
    val mContext = LocalContext.current
    Button(onClick = {

        val intent: Intent = Intent(
            mContext,
            RActivity::class.java
        )
        val b = Bundle()
        b.putString("key", key)
        intent.putExtras(b)
        mContext.startActivity(intent)
    }, modifier = Modifier.padding(top = 20.dp)) {
        Column() {
            Text(text = heading)
            Text(text = subheading)
        }
    }
}

@Composable
fun RenderAppServices() {
    Column(modifier = Modifier.padding(top = 24.dp)) {
      RenderSlot(heading = "Navigate to App A", subheading = "", "appA")
      RenderSlot(heading = "Navigate to App B", subheading = "", "appB")
  }
}

@Composable
fun RenderScreenContent() {
  Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
  ) {
      RenderHeader()
      RenderAppServices()
  }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview3() {
    NativeRNIntegrationTheme {
        RenderScreenContent()
    }
}


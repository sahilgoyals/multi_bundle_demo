package com.example.nativernintegration

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nativernintegration.ui.theme.NativeRNIntegrationTheme

class SplashScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val reactNativeInstanceWrapper = ReactNativeInstanceManager.getInstance()
//        reactNativeInstanceWrapper.Rebuild( this)
//        reactNativeInstanceWrapper.createReactContextInBackground();
        setContent {
            NativeRNIntegrationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ShowContent()
                }
            }
        }
    }
}

@Composable
fun HousingLogo() {
    val image = painterResource(R.drawable.housing_logo)
    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier.size(width = 124.dp, height = 124.dp)
    )
}

@Composable
fun AppName() {
    Text(
        text = stringResource(R.string.housing_app_name),
        modifier = Modifier.padding(top = 16.dp),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ActionCta() {
    val mContext = LocalContext.current
    Button(onClick = {
        mContext.startActivity(Intent(mContext, PreLoginScreen::class.java))
    }, modifier = Modifier.padding(top = 20.dp)) {
        Text(text = "Launch")
    }
}

@Composable
fun ShowContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        bootCommonRnBundle()
        HousingLogo()
        AppName()
        ActionCta()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview2() {
    NativeRNIntegrationTheme {
        ShowContent()
    }
}

//@Composable
//private fun bootCommonRnBundle() {
//    val reactNativeInstanceWrapper = ReactNativeInstanceManager.getInstance()
//    val application = LocalContext.current.applicationContext as Application
//    reactNativeInstanceWrapper.Rebuild(application, SplashScreen.)
//    reactNativeInstanceWrapper.createReactContextInBackground();
//}
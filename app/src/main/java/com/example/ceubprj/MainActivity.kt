package com.example.ceubprj

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ceubprj.ui.theme.CeubPrjTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CeubPrjTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "welcome_screen") {
                    composable("welcome_screen") {
                        WelcomeScreen(navController)
                    }
                    composable("webview_screen") {
                        WebViewScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun WelcomeScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { navController.navigate("webview_screen") }) {
                Text("Entrar")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Bem-vindo")
        }
    }
}

@Composable
fun WebViewScreen() {
    val url = "https://steamdb.info"
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    webViewClient = WebViewClient()
                    loadUrl(url)
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    CeubPrjTheme {
        WelcomeScreen(rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun WebViewScreenPreview() {
    CeubPrjTheme {
        WebViewScreen()
    }
}

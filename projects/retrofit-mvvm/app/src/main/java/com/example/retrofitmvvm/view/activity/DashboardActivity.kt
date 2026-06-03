package com.example.retrofitmvvm.view.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.retrofitmvvm.navigation.AppNavGraph
import com.example.retrofitmvvm.ui.theme.RetrofitMVVMTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            RetrofitMVVMTheme(
                darkTheme = false
            ) {

                val navController = rememberNavController()

                AppNavGraph(navController)

            }
        }
    }
}
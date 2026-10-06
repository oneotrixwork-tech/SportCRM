package com.oneotrixwork.sportcrm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    val repository = AuthRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            var loginText by remember { mutableStateOf("") }
            App(
                loginText = loginText,
                onLogin = {
                    lifecycleScope.launch {
                       try {
                            val res = repository.login(
                                login = "coach_tengo",
                                password = "123456"
                            )
                            if (res.success) {
                                loginText = "Успешно, роль: ${res.role}"
                            } else loginText = "Ошибка чего-то"
                        } catch (e: Exception) {
                            loginText = "Ошибка логина или пароля"
                        }

                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
}
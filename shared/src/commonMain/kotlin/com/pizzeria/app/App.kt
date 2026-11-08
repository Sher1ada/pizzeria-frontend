package com.pizzeria.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*

@Composable
fun App(
    onBiometricClick: ((onSuccess: (role: String, name: String) -> Unit) -> Unit)? = null
) {
    var userRole by remember { mutableStateOf<String?>(null) }
    var userName by remember { mutableStateOf<String?>(null) }

    MaterialTheme {
        if (userRole == null || userName == null) {
            LoginScreen(
                onLoginSuccess = { role, nombre ->
                    userRole = role
                    userName = nombre
                },
                onBiometricClick = onBiometricClick?.let { triggerBiometric ->
                    {
                        triggerBiometric { role, nombre ->
                            userRole = role
                            userName = nombre
                        }
                    }
                }
            )
        } else {
            val logoutAction = {
                userRole = null
                userName = null
            }

            when (userRole!!.uppercase()) {
                "ADMINISTRADOR", "ADMIN" -> AdminScreen(nombre = userName!!, onLogout = logoutAction)
                "SUPERVISOR" -> SupervisorScreen(nombre = userName!!, onLogout = logoutAction)
                "CLIENTE" -> ClientScreen(nombre = userName!!, onLogout = logoutAction)
                "VENDEDOR" -> VendedorScreen(nombre = userName!!, onLogout = logoutAction)
                else -> ClientScreen(nombre = userName!!, onLogout = logoutAction)
            }
        }
    }
}
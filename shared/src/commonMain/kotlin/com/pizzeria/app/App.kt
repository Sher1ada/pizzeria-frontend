package com.pizzeria.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*

@Composable
fun App() {
    var userRole by remember { mutableStateOf<String?>(null) }
    var userName by remember { mutableStateOf<String?>(null) }

    MaterialTheme {
        if (userRole == null || userName == null) {
            LoginScreen(onLoginSuccess = { role, nombre ->
                userRole = role
                userName = nombre
            })
        } else {
            RoleDashboard(
                role = userRole!!,
                nombre = userName!!,
                onLogout = {
                    userRole = null
                    userName = null
                }
            )
        }
    }
}
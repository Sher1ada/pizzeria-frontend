package com.pizzeria.app

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientScreen(
    nombre: String,
    onLogout: () -> Unit
) {
    val cartItems = remember { mutableStateListOf<Product>() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Hola, $nombre 🍕") },
                actions = {
                    TextButton(onClick = onLogout) {
                        Text("Cerrar Sesión")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            CatalogScreen(
                onProductSelected = { product ->
                    cartItems.add(product)
                }
            )
        }
    }
}
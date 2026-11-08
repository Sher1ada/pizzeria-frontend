package com.pizzeria.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.fragment.app.FragmentActivity

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            var biometricError by remember { mutableStateOf<String?>(null) }
            val isBiometricReady = BiometricHelper.isBiometricAvailable(this)

            App(
                onBiometricClick = if (isBiometricReady) { onSuccess ->
                    BiometricHelper.showBiometricPrompt(
                        activity = this,
                        onSuccess = {
                            // Al escanear la huella correctamente, asigna el rol y entra a la app
                            onSuccess("CLIENTE", "Usuario Biométrico")
                        },
                        onError = { error ->
                            biometricError = error
                        }
                    )
                } else null
            )
        }
    }
}
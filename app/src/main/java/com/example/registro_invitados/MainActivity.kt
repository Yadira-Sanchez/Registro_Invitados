package com.example.registro_invitados

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.registro_invitados.ui.theme.Registro_InvitadosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Registro_InvitadosTheme {
                // Pantalla principal
                Surface(modifier = Modifier.fillMaxSize()) {
                    ControlAforo()
                }
            }
        }
    }
}

@Composable
fun ControlAforo() {
    // Variables que guardan la información (Estado)
    var personas by remember { mutableIntStateOf(0) }
    var ultimo by remember { mutableStateOf("Ninguno") }
    var textoNombre by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Título y Contadores
        Text("Control de Aforo", fontSize = 30.sp)
        Text("Personas adentro: $personas", fontSize = 20.sp)
        Text("Último registrado: $ultimo")

        // 2. Formulario para registrar
        TextField(
            value = textoNombre,
            onValueChange = { textoNombre = it },
            label = { Text("Escribe el nombre aquí") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (textoNombre.isNotEmpty()) {
                    ultimo = textoNombre
                    personas = personas + 1
                    textoNombre = "" // Borra el nombre escrito
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar e Ingresar")
        }

        // 3. Botones de abajo
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = {
                if (personas > 0) personas = personas - 1
            }) {
                Text("Salida (-1)")
            }

            Button(onClick = {
                personas = 0
                ultimo = "Ninguno"
            }) {
                Text("Reiniciar")
            }
        }
    }
}

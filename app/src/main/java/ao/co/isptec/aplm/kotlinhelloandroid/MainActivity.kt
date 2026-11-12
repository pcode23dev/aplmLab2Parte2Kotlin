package ao.co.isptec.aplm.kotlinhelloandroid

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import android.util.Log
import ao.co.isptec.aplm.kotlinhelloandroid.ui.theme.KotlinHelloAndroidTheme
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        Log.d("MainActivity Exec 2", "onCreate")

        setContent {
            KotlinHelloAndroidTheme {

                val contexto = LocalContext.current
                var mensagem by remember {
                    mutableStateOf("")
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        OutlinedTextField(
                            value = mensagem,
                            onValueChange = { mensagem = it },
                            label = {
                                Text("Escreva uma mensagem")
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Button(
                            onClick = {

                                Toast.makeText(
                                    contexto,
                                    "A enviar mensagem...",
                                    Toast.LENGTH_SHORT
                                ).show()

                                Log.d(
                                    "MainActivity",
                                    "Mensagem enviada: $mensagem"
                                )

                                val intent = Intent(
                                    contexto,
                                    MostrarMensagemActivity::class.java
                                )

                                intent.putExtra(
                                    "dataCampoMensagem",
                                    mensagem
                                )
                                // Limpar campo
                                mensagem = ""

                                contexto.startActivity(intent)
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Enviar mensagem")
                        }

                        Button(
                            onClick = {
                                val intent = Intent(
                                    contexto,
                                    GestorTarefasActivity::class.java
                                )
                                contexto.startActivity(intent)
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Abrir Gestor de Tarefa")
                        }
                    }
                }
            }
        }
    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinHelloAndroidTheme {
        Greeting("Android")
    }
}
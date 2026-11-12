
package ao.co.isptec.aplm.kotlinhelloandroid

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import ao.co.isptec.aplm.kotlinhelloandroid.ui.theme.KotlinHelloAndroidTheme

class GestorTarefasActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KotlinHelloAndroidTheme {

                val taskList = remember {
                    mutableStateListOf<Task>()
                }

                var text by remember {
                    mutableStateOf("")
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {

                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
                        label = { Text("Nova Tarefa") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            val novaTarefa = text.trim()

                            if (novaTarefa.isNotEmpty()) {

                                val existe = taskList.any {
                                    it.titulo.equals(novaTarefa, ignoreCase = true)
                                }

                                if (!existe) {
                                    taskList.add(
                                        Task(titulo = novaTarefa)
                                    )
                                    Log.d("GestorTarefas", "Tarefa adicionada: $novaTarefa")
                                    text = ""

                                } else {
                                    Toast.makeText(
                                        this@GestorTarefasActivity,
                                        "Tarefa duplicada: $novaTarefa",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    Log.d("GestorTarefas", "Tarefa duplicada: $novaTarefa")
                                }

                            } else {
                                Toast.makeText(
                                    this@GestorTarefasActivity,
                                    "Campo vazio. Nenhuma tarefa adicionada.",
                                    Toast.LENGTH_SHORT).show()
                                Log.d("GestorTarefas", "Campo vazio. Nenhuma tarefa adicionada.")
                            }
                        }
                    ) {
                        Text("➕")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    LazyColumn {
                        itemsIndexed(taskList) { index, task ->

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Checkbox(
                                    checked = task.isConcluido,
                                    onCheckedChange = { checked ->
                                        taskList[index] = task.copy(isConcluido = checked)
                                    }
                                )

                                Text(
                                    text = task.titulo, modifier = Modifier
                                        .weight(1f)
                                        .padding(8.dp)
                                        .clickable() {
                                            taskList.removeAt(index)
                                            Toast.makeText(
                                                this@GestorTarefasActivity,
                                                "Tarefa removida: ${task.titulo}",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            Log.d(
                                                "GestorTarefas",
                                                "Tarefa removida: ${task.titulo}"
                                            )
                                        },
                                    textDecoration = if (task.isConcluido)
                                        TextDecoration.LineThrough
                                    else
                                        TextDecoration.None
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

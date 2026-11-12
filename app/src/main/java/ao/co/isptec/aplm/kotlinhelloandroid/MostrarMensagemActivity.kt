package ao.co.isptec.aplm.kotlinhelloandroid

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ao.co.isptec.aplm.kotlinhelloandroid.ui.theme.KotlinHelloAndroidTheme

class MostrarMensagemActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mensagem = intent.getStringExtra("dataCampoMensagem")
            ?: "Nenhuma mensagem recebida"


        // 2. Separar palavras
        val palavras = mensagem?.split(" ") ?: emptyList()

        // 3. Contar palavras
        val numPalavras = palavras.size

        // 4. Verificar estado
        val status = if (palavras.isEmpty()) {
            "Não processado"
        } else {
            "Processado"
        }

        val resumo = when {
            numPalavras == 0 -> "Nenhuma palavra encontrada"
            numPalavras == 1 -> "Uma palavra encontrada"
            else -> "Foram encontradas $numPalavras palavras"
        }

        // 6. Apresentar resultados
        Log.d("EXERCICIO_2_1", "Mensagem: $mensagem")
        Log.d("EXERCICIO_2_1", "Palavras: $palavras")
        Log.d("EXERCICIO_2_1", "Quantidade: $numPalavras")
        Log.d("EXERCICIO_2_1", "Estado: $status")
        Log.d("EXERCICIO_2_1", "Resumo: $resumo")


        Log.d("EXERCICIO_2_2", "inicio")

        val palavrasTeste = palavras.toList()

        val bundle = PalavraBundle(palavrasTeste)

        Log.d("EXERCICIO_2_2", "Total: ${bundle.contar}")
        Log.d("EXERCICIO_2_2", "Média: ${bundle.comprimentoMedio}")
        Log.d("EXERCICIO_2_2", "Maior palavra: ${bundle.palavraMaisLonga}")

        Log.d("EXERCICIO_2_3", "iincio do exercicoi 3")


        val resumoGerado = gerarResumo(bundle)
        Log.d("EXERCICIO_2_3", resumoGerado)



        setContent {
            KotlinHelloAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Mensagem recebida:"
                        )

                        Text(
                            text = mensagem
                        )
                    }
                }
            }
        }
    }
}
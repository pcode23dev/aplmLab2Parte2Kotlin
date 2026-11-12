package ao.co.isptec.aplm.kotlinhelloandroid

data class PalavraBundle(val palavras: List<String>) {

        val contar = palavras.size

        val comprimentoMedio = palavras.map { it.length }.average()

        val palavraMaisLonga = palavras.maxByOrNull { it.length } ?: "Não disponível."

}

fun gerarResumo(bundle: PalavraBundle): String {

        // Filtrar palavras com mais de 4 caracteres
        val palavrasLongas = bundle.palavras.filter {
                it.length > 4
        }

        // Devolver resumo
        return """
        Total de palavras: ${bundle.contar}
        Comprimento médio: ${bundle.comprimentoMedio}
        Palavra mais longa: ${bundle.palavraMaisLonga}
        Palavras longas: ${palavrasLongas.joinToString()}
    """.trimIndent()
}
package com.example.truker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener
import androidx.core.view.WindowInsetsCompat
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {

    // declaração variáveis
    private var jogador1Pontos = 0
    private var jogador2Pontos = 0
    private var jogador1Vitorias = 0
    private var jogador2Vitorias = 0

    private lateinit var tvJogador1: TextView
    private lateinit var tvJogador2: TextView
    private lateinit var tvJogador1Pontos: TextView
    private lateinit var tvJogador2Pontos: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicialização dos TextViews
        tvJogador1 = findViewById(R.id.tvJogador1)
        tvJogador2 = findViewById(R.id.tvJogador2)
        tvJogador1Pontos = findViewById(R.id.tvJogador1Pontos)
        tvJogador2Pontos = findViewById(R.id.tvJogador2Pontos)

        tvJogador1Pontos.text = jogador1Pontos.toString()
        tvJogador2Pontos.text = jogador2Pontos.toString()

        // Configuração dos botões do Jogador 1
        findViewById<Button>(R.id.btJogador1Soma1).setOnClickListener {
            atualizarPontos(1, 1)
        }
        findViewById<Button>(R.id.btJogador1Soma3).setOnClickListener {
            atualizarPontos(1, 3)
        }
        findViewById<Button>(R.id.btJogador1Soma6).setOnClickListener {
            atualizarPontos(1, 6)
        }
        findViewById<Button>(R.id.btJogador1Soma9).setOnClickListener {
            atualizarPontos(1, 9)
        }
        findViewById<Button>(R.id.btJogador1Soma12).setOnClickListener {
            atualizarPontos(1, 12)
        }

        // Configuração dos botões do Jogador 2
        findViewById<Button>(R.id.btJogador2Soma1).setOnClickListener {
            atualizarPontos(2, 1)
        }
        findViewById<Button>(R.id.btJogador2Soma3).setOnClickListener {
            atualizarPontos(2, 3)
        }
        findViewById<Button>(R.id.btJogador2Soma6).setOnClickListener {
            atualizarPontos(2, 6)
        }
        findViewById<Button>(R.id.btJogador2Soma9).setOnClickListener {
            atualizarPontos(2, 9)
        }
        findViewById<Button>(R.id.btJogador2Soma12).setOnClickListener {
            atualizarPontos(2, 12)
        }

        // Configuração dos botões de controle
        findViewById<Button>(R.id.btHistorico).setOnClickListener {
            val intent = Intent(this, Historico::class.java).apply {
                putExtra("nomeJogador1", tvJogador1.text.toString())
                putExtra("qtVitoriasJogador1", jogador1Vitorias)
                putExtra("nomeJogador2", tvJogador2.text.toString())
                putExtra("qtVitoriasJogador2", jogador2Vitorias)
            }
            startActivity(intent)
        }
    }

    private fun atualizarPontos(jogador: Int, pontos: Int) {
        if (jogador == 1) {
            jogador1Pontos += pontos
            tvJogador1Pontos.text = jogador1Pontos.toString()
            verificarVencedor(1)
        } else {
            jogador2Pontos += pontos
            tvJogador2Pontos.text = jogador2Pontos.toString()
            verificarVencedor(2)
        }
    }

    private fun verificarVencedor(jogador: Int) {
        val pontos = if (jogador == 1) jogador1Pontos else jogador2Pontos

        if (pontos > 11) {
            if (jogador == 1) {
                jogador1Vitorias++
            } else {
                jogador2Vitorias++
            }

            // Exemplo com AlertDialog (Recomendado para fim de jogo)
            AlertDialog.Builder(this)
                .setTitle("Fim de Partida!")
                .setMessage("Jogador $jogador venceu a partida!")
                .setPositiveButton("OK") { _, _ ->
                    zerarPontos()
                }
                .setCancelable(false)
                .show()
        }
    }

    private fun zerarPontos() {
        jogador1Pontos = 0
        jogador2Pontos = 0
        tvJogador1Pontos.text = jogador1Pontos.toString()
        tvJogador2Pontos.text = jogador2Pontos.toString()
    }
}

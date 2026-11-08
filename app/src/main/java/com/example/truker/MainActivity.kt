package com.example.truker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var nomeJogador1 = "Jogador 1"
    private var nomeJogador2 = "Jogador 2"

    private val getNomeJogadores = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            val nome1 = data?.getStringExtra("nomeJogador1")
            val nome2 = data?.getStringExtra("nomeJogador2")

            if (!nome1.isNullOrEmpty()) {
                nomeJogador1 = nome1
                tvJogador1.text = nome1
            }
            if (!nome2.isNullOrEmpty()) {
                nomeJogador2 = nome2
                tvJogador2.text = nome2
            }
        }
    }

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

        tvJogador1.text = nomeJogador1
        tvJogador2.text = nomeJogador2
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
                putExtra("nomeJogador1", nomeJogador1)
                putExtra("qtVitoriasJogador1", jogador1Vitorias)
                putExtra("nomeJogador2", nomeJogador2)
                putExtra("qtVitoriasJogador2", jogador2Vitorias)
            }
            startActivity(intent)
        }

        findViewById<Button>(R.id.btZerarHistorico).setOnClickListener {
            jogador1Vitorias = 0
            jogador2Vitorias = 0
            zerarPontos()
        }

        findViewById<Button>(R.id.btInfNomes).setOnClickListener {
            val intent = Intent(this, InformarNomes::class.java).apply {
                putExtra("nomeJogador1", nomeJogador1)
                putExtra("nomeJogador2", nomeJogador2)
            }
            getNomeJogadores.launch(intent)
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
            val nomeVencedor = if (jogador == 1) nomeJogador1 else nomeJogador2
            if (jogador == 1) {
                jogador1Vitorias++
            } else {
                jogador2Vitorias++
            }

            AlertDialog.Builder(this)
                .setTitle("Fim de Partida!")
                .setMessage("$nomeVencedor venceu a partida!")
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

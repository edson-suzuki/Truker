package com.example.truker

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Historico : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historico)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Receber os parâmetros enviados via Intent
        val nomeJogador1 = intent.getStringExtra("nomeJogador1") ?: "Jogador 1"
        val vitoriasJogador1 = intent.getIntExtra("qtVitoriasJogador1", 0)

        val nomeJogador2 = intent.getStringExtra("nomeJogador2") ?: "Jogador 2"
        val vitoriasJogador2 = intent.getIntExtra("qtVitoriasJogador2", 0)

        // 2. Mapear os elementos do layout
        val tvNomeJogador1 = findViewById<TextView>(R.id.tvNomeJogador1)
        val tvVitoriasJogador1 = findViewById<TextView>(R.id.tvVitoriasJogador1)
        val tvNomeJogador2 = findViewById<TextView>(R.id.tvNomeJogador2)
        val tvVitoriasJogador2 = findViewById<TextView>(R.id.tvVitoriasJogador2)
        val btVoltar = findViewById<Button>(R.id.btVoltar)

        // 3. Exibir os dados recebidos nos TextViews
        tvNomeJogador1.text = nomeJogador1
        tvVitoriasJogador1.text = vitoriasJogador1.toString()
        tvNomeJogador2.text = nomeJogador2
        tvVitoriasJogador2.text = vitoriasJogador2.toString()

        // 4. Voltar para a tela anterior
        btVoltar.setOnClickListener {
            finish()
        }
    }
}
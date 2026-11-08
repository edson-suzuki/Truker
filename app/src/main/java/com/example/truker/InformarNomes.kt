package com.example.truker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InformarNomes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_informar_nomes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etJogador1 = findViewById<EditText>(R.id.etJogador1)
        val etJogador2 = findViewById<EditText>(R.id.etJogador2)

        // Preenche com os nomes recebidos da MainActivity, se existirem
        val nome1Atual = intent.getStringExtra("nomeJogador1")
        val nome2Atual = intent.getStringExtra("nomeJogador2")
        if (!nome1Atual.isNullOrEmpty()) etJogador1.setText(nome1Atual)
        if (!nome2Atual.isNullOrEmpty()) etJogador2.setText(nome2Atual)

        findViewById<Button>(R.id.btConfirmar).setOnClickListener {
            val resultIntent = Intent().apply {
                putExtra("nomeJogador1", etJogador1.text.toString())
                putExtra("nomeJogador2", etJogador2.text.toString())
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}

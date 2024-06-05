package com.pdm115.proyectopdm2024_gt1_grupo1_tema1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegistroParteUno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_parte_uno)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnRegistrarse: Button = findViewById(R.id.btn_siguiente_registro_parte1)

        btnRegistrarse.setOnClickListener {
            val intent = Intent(this, RegistroParteDos::class.java)
            startActivity(intent)
        }

        val txtIniciarSesion = findViewById<TextView>(R.id.txt_ingresa_aqui_registro_parte1)
        txtIniciarSesion.setOnClickListener {
            val intent = Intent(this, IniciarSesion::class.java)
            startActivity(intent)
        }
    }
}
package com.pdm115.proyectopdm2024_gt1_grupo1_tema1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Calificaciones : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calificaciones)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Encontrar el RecyclerView utilizando la vista inflada
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_comentarios_califiaciones)

        // Configurar el LayoutManager
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Configurar el Adaptador
        val adapter = CalificacionesAdapter()
        recyclerView.adapter = adapter

        val btnVolverInicio: Button = findViewById(R.id.btn_volver_inicio_calificaciones)

        btnVolverInicio.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
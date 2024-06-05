package com.pdm115.proyectopdm2024_gt1_grupo1_tema1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegistroParteUno : AppCompatActivity() {

    private lateinit var nombreCompleto: EditText
    private lateinit var correoElectronico: EditText
    private lateinit var fechaNacimiento: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_parte_uno)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        try {
            bindEditText()
        } catch (e: Exception) {
            Log.e("RegistroParteUno", "Error al vincular EditTexts", e)
            Toast.makeText(this, "Error al inicializar campos de texto", Toast.LENGTH_LONG).show()
        }
        val btnRegistrarse: Button = findViewById(R.id.btn_siguiente_registro_parte1)
        val btnRegresar: Button = findViewById(R.id.btn_anterior_registro_parte1)

        btnRegistrarse.setOnClickListener {
            if (!verificarCampos()) {
                Toast.makeText(this, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!correoValido(correoElectronico.text.toString())) {
                Toast.makeText(this, "Correo electrónico inválido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(this, RegistroParteDos::class.java)
            intent.putExtra("nombreCompleto", nombreCompleto.text.toString())
            intent.putExtra("correoElectronico", correoElectronico.text.toString())
            intent.putExtra("fechaNacimiento", fechaNacimiento.text.toString())
            startActivity(intent)
        }

        val txtIniciarSesion = findViewById<TextView>(R.id.txt_ingresa_aqui_registro_parte1)
        txtIniciarSesion.setOnClickListener {
            val intent = Intent(this, IniciarSesion::class.java)
            startActivity(intent)
        }
    }

    private fun verificarCampos(): Boolean {
        return nombreCompleto.text.isNotEmpty() && correoElectronico.text.isNotEmpty() && fechaNacimiento.text.isNotEmpty()
    }

    private fun bindEditText () {
        nombreCompleto = findViewById(R.id.edtxt_nombre_completo_registro_parte1)
        correoElectronico = findViewById(R.id.edtxt_correo_registro_parte1)
        fechaNacimiento = findViewById(R.id.edtxt_fecha_nacimiento_registro_parte1)
    }

    private fun correoValido(correo: String): Boolean {
        return correo.contains("@") && correo.contains(".")
    }

}
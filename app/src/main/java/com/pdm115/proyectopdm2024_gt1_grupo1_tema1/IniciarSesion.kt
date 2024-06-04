package com.pdm115.proyectopdm2024_gt1_grupo1_tema1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Data.FirebaseClient

class IniciarSesion : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var correo: EditText
    private lateinit var clave: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_iniciar_sesion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            goPrincipalMenu()
        }

        bindEditText()

        val buttonAtras: Button = findViewById(R.id.btn_anterior_login)
        val buttonLogin: Button = findViewById(R.id.btn_ingresar_login)

        buttonLogin.setOnClickListener {
            if (verificarCampos()) {
                val correo = correo.text.toString()
                val clave = clave.text.toString()

                auth.signInWithEmailAndPassword(correo, clave)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            goPrincipalMenu()
                        } else{
                            Toast.makeText(this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        buttonAtras.setOnClickListener {
            finish()
        }
    }

    private fun verificarCampos(): Boolean {
        return correo.text.isNotEmpty() && clave.text.isNotEmpty()
    }

    private fun goToMainActivity ()
    {
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun bindEditText ()
    {
        correo = findViewById(R.id.edtxt_correo)
        clave = findViewById(R.id.edtxt_contrasena)
    }

    private fun goPrincipalMenu()
    {
        val intent: Intent = Intent(this, PrincipalConMenus::class.java)
        startActivity(intent)
    }
}
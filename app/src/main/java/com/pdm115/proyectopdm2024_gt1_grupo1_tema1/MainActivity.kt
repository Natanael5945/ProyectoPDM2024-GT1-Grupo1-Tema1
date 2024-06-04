package com.pdm115.proyectopdm2024_gt1_grupo1_tema1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Data.Services.AuthService
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Data.Services.BoletinService
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Data.Services.RolService
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Data.Services.SoporteBoletoService
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Data.Services.UsuarioService
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models.Boletines
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models.Usuario
import java.util.UUID

class MainActivity : AppCompatActivity() {

    private lateinit var rolService: RolService
    private lateinit var usuarioService: UsuarioService
    private lateinit var authService: AuthService
    private lateinit var soporteBoletoService: SoporteBoletoService
    private lateinit var boletinService: BoletinService
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
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


        rolService = RolService(FirebaseDatabase.getInstance())
        usuarioService = UsuarioService(FirebaseDatabase.getInstance())
        soporteBoletoService = SoporteBoletoService(FirebaseDatabase.getInstance())
        boletinService = BoletinService(FirebaseDatabase.getInstance())

        val btnLogin:Button = findViewById(R.id.btn_iniciar_sesion)
        val btnRegistrarse:Button = findViewById(R.id.btn_registrarse)

        btnLogin.setOnClickListener {
            val intent = Intent(this, IniciarSesion::class.java)
            startActivity(intent)
        }

        btnRegistrarse.setOnClickListener {
            val intent2 = Intent(this, RegistroParteUno::class.java)
            startActivity(intent2)
        }


//        boletinService.existCollection {
//            if (it) {
//                Toast.makeText(this, "La colección de boletines existe", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(this, "La colección de boletines no existe", Toast.LENGTH_SHORT).show()
//                val newBoletin: Boletines = Boletines(
//                    UUID.randomUUID().toString(),
//                    "heac.creative@gmail.com",
//                )
//
//                boletinService.createBoletin(newBoletin) {
//                    if (it) {
//                        Toast.makeText(this, "Boletín creado", Toast.LENGTH_SHORT).show()
//                    } else {
//                        Toast.makeText(this, "Error al crear el boletín", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        }
//


        

    }

    private fun goPrincipalMenu()
    {
        val intent: Intent = Intent(this, PrincipalConMenus::class.java)
        startActivity(intent)
    }
}
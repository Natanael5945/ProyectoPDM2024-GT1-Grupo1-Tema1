package com.pdm115.proyectopdm2024_gt1_grupo1_tema1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Data.FirebaseClient
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Data.Services.AuthService
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Data.Services.RolService
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Data.Services.UsuarioService
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models.Rol
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models.Usuario
import java.util.UUID
import javax.inject.Inject
import android.widget.EditText
import android.widget.Toast

class RegistroParteDos : AppCompatActivity() {

    private lateinit var nombreUsuario: EditText
    private lateinit var contrasena: EditText
    private lateinit var confirmarContrasena: EditText

    private lateinit var rolService: RolService
    private lateinit var usuarioService: UsuarioService
    private lateinit var authService: AuthService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_parte_dos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializa los servicios
        val firebaseClient = FirebaseClient()
        rolService = RolService(firebaseClient.db)
        usuarioService = UsuarioService(firebaseClient.db)
        authService = AuthService(firebaseClient)

        this.bindEditText()
        val btnRegistrarse: Button = findViewById(R.id.btn_registrarme_registro_parte2)
        val btnRegresar: Button = findViewById(R.id.btn_anterior_registro_parte2)
        val intentA1 = intent

        val nombreCompleto = intentA1.getStringExtra("nombreCompleto")
        val correoElectronico = intentA1.getStringExtra("correoElectronico")
        val fechaNacimiento = intentA1.getStringExtra("fechaNacimiento")

        btnRegistrarse.setOnClickListener {
            if (!this.verificarCampos()) {
                Toast.makeText(this, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (contrasena.text.toString() != confirmarContrasena.text.toString()) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            rolService.getClientRol { result ->
                result.onSuccess { rol ->
                    if (rol != null) {
                        Toast.makeText(this, "Rol de cliente obtenido " + rol.nombreRol, Toast.LENGTH_SHORT).show()
                        if (rol.nombreRol != "") {
                            val nuevoUsuario = Usuario(
                                idUsuario = UUID.randomUUID().toString(),
                                nombreUsuario = nombreUsuario.text.toString(),
                                nombreCompleto = nombreCompleto!!,
                                correoUsuario = correoElectronico!!,
                                clave = contrasena.text.toString(),
                                fechaNacimiento = fechaNacimiento!!,
                                rol = rol,
                                dui = "",
                                avatarPath = ""
                            )
                            crearUsuarioFirebase(nuevoUsuario)
                        }
                    } else {
                        Toast.makeText(this, "Rol no encontrado", Toast.LENGTH_SHORT).show()
                    }
                }.onFailure { exception ->
                    Toast.makeText(this, "Error al obtener el rol: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
            }

            rolService.getClientRol { clienteRol ->
                if (clienteRol != null) {

                } else {
                    Toast.makeText(this, "Error al obtener el rol de cliente", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnRegresar.setOnClickListener {
            finish()
        }

        val txtIniciarSesion = findViewById<TextView>(R.id.txt_ingresa_aqui_registro_parte2)
        txtIniciarSesion.setOnClickListener {
            val intent = Intent(this, IniciarSesion::class.java)
            startActivity(intent)
        }
    }

    /**
    * Verifica que los campos no estén vacíos
     */
    private fun verificarCampos(): Boolean {
        return nombreUsuario.text.isNotEmpty() && contrasena.text.isNotEmpty() && confirmarContrasena.text.isNotEmpty()
    }

    /**
    * Asocia los EditText con las variables
    */
    private fun bindEditText() {
        nombreUsuario = findViewById(R.id.edtxt_nombre_usuario_parte2)
        contrasena = findViewById(R.id.edtxt_contrasenia_parte2)
        confirmarContrasena = findViewById(R.id.edtxt_repetir_contrasenia)
    }

    @SuppressLint("ShowToast")
    private fun crearUsuarioFirebase(nuevoUsuario: Usuario)
    {

        usuarioService.existUsuario(nuevoUsuario.correoUsuario) { exists ->
            if (exists) {
                // user already exists
                Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Registrando usuario", Toast.LENGTH_SHORT).show()
                usuarioService.existCollection { existCol ->
                    if (existCol) {
                        usuarioService.createUsuario(nuevoUsuario) { it ->
                            if (it) {
                                authService.createAccount(nuevoUsuario.correoUsuario,nuevoUsuario.clave) { lr ->
                                    if (lr) {
                                        Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show()
                                        goRegistroCompletadoIntent()
                                    } else {
                                        Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
                                        //- delete usuario
                                        usuarioService.deleteUsuario(nuevoUsuario.idUsuario) { _ -> }
                                        return@createAccount
                                    }
                                }
                            } else {
                                Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }

            }
        }

    }

    private fun goRegistroCompletadoIntent()
    {
        val intent = Intent(this, RegistroCompletado::class.java)
        startActivity(intent)
    }

    private fun goLoginIntent()
    {
        val intent: Intent = Intent(this, IniciarSesion::class.java)
        startActivity(intent)
    }

    private fun generateRoles(callback: (Rol) -> Unit)
    {
        val roles = listOf(
            Rol(UUID.randomUUID().toString(), "Cliente", "Cliente de la aplicación"),
            Rol(UUID.randomUUID().toString(), "Administrador", "Administrador de la aplicación")
        )
        roles.forEach {
            FirebaseDatabase.getInstance().getReference("Roles").child(it.idRol).setValue(it)
        }

        callback(roles.first { it.nombreRol == "Administrador" })
    }
}
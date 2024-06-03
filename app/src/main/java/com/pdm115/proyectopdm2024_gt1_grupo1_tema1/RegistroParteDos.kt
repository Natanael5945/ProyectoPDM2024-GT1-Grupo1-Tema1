package com.pdm115.proyectopdm2024_gt1_grupo1_tema1

import android.annotation.SuppressLint
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
import com.google.firebase.database.FirebaseDatabase
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models.Rol
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models.Usuario
import java.util.UUID

class RegistroParteDos : AppCompatActivity() {

    private lateinit var nombreUsuario: EditText
    private lateinit var contrasena: EditText
    private lateinit var confirmarContrasena: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_parte_dos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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
            getClientRol { clienteRol ->
                if (clienteRol != null) {
                    if (clienteRol.nombreRol != "") {
                        val nuevoUsuario = Usuario(
                            idUsuario = UUID.randomUUID().toString(),
                            nombreUsuario = nombreUsuario.text.toString(),
                            nombreCompleto = nombreCompleto!!,
                            correoUsuario = correoElectronico!!,
                            clave = contrasena.text.toString(),
                            fechaNacimiento = fechaNacimiento!!,
                            rol = clienteRol,
                            avatarPath = ""
                        )
                        this.crearUsuarioFirebase(nuevoUsuario)
                    }
                }
            }


        }

        btnRegresar.setOnClickListener {
            finish()
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

        existUserFirebase(nuevoUsuario) { exist ->
            if (exist as Boolean) {
                // user already exists
                Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show()
            } else {
                // register on firebasedatabase, then and firebase auth
        FirebaseDatabase.getInstance().getReference("Usuarios").child(nuevoUsuario.idUsuario).setValue(nuevoUsuario).addOnSuccessListener {
                Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show()
                    // register on firebase auth
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(nuevoUsuario.correoUsuario, nuevoUsuario.clave).addOnSuccessListener {
                        Toast.makeText(this, "Usuario registrado en Firebase Auth", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, RegistroCompletado::class.java)
                        startActivity(intent)
                    }.addOnFailureListener {
                        Toast.makeText(this, "Error al registrar usuario en Firebase Auth", Toast.LENGTH_SHORT).show()
                        //- delete usuario
                        FirebaseDatabase.getInstance().getReference("Usuarios").child(nuevoUsuario.idUsuario).removeValue()
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
                }
            }
        }

        
    }

    private fun existUserFirebase(nuevoUsuario: Usuario, callback: (Boolean) -> Unit) {
        

        val databaseReference = FirebaseDatabase.getInstance().getReference("Usuarios")
        val query = databaseReference.orderByChild("correoUsuario").equalTo(nuevoUsuario.correoUsuario)
        query.get().addOnSuccessListener {
            callback(it.exists())
        }.addOnFailureListener {
            callback(false)
        }
    }

    private fun goLoginIntent()
    {
        val intent: Intent = Intent(this, IniciarSesion::class.java)
        startActivity(intent)
    }

    private fun getClientRol(callback: (Rol?) -> Unit)
    {
        // if roles are not created, create them
        FirebaseDatabase.getInstance().getReference("Roles").get().addOnSuccessListener {
            if (!it.exists()) {
                generateRoles()
                // get and return administrator role
                FirebaseDatabase.getInstance().getReference("Roles").get().addOnSuccessListener {
                    val roles = it.children.map { it.getValue(Rol::class.java) }
                    val clienteRol = roles.firstOrNull { it?.nombreRol == "Administrador" }
                    if (clienteRol != null) {
                        Toast.makeText(this, "Rol Administrador encontrado: ${clienteRol.nombreRol}", Toast.LENGTH_SHORT).show()
                        callback(clienteRol)
                    } else {
                        Toast.makeText(this, "Rol Administrador no encontrado", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, "Error al obtener roles", Toast.LENGTH_SHORT).show()
                }
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Error al obtener roles", Toast.LENGTH_SHORT).show()
        }
        // get all roles from firebase
        FirebaseDatabase.getInstance().getReference("Roles").get().addOnSuccessListener { snapshot ->
            val roles = snapshot.children.mapNotNull { it.getValue(Rol::class.java) }

            // Encontramos el primer rol cuyo idRol sea "Cliente"
            val clienteRol: Rol? = roles.firstOrNull { it.nombreRol == "Cliente" }

            if (clienteRol != null) {
                // Aquí puedes usar el rol encontrado
                Toast.makeText(this, "Rol Cliente encontrado: ${clienteRol.nombreRol}", Toast.LENGTH_SHORT).show()
                callback(clienteRol)
            } else {
                // Manejo cuando no se encuentra el rol "Cliente"
                Toast.makeText(this, "Rol Cliente no encontrado", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            // Manejo de error en caso de que falle la obtención de los roles
            Toast.makeText(this, "Error al obtener roles", Toast.LENGTH_SHORT).show()
        }
    }

    private fun generateRoles()
    {
        val roles = listOf(
            Rol(UUID.randomUUID().toString(), "Cliente", "Cliente de la aplicación"),
            Rol(UUID.randomUUID().toString(), "Administrador", "Administrador de la aplicación")
        )
        roles.forEach {
            FirebaseDatabase.getInstance().getReference("Roles").child(it.idRol).setValue(it)
        }
    }
}
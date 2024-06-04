package com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Data.Services

import com.google.firebase.database.FirebaseDatabase
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models.Rol
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models.Usuario
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsuarioService  @Inject constructor(private val firebase: FirebaseDatabase)  {
    companion object {
        const val COLECCION_USUARIO = "Usuarios"
    }

    fun getUsuarioById(idUsuario: String, callback: (Usuario) -> Unit) {
        firebase.getReference(COLECCION_USUARIO).child(idUsuario).get()
            .addOnSuccessListener {
                val usuario = it.getValue(Usuario::class.java)
                if (usuario != null) {
                    callback(usuario)
                } else {
                    throw Exception("Error al obtener el usuario")
                }
            }
            .addOnFailureListener { exception ->
                throw Exception("Error al obtener el usuario: $exception")
            }
    }

    fun getUsuarioByCorreo(correo: String, callback: (Usuario) -> Unit) {
        firebase.getReference(COLECCION_USUARIO).get().addOnSuccessListener { snapshot ->
            val usuarios = snapshot.children.mapNotNull { it.getValue(Usuario::class.java) }
            val usuario = usuarios.firstOrNull { it.correoUsuario == correo }
            if (usuario != null) {
                callback(usuario)
            } else {
                throw Exception("Error al obtener el usuario")
            }
        }
    }

    fun createUsuario(usuario: Usuario, callback: (Boolean) -> Unit) {
        firebase.getReference(COLECCION_USUARIO).child(usuario.idUsuario).setValue(usuario)
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener { exception ->
                throw Exception("Error al crear el usuario: $exception")
            }
    }
    fun updateUsuario(usuario: Usuario, callback: (Boolean) -> Unit) {
        firebase.getReference(COLECCION_USUARIO).child(usuario.idUsuario).setValue(usuario)
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener { exception ->
                throw Exception("Error al actualizar el usuario: $exception")
            }
    }
    fun deleteUsuario(idUsuario: String, callback: (Boolean) -> Unit) {
        firebase.getReference(COLECCION_USUARIO).child(idUsuario).removeValue()
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener { exception ->
                throw Exception("Error al eliminar el usuario: $exception")
            }
    }

    fun getUsuarios() {
        firebase.getReference(COLECCION_USUARIO).get()
            .addOnSuccessListener {
                val usuarios = it.getValue(Usuario::class.java)
                if (usuarios != null) {
                    //return usuarios
                } else {
                    throw Exception("Error al obtener los usuarios")
                }
            }
            .addOnFailureListener { exception ->
                throw Exception("Error al obtener los usuarios: $exception")
            }
    }

    fun existCollection(callback: (Boolean) -> Unit) {
        firebase.getReference(COLECCION_USUARIO).get()
            .addOnSuccessListener {
                callback(it.exists())
            }
            .addOnFailureListener {
                callback(false)
            }
    }

    fun existUsuario(correo: String, callback: (Boolean) -> Unit) {
        firebase.getReference(COLECCION_USUARIO).orderByChild("correoUsuario").equalTo(correo).get()
            .addOnSuccessListener {
                callback(it.exists())
            }
            .addOnFailureListener {
                callback(false)
            }
    }

    
}
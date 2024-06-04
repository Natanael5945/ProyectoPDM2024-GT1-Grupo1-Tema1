package com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Data.Services

import com.google.firebase.database.FirebaseDatabase
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models.Rol
import javax.inject.Inject

class RolService @Inject constructor(private val firebase: FirebaseDatabase) {
  companion object {
    const val COLECCION_ROL = "Roles"
  }

  fun getRolById(idRol: String, callback: (result: Result<Rol?>) -> Unit) {
    firebase.getReference(COLECCION_ROL).child(idRol).get()
      .addOnSuccessListener { dataSnapshot ->
        val rol = dataSnapshot.getValue(Rol::class.java)
        callback(Result.success(rol))
      }
      .addOnFailureListener { exception ->
        callback(Result.failure(exception))
      }
  }

  fun getRolByNombre(nombre: String, callback: (result: Result<List<Rol>>) -> Unit) {
    firebase.getReference(COLECCION_ROL).orderByChild("nombreRol").equalTo(nombre).get()
      .addOnSuccessListener { dataSnapshot ->
        val roles = dataSnapshot.children.mapNotNull { it.getValue(Rol::class.java) }
        callback(Result.success(roles))
      }
      .addOnFailureListener { exception ->
        callback(Result.failure(exception))
      }
  }

  fun getRoles(callback: (result: Result<List<Rol>>) -> Unit) {
    firebase.getReference(COLECCION_ROL).get()
      .addOnSuccessListener { dataSnapshot ->
        val roles = dataSnapshot.children.mapNotNull { it.getValue(Rol::class.java) }
        callback(Result.success(roles))
      }
      .addOnFailureListener { exception ->
        callback(Result.failure(exception))
      }
  }

  fun createRol(rol: Rol, callback: (result: Result<Boolean>) -> Unit) {
    firebase.getReference(COLECCION_ROL).child(rol.idRol).setValue(rol)
      .addOnSuccessListener {
        callback(Result.success(true))
      }
      .addOnFailureListener { exception ->
        callback(Result.failure(exception))
      }
  }

  fun updateRol(rol: Rol, callback: (result: Result<Boolean>) -> Unit) {
    firebase.getReference(COLECCION_ROL).child(rol.idRol).setValue(rol)
      .addOnSuccessListener {
        callback(Result.success(true))
      }
      .addOnFailureListener { exception ->
        callback(Result.failure(exception))
      }
  }

  fun deleteRol(idRol: String, callback: (result: Result<Boolean>) -> Unit) {
    firebase.getReference(COLECCION_ROL).child(idRol).removeValue()
      .addOnSuccessListener {
        callback(Result.success(true))
      }
      .addOnFailureListener { exception ->
        callback(Result.failure(exception))
      }
  }

  fun existCollection(callback: (result: Result<Boolean>) -> Unit) {
    firebase.getReference(COLECCION_ROL).get()
      .addOnSuccessListener {
        callback(Result.success(it.exists()))
      }
      .addOnFailureListener { exception ->
        callback(Result.failure(exception))
      }
  }

  fun getAdminRol (callback: (Rol) -> Unit) {
    firebase.getReference(COLECCION_ROL).orderByChild("nombreRol").equalTo("Administrador").get()
      .addOnSuccessListener {
        val rol = it.children.mapNotNull { it.getValue(Rol::class.java) }.firstOrNull()
        if (rol != null) {
          callback(rol)
        } else {
          throw Exception("Rol no encontrado")
        }
      }
      .addOnFailureListener {
        throw Exception("Error al obtener el rol")
      }
  }

  fun getClientRol(callback: (result: Result<Rol?>) -> Unit) {

    
    firebase.getReference(COLECCION_ROL).get().addOnSuccessListener { snapshot ->
            val roles = snapshot.children.mapNotNull { it.getValue(Rol::class.java) }

            // Encontramos el primer rol cuyo idRol sea "Cliente"
            val clienteRol: Rol? = roles.firstOrNull { it.nombreRol == "Cliente" }

            if (clienteRol != null) {
                // Aquí puedes usar el rol encontrado
                callback(Result.success(clienteRol))
            } else {
                // Manejo cuando no se encuentra el rol "Cliente"
                callback(Result.failure(Exception("Rol no encontrado")))
            }
        }.addOnFailureListener {
            // Manejo de error en caso de que falle la obtención de los roles
            callback(Result.failure(it))
        }
  }


}

package com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Data.Services

import com.google.firebase.database.FirebaseDatabase
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models.Boletines
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class BoletinService @Inject constructor(private val firebase: FirebaseDatabase)  {
    companion object {
        const val COLECCION_BOLETIN = "Boletines"
    }

    fun getBoletinById(idBoletin: String, callback: (Boletines) -> Unit) {
        firebase.getReference(COLECCION_BOLETIN).child(idBoletin).get()
            .addOnSuccessListener {
                val boletin = it.getValue(Boletines::class.java)
                if (boletin != null) {
                    callback(boletin)
                } else {
                    throw Exception("Error al obtener el boletin")
                }
            }
            .addOnFailureListener { exception ->
                throw Exception("Error al obtener el boletin: $exception")
            }
    }


    fun getBoletines(callback: (List<Boletines>) -> Unit) {
        firebase.getReference(COLECCION_BOLETIN).get()
            .addOnSuccessListener {
                val boletines = it.getValue(Boletines::class.java)
                if (boletines != null) {
                    callback(listOf(boletines))
                } else {
                    throw Exception("Error al obtener el boletines")
                }
            }
            .addOnFailureListener { exception ->
                throw Exception("Error al obtener el boletines: $exception")
            }
    }

    fun createBoletin(boletin: Boletines, callback: (Boolean) -> Unit) {
        firebase.getReference(COLECCION_BOLETIN).child(boletin.idBoletin).setValue(boletin)
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener { exception ->
                throw Exception("Error al crear el boletin: $exception")
            }
    }

    fun updateBoletin(boletin: Boletines, callback: (Boolean) -> Unit) {
        firebase.getReference(COLECCION_BOLETIN).child(boletin.idBoletin).setValue(boletin)
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener { exception ->
                throw Exception("Error al actualizar el boletin: $exception")
            }
    }

    fun deleteBoletin(idBoletin: String, callback: (Boolean) -> Unit) {
        firebase.getReference(COLECCION_BOLETIN).child(idBoletin).removeValue()
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener { exception ->
                throw Exception("Error al eliminar el boletin: $exception")
            }
    }

    fun existCollection(callback: (Boolean) -> Unit) {
        firebase.getReference(COLECCION_BOLETIN).get()
            .addOnSuccessListener {
                callback(it.exists())
            }
            .addOnFailureListener { exception ->
                throw Exception("Error al obtener los boletines: $exception")
            }
    }
}
package com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Data.Services

import com.google.firebase.database.FirebaseDatabase
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models.SoporteBoletos
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SoporteBoletoService  @Inject constructor(private val firebase: FirebaseDatabase) {
    companion object {
        const val COLECCION_SOPORTE_BOLETO = "SoporteBoleto"
    }

    fun getSoporteBoletoById(idSoporteBoleto: String, callback: (SoporteBoletos) -> Unit) {
        firebase.getReference(COLECCION_SOPORTE_BOLETO).child(idSoporteBoleto).get()
            .addOnSuccessListener {
                val soporteBoleto = it.getValue(SoporteBoletos::class.java)
                if (soporteBoleto != null) {
                    callback(soporteBoleto)
                } else {
                    throw Exception("Error al obtener el soporteBoleto")
                }
            }
            .addOnFailureListener { exception ->
                throw Exception("Error al obtener el soporteBoleto: $exception")
            }
    }

    fun getSoporteBoletos( callback: (List<SoporteBoletos>) -> Unit) {
        firebase.getReference(COLECCION_SOPORTE_BOLETO).get()
            .addOnSuccessListener {
                val soporteBoletos = it.getValue(SoporteBoletos::class.java)
                if (soporteBoletos != null) {
                    callback(listOf(soporteBoletos))
                } else {
                    throw Exception("Error al obtener el soporteBoletos")
                }
            }
            .addOnFailureListener { exception ->
                throw Exception("Error al obtener el soporteBoletos: $exception")
            }
    }


    fun createSoporteBoleto(soporteBoleto: SoporteBoletos, callback: (Boolean) -> Unit) {
        firebase.getReference(COLECCION_SOPORTE_BOLETO).child(soporteBoleto.idSoporteBoletos).setValue(soporteBoleto)
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener { exception ->
                throw Exception("Error al crear el soporteBoleto: $exception")
            }
    }

    fun updateSoporteBoleto(soporteBoleto: SoporteBoletos, callback: (Boolean) -> Unit) {
        firebase.getReference(COLECCION_SOPORTE_BOLETO).child(soporteBoleto.idSoporteBoletos).setValue(soporteBoleto)
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener { exception ->
                throw Exception("Error al actualizar el soporteBoleto: $exception")
            }
    }

    fun deleteSoporteBoleto(idSoporteBoleto: String, callback: (Boolean) -> Unit) {
        firebase.getReference(COLECCION_SOPORTE_BOLETO).child(idSoporteBoleto).removeValue()
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener { exception ->
                throw Exception("Error al eliminar el soporteBoleto: $exception")
            }
    }

    fun existCollection( callback: (Boolean) -> Unit) {
        firebase.getReference(COLECCION_SOPORTE_BOLETO).get()
            .addOnSuccessListener {
                callback(it.exists())
            }
    }
}
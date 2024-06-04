package com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Data.Services

import com.google.firebase.database.FirebaseDatabase
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models.Hoteles
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models.CalificacionesHotel
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models.Habitaciones
import javax.inject.Inject

class HotelService @Inject constructor(private val firebase: FirebaseDatabase) {
  companion object {
    const val COLECCION_ROL = "Hoteles"
  }

  fun getHotelById(idHotel: String, callback: (result: Result<Hoteles?>) -> Unit) {
    firebase.getReference(COLECCION_ROL).child(idHotel).get()
      .addOnSuccessListener { dataSnapshot ->
        val hotel = dataSnapshot.getValue(Hoteles::class.java)
        callback(Result.success(hotel))
      }
      .addOnFailureListener { exception ->
        callback(Result.failure(exception))
      }
  }

  fun createHotel(hote: Hoteles, callback: (result: Result<Hoteles?>) -> Unit) {
    firebase.getReference(COLECCION_ROL).child(hote.idHotel).setValue(hote)
      .addOnSuccessListener {
        callback(Result.success(hote))
      }
      .addOnFailureListener { exception ->
        callback(Result.failure(exception))
      }
  }

  fun updateHotel(hote: Hoteles, callback: (result: Result<Hoteles?>) -> Unit) {
    firebase.getReference(COLECCION_ROL).child(hote.idHotel).setValue(hote)
      .addOnSuccessListener {
        callback(Result.success(hote))
      }
      .addOnFailureListener { exception ->
        callback(Result.failure(exception))
      }
  }

  fun setCalificacionHotel(idHotel: String, calificacion: CalificacionesHotel, callback: (result: Result<CalificacionesHotel?>) -> Unit) {
    firebase.getReference(COLECCION_ROL).child(idHotel).child("calificaciones").setValue(calificacion)
      .addOnSuccessListener {
        callback(Result.success(calificacion))
      }
      .addOnFailureListener { exception ->
        callback(Result.failure(exception))
      }
  }

  fun setHabitacionHotel(idHotel: String, habitacion: Habitaciones, callback: (result: Result<Habitaciones?>) -> Unit) {
    firebase.getReference(COLECCION_ROL).child(idHotel).child("habitaciones").setValue(habitacion)
      .addOnSuccessListener {
        callback(Result.success(habitacion))
      }
      .addOnFailureListener { exception ->
        callback(Result.failure(exception))
      }
  }

  fun getHabitacionesHotel(idHotel: String, callback: (result: Result<List<Habitaciones>>) -> Unit) {
    firebase.getReference(COLECCION_ROL).child(idHotel).child("habitaciones").get()
      .addOnSuccessListener { dataSnapshot ->
        val habitaciones = dataSnapshot.children.mapNotNull { it.getValue(Habitaciones::class.java) }
        callback(Result.success(habitaciones))
      }
      .addOnFailureListener { exception ->
        callback(Result.failure(exception))
      }
  }

  fun deleteHotel(idHotel: String, callback: (result: Result<Boolean>) -> Unit) {
    firebase.getReference(COLECCION_ROL).child(idHotel).removeValue()
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
  

}
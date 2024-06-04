package com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Habitaciones(
    val idHabitacion: String,
    val nombreHabitacion: String,
    val descripcionHabitacion: String,
    val numCamas: Int,
    val numBanios: Int,
    val personasMaximo: Int,
    val precio: Double,
    val calificaiones: List<CalificacionesHabitacion>,
    val thumbnail: String,
    val imagenes: List<String>
):Parcelable
package com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hoteles(
    val idHotel: String,
    val nombreHotel: String,
    val direccionHotel: String,
    val descripcionHotel: String,
    val telefonoHotel: String,
    val thumbnail: String,
    val calificaciones: List<CalificacionesHotel>,
    val habitaciones: List<Habitaciones>
):Parcelable

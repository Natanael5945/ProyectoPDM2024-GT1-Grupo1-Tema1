package com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Actividades(
    val idActividad: Int,
    val nombreActividad: String,
    val tipoActividad: String,
    val descripcionActividad: String,
    val precioActividad: Float
):Parcelable

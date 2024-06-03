package com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Habitaciones(
    val idHabitacion: Int,
    val numCamas: Int,
    val numBanios: Int,
    val personasMaximo: Int

):Parcelable
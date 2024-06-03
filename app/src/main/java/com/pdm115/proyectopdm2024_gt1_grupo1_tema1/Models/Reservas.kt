package com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Reservas(
    val idReserva: Int,
    val fechaInicio: Date,
    val fechaFin: Date,
    val estadoReserva: String
):Parcelable

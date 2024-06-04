package com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SoporteBoletos(
    val idSoporteBoletos: String,
    val estadoBoleto: String?,
    val descripcionBoleto: String?,
    val usuario: String?
): Parcelable {
    constructor(): this("", null, null, null)
}

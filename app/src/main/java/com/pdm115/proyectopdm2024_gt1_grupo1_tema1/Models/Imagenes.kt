package com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Imagenes(
    val idImagen: Int,
    val originalName: String,
    val enconding: String,
    val fileNameval: String,
    val path: String,
    val size: Int,
    val mimeType: String,
    val destination: String
):Parcelable

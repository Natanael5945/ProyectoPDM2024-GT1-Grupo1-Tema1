package com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Usuario(
    // idUsuario, DUI, nombreCompleto, nombreUsuario, correoUsuario, clave, rol, avatarPath
    val isUsuario: String,
    val nombreCompleto: String,
    val nombreUsuario: String,
    val correoUsuario: String,
    val clave: String,
    val fechaNacimiento: String,
    val rol: Rol,
    val avatarPath: String
) : Parcelable

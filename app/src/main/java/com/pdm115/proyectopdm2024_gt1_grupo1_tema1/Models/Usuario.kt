package com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Usuario(
    // idUsuario, DUI, nombreCompleto, nombreUsuario, correoUsuario, clave, rol, avatarPath
    val idUsuario: String,
    var nombreCompleto: String,
    var nombreUsuario: String,
    var correoUsuario: String,
    val clave: String,
    var fechaNacimiento: String,
    val rol: Rol?,
    var dui: String?,
    val avatarPath: String?
) : Parcelable {
    constructor(): this(UUID.randomUUID().toString(), "", "", "","", "", null, "", "")
}

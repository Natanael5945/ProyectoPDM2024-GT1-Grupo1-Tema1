package com.pdm115.proyectopdm2024_gt1_grupo1_tema1

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Data.Services.UsuarioService
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models.Usuario

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditarInformacionPersonal.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditarInformacionPersonal : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var auth: FirebaseAuth
    private lateinit var usuarioService: UsuarioService
    private lateinit var usuario: Usuario
    private lateinit var storage: FirebaseStorage

    private lateinit var nombreCompleto: EditText
    private lateinit var nombreUsuario: EditText
    private lateinit var correoUsuario: EditText
    private lateinit var fechaNacimiento: EditText
    private lateinit var duiUsuario: EditText
    private lateinit var avatarPath: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        auth = FirebaseAuth.getInstance()
        storage = FirebaseStorage.getInstance()
        val currentUser = auth.currentUser
        usuarioService = UsuarioService(FirebaseDatabase.getInstance())

        if (currentUser != null) {
            currentUser.email?.let {
                usuarioService.getUsuarioByCorreo(it) {
                    usuario = it
                    bindUserEditText()
                    attachButton()
                }
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editar_informacion_personal, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EditarInformacionPersonal.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditarInformacionPersonal().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun subirImagen(uri: Uri) {
        // Verifica si el usuario tiene un ID asignado
        if (usuario.idUsuario != null) {
            // Obtén la referencia al lugar donde se guardará la imagen en Firebase Storage
            val ref = storage.reference.child("images/${usuario.idUsuario}")

            // Sube el archivo a Firebase Storage usando la URI
            ref.putFile(uri)
                .addOnSuccessListener {
                    Toast.makeText(context, "Imagen subida exitosamente", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(context, "Error al subir la imagen: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(context, "Usuario no tiene ID asignado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun guardarDatosUsuario ()
    {
        if (validarDatos())
        {
            usuario.nombreCompleto = nombreCompleto.text.toString()
            usuario.nombreUsuario = nombreUsuario.text.toString()
            usuario.correoUsuario = correoUsuario.text.toString()
            usuario.fechaNacimiento = fechaNacimiento.text.toString()
            usuario.dui = duiUsuario.text.toString()
            usuarioService.updateUsuario(usuario) {
                if (it) {
                    Toast.makeText(context, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Error al actualizar los datos", Toast.LENGTH_SHORT).show()
                }
            }
        }
        else
        {
            Toast.makeText(context, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun attachButton ()
    {
        val guardarDatos: Button = requireView().findViewById(R.id.btn_save_nueva_contra)
        guardarDatos.setOnClickListener {
            guardarDatosUsuario()
        }
    }

    private fun validarDatos (): Boolean
    {
        return nombreCompleto.text.isNotEmpty() && nombreUsuario.text.isNotEmpty() && correoUsuario.text.isNotEmpty() && fechaNacimiento.text.isNotEmpty()
    }

    private fun bindUserEditText()
    {
        nombreCompleto = requireView().findViewById(R.id.edtxt_nombre_completo_editar_infop)
        nombreUsuario = requireView().findViewById(R.id.edtxt_nombre_usuario_editar_infop)
        correoUsuario = requireView().findViewById(R.id.edtxt_correo_editar_infop)
        fechaNacimiento = requireView().findViewById(R.id.edtxt_fecha_nacimiento_editar_infop)
        avatarPath = requireView().findViewById(R.id.imageView)
        duiUsuario = requireView().findViewById(R.id.edtxt_dui)

        nombreCompleto.setText(usuario.nombreCompleto)
        nombreUsuario.setText(usuario.nombreUsuario)
        correoUsuario.setText(usuario.correoUsuario)
        fechaNacimiento.setText(usuario.fechaNacimiento)
        duiUsuario.setText(usuario.dui)
    }
}

package com.pdm115.proyectopdm2024_gt1_grupo1_tema1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NuevaContrasenia.newInstance] factory method to
 * create an instance of this fragment.
 */
class NuevaContrasenia : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var auth: FirebaseAuth

    private lateinit var currentPassword: EditText
    private lateinit var newPassword: EditText
    private lateinit var confirmPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        auth = FirebaseAuth.getInstance()
        
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Mueve tu código que accede a la vista aquí
        bindEditText()
        attachButton()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nueva_contrasenia, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NuevaContrasenia.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NuevaContrasenia().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun cambiarContra()
    {
        val user = auth.currentUser
        val credential = EmailAuthProvider.getCredential(user?.email!!, currentPassword.text.toString())

        user.reauthenticate(credential).addOnCompleteListener {
            if (it.isSuccessful)
            {
                user.updatePassword(newPassword.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful)
                    {
                        Toast.makeText(requireContext(), "Contraseña cambiada exitosamente", Toast.LENGTH_SHORT).show()
                        cleanEditText()
                    }
                    else
                    {
                        Toast.makeText(requireContext(), "Error al cambiar la contraseña", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else
            {
                Toast.makeText(requireContext(), "Error al autenticar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun verificarDatos ()
    {
        if (currentPassword.text.toString().isEmpty() || newPassword.text.toString().isEmpty() || confirmPassword.text.toString().isEmpty())
        {
            Toast.makeText(requireContext(), "Por favor llene todos los campos", Toast.LENGTH_SHORT).show()
        }
        else
        {
            if (newPassword.text.toString() != confirmPassword.text.toString())
            {
                Toast.makeText(requireContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
            else
            {
                cambiarContra()
            }
        }
    }

    private fun attachButton ()
    {
        val cambiarContraButton: Button = requireView().findViewById(R.id.btn_save_nueva_contra)
        cambiarContraButton.setOnClickListener{
            verificarDatos()
        }
    }
    private fun bindEditText(){
        currentPassword = requireView().findViewById(R.id.editTextTextPassword)
        newPassword = requireView().findViewById(R.id.editTextTextPassword2)
        confirmPassword = requireView().findViewById(R.id.editTextTextPassword3)
    }

    private fun cleanEditText(){
        currentPassword.text.clear()
        newPassword.text.clear()
        confirmPassword.text.clear()
    }
}
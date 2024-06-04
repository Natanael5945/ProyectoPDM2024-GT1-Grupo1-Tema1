package com.pdm115.proyectopdm2024_gt1_grupo1_tema1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.commit
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Configuraciones.newInstance] factory method to
 * create an instance of this fragment.
 */
class Configuraciones : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //  return inflater.inflate(R.layout.fragment_configuraciones, container, false)
        val view = inflater.inflate(R.layout.fragment_configuraciones, container, false)
        val infoPersonalBoton : Button = view.findViewById(R.id.btn_info_personal_configuraciones)
        val cambiarContraBoton : Button = view.findViewById(R.id.btn_cambiar_contra_configuraciones)
        val facturasBoton : Button = view.findViewById(R.id.btn_facturacion_configuraciones)
        val cerrarSesionButton: Button = view.findViewById(R.id.btn_cerrar_sesion_configuraciones)
        
        infoPersonalBoton.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.frame_contenedor, EditarInformacionPersonal())
                addToBackStack(null)
            }
        }

        cambiarContraBoton.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.frame_contenedor, NuevaContrasenia())
                addToBackStack(null)
            }
        }

        facturasBoton.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.frame_contenedor, UltimasReservas())
                addToBackStack(null)
            }
        }

        cerrarSesionButton.setOnClickListener {
            cerrarSesion()
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Configuraciones.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Configuraciones().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun cerrarSesion() {
        auth.signOut()
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
    }
}
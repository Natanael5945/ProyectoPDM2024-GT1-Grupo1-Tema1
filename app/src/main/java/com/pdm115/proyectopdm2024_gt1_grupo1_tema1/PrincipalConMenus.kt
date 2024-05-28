package com.pdm115.proyectopdm2024_gt1_grupo1_tema1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.bottomnavigation.BottomNavigationView

class PrincipalConMenus : AppCompatActivity() {
    lateinit var menuNavegacion:BottomNavigationView

    private val mOnNavegacionMenu = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when(item.itemId){
            R.id.item_habitaciones ->{
                cambioDeFragmento(Habitaciones())
                return@OnNavigationItemSelectedListener true
            }

            R.id.item_hoteles ->{
                cambioDeFragmento(Hoteles())
                return@OnNavigationItemSelectedListener true
            }

            R.id.item_reservas_activas ->{
                cambioDeFragmento(ReservasActivas())
                return@OnNavigationItemSelectedListener true
            }

            R.id.item_configuracion ->{
                cambioDeFragmento(Configuraciones())
                return@OnNavigationItemSelectedListener true
            }

        }

        false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_principal_con_menus)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        menuNavegacion = findViewById(R.id.menu_inferior)
        menuNavegacion.setOnNavigationItemSelectedListener(mOnNavegacionMenu)
        //Fragmento con el cual se mostrara en la vista sino se pone no sale nada al comienzo hasta dar clic
        cambioDeFragmento(Habitaciones())



    }

    private fun FragmentActivity.cambioDeFragmento(fragmento: Fragment){

        supportFragmentManager.commit {
            replace(R.id.frame_contenedor, fragmento)
            setReorderingAllowed(true)
            addToBackStack(null)
        }

        /*
        //Versión larga implementar en dado caso no funcione la otra
        supportFragmentManager.commit {
            replace<Habitaciones>(R.id.frame_contenedor)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        */

    }
}
package com.pdm115.proyectopdm2024_gt1_grupo1_tema1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Data.Services.AuthService
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Data.Services.BoletinService
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Data.Services.HotelService
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Data.Services.RolService
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Data.Services.SoporteBoletoService
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Data.Services.UsuarioService
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models.Boletines
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models.CalificacionesHabitacion
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models.CalificacionesHotel
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models.Habitaciones
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models.Usuario
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models.Habitaciones as HabitacionModel
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Models.Hoteles as HotelModel
import java.util.UUID

class MainActivity : AppCompatActivity() {

    private lateinit var rolService: RolService
    private lateinit var usuarioService: UsuarioService
    private lateinit var authService: AuthService
    private lateinit var soporteBoletoService: SoporteBoletoService
    private lateinit var boletinService: BoletinService
    private lateinit var hotelService: HotelService

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            goPrincipalMenu()
        }


        rolService = RolService(FirebaseDatabase.getInstance())
        usuarioService = UsuarioService(FirebaseDatabase.getInstance())
        soporteBoletoService = SoporteBoletoService(FirebaseDatabase.getInstance())
        boletinService = BoletinService(FirebaseDatabase.getInstance())
        hotelService = HotelService(FirebaseDatabase.getInstance())

        val btnLogin:Button = findViewById(R.id.btn_iniciar_sesion)
        val btnRegistrarse:Button = findViewById(R.id.btn_registrarse)

        btnLogin.setOnClickListener {
            val intent = Intent(this, IniciarSesion::class.java)
            startActivity(intent)
        }

        btnRegistrarse.setOnClickListener {
            val intent2 = Intent(this, RegistroParteUno::class.java)
            startActivity(intent2)
        }

//        var listCalHotel: List<CalificacionesHotel>
//        var listCalHabL: List<CalificacionesHabitacion>
//        var listHabitaciones: List<HabitacionModel>
//
//        val calificacionHotel1 = CalificacionesHotel(UUID.randomUUID().toString(), 5)
//        val calificacionHabitacion1 = CalificacionesHabitacion(UUID.randomUUID().toString(), 5)
//
//        listCalHabL = listOf(calificacionHabitacion1)
//        listCalHotel = listOf(calificacionHotel1)
//
//
//        val habitacionHotel1 = HabitacionModel(
//            UUID.randomUUID().toString(),
//            "Suite Marina",
//            "Una lujosa suite con una impresionante vista panorámica del océano, decorada con un estilo moderno y elegante.",
//            2,
//            1,
//            2,
//            100.0,
//            listCalHabL,
//            "https://i.ibb.co/VV9DzFs/habitacion-dos.png",
//            listOf("https://i.ibb.co/YXQNWN0/habitacion-cinco.png","https://i.ibb.co/vdr3X9R/habitacion-cuatro.png", "https://i.ibb.co/CnX8Krn/habitacion-tres.png")
//        )
//
//        val habitacionHotel2 = HabitacionModel(
//            UUID.randomUUID().toString(),
//            "Refugio del Atardecer",
//            "Disfruta del confort y la calma en esta habitación que ofrece vistas inolvidables de los atardeceres sobre el mar.",
//            2,
//            1,
//            2,
//            100.0,
//            listCalHabL,
//            "https://i.ibb.co/vdr3X9R/habitacion-cuatro.png",
//            listOf("https://i.ibb.co/YXQNWN0/habitacion-cinco.png", "https://i.ibb.co/CnX8Krn/habitacion-tres.png", "https://i.ibb.co/VV9DzFs/habitacion-dos.png")
//        )
//
//        val nuevoHotel = HotelModel(
//            UUID.randomUUID().toString(),
//            "El Oasis del Mar",
//            "Avenida del Coral, 348, Playa Esmeralda, Cabañas",
//            "Descubre la magia de alojarte frente al mar en 'El Oasis del Mar', un lugar donde cada detalle está pensado para tu relax y disfrute.",
//            "12345678",
//            "https://i.ibb.co/YXQNWN0/habitacion-cinco.png",
//            listOf(calificacionHotel1),
//            listOf(habitacionHotel1, habitacionHotel2)
//        )
//
//        hotelService.existCollection {
//            it.onSuccess {
//                if (it) {
//                    Toast.makeText(this, "La colección de hoteles existe", Toast.LENGTH_SHORT).show()
//                    hotelService.createHotel(nuevoHotel) {
//                        it.onSuccess {
//                            Toast.makeText(this, "Hotel creado", Toast.LENGTH_SHORT).show()
//                        }.onFailure {
//                            Toast.makeText(this, "Error al crear el hotel", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                } else {
//                    Toast.makeText(this, "La colección de hoteles no existe", Toast.LENGTH_SHORT).show()
//                    hotelService.createHotel(nuevoHotel) {
//                        it.onSuccess {
//                            Toast.makeText(this, "Hotel creado", Toast.LENGTH_SHORT).show()
//                        }.onFailure {
//                            Toast.makeText(this, "Error al crear el hotel", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//            }.onFailure {
//                Toast.makeText(this, "Error al verificar la existencia de la colección de hoteles", Toast.LENGTH_SHORT).show()
//            }
//
//        }

//        boletinService.existCollection {
//            if (it) {
//                Toast.makeText(this, "La colección de boletines existe", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(this, "La colección de boletines no existe", Toast.LENGTH_SHORT).show()
//                val newBoletin: Boletines = Boletines(
//                    UUID.randomUUID().toString(),
//                    "heac.creative@gmail.com",
//                )
//
//                boletinService.createBoletin(newBoletin) {
//                    if (it) {
//                        Toast.makeText(this, "Boletín creado", Toast.LENGTH_SHORT).show()
//                    } else {
//                        Toast.makeText(this, "Error al crear el boletín", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        }
//


        

    }

    private fun goPrincipalMenu()
    {
        val intent: Intent = Intent(this, PrincipalConMenus::class.java)
        startActivity(intent)
    }
}
package com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Data.Services

import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Data.FirebaseClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthService @Inject constructor(private val firebase: FirebaseClient) {

    fun login(email: String, password: String, callback: (Boolean) -> Unit) {
        firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                callback(it.isSuccessful)
            }
    }
    fun createAccount(email: String, password: String, callback:  (Boolean) -> Unit) {
        firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                callback(it.isSuccessful)
            }.addOnFailureListener {
                callback(false)
            }
    }
    fun sendVerificationEmail(callback: (Boolean) -> Unit) {
        firebase.auth.currentUser?.sendEmailVerification()
            ?.addOnCompleteListener {
                callback(it.isSuccessful)
            }
    }

    fun logout() {
        firebase.auth.signOut()
    }


}
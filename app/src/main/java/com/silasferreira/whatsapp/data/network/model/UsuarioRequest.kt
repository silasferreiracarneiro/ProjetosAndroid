package com.silasferreira.whatsapp.data.network.model

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.google.firebase.database.DatabaseReference
import com.silasferreira.whatsapp.data.network.FirebaseModule.Companion.authFirebase
import com.silasferreira.whatsapp.data.network.FirebaseModule.Companion.database
import com.silasferreira.whatsapp.data.network.repository.UsuarioRepository
import com.silasferreira.whatsapp.model.Usuario
import java.lang.Exception

class UsuarioRequest: UsuarioRepository {

    private val auth: FirebaseAuth = authFirebase()
    private val reference: DatabaseReference = database()

    override fun savedUser(user: Usuario): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(user.email, user.senha)
    }

    override fun signIn(user: Usuario): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(user.email, user.senha)
    }

    override fun loggedIn(): FirebaseUser? {
        return auth.currentUser
    }

    override fun logout() {
        auth.signOut()
    }

    override fun createUserInDatabase(user: Usuario) {
        try {
            var idUser = user.uid
            var ref = reference.child("usuarios").child(idUser)
            ref.setValue(user)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    override fun deleteUserInAuthentication(): Task<Void>? {
        return auth.currentUser?.delete()
    }
}
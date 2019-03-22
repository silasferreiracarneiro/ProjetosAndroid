package com.silasferreira.whatsapp.data.network.model

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.google.firebase.database.DatabaseReference
import com.silasferreira.whatsapp.data.network.FirebaseModule.Companion.authFirebase
import com.silasferreira.whatsapp.data.network.FirebaseModule.Companion.database
import com.silasferreira.whatsapp.data.network.repository.UsuarioRepository
import com.silasferreira.whatsapp.data.prefs.PreferencesHelper
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.utils.AppConstants.Companion.PATH_FOTO
import com.silasferreira.whatsapp.utils.AppConstants.Companion.PATH_USER
import com.silasferreira.whatsapp.utils.Base64Utils
import java.lang.Exception
import javax.inject.Inject

class UsuarioRequest @Inject constructor(var prefHelter: PreferencesHelper): UsuarioRepository {

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
            var ref = reference.child(PATH_USER).child(idUser)
            ref.setValue(user)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    override fun deleteUserInAuthentication(): Task<Void>? {
        return loggedIn()?.delete()
    }

    override fun savedImageUser(image: ByteArray) {

        try {
            var user = this.loggedIn()
            if(user != null){
                var ref = reference.child(PATH_USER).child(Base64Utils.encode(user.email)).child(PATH_FOTO)
                ref.setValue(Base64Utils.encodeByte(image))
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    /*override fun updateUserPhoto(): DatabaseReference {
        var user = this.loggedIn()
        return reference.child(PATH_USER).child(Base64Utils.encode(user?.email))
    }*/

    override fun searchUserPhoto(): DatabaseReference {
        var user = this.loggedIn()
        return reference.child(PATH_USER).child(Base64Utils.encode(user?.email))
    }
}
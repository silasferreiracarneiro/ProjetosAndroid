package com.example.instagram.data.network.model

import com.example.instagram.data.firebase.ConfigFirebaseContract
import com.example.instagram.data.network.repository.UserRepository
import com.example.instagram.model.User
import com.example.instagram.utils.AppConstants.Companion.USER
import com.example.instagram.utils.Base64Utils
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

class UserRequest(var firebaseConfig: ConfigFirebaseContract): UserRepository {

    override fun createUserInAuthentication(user: User): Task<AuthResult> {
        return firebaseConfig.authFirebase().createUserWithEmailAndPassword(user.email, user.password)
    }

    override fun createUser(user: User) {
        firebaseConfig.database().child(USER).child(Base64Utils.encode(user.email)).setValue(user)
    }
}
package com.example.instagram.data.network.model

import com.example.instagram.data.firebase.ConfigFirebaseContract
import com.example.instagram.data.network.repository.UserRepository
import com.example.instagram.model.User
import com.example.instagram.utils.AppConstants.Companion.USER
import com.example.instagram.utils.AppConstants.Companion.USER_NAME
import com.example.instagram.utils.Base64Utils
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query

class UserRequest(var firebaseConfig: ConfigFirebaseContract): UserRepository {

    override fun signOut() {
        firebaseConfig.authFirebase().signOut()
    }

    override fun signInUser(user: User): Task<AuthResult> {
        return firebaseConfig.authFirebase().signInWithEmailAndPassword(user.email, user.password)
    }

    override fun createUserInAuthentication(user: User): Task<AuthResult> {
        return firebaseConfig.authFirebase().createUserWithEmailAndPassword(user.email, user.password)
    }

    override fun createUser(user: User) {
        firebaseConfig.database().child(USER).child(Base64Utils.encode(user.email)).setValue(user)
    }

    override fun loggedIn(): FirebaseUser? {
        return firebaseConfig.authFirebase().currentUser
    }

    override fun getUser(): DatabaseReference {
        return firebaseConfig.database().child(USER).child(Base64Utils.encode(loggedIn()?.email))
    }

    override fun searchUser(caracter: String): Query {
        return firebaseConfig.database().child(USER).orderByChild(USER_NAME)
            .startAt(caracter).endAt(caracter + "\uf8ff")
    }
}
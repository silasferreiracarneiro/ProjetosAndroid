package com.example.instagram.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.FirebaseStorage

interface ConfigFirebaseContract {

    fun database() : DatabaseReference
    fun authFirebase(): FirebaseAuth
    fun storage(): FirebaseStorage
}
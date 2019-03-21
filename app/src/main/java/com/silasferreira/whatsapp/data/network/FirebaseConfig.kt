package com.silasferreira.whatsapp.data.network

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

open class FirebaseModule {

    companion object {

        fun database() : DatabaseReference = FirebaseDatabase.getInstance().reference

        fun authFirebase(): FirebaseAuth = FirebaseAuth.getInstance()

        fun storage(): FirebaseStorage = FirebaseStorage.getInstance()
    }
}
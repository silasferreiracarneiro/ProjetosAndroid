package com.example.instagram.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class ConfigFirebase : ConfigFirebaseContract {

    override fun database(): DatabaseReference {
        return FirebaseDatabase.getInstance().reference
    }

    override fun authFirebase(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    override fun storage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }
}
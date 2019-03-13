package com.silasferreira.whatsapp.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@Singleton
class FirebaseModule {

    @Provides
    fun database() : DatabaseReference = FirebaseDatabase.getInstance().reference

    @Provides
    fun authFirebase(): FirebaseAuth = FirebaseAuth.getInstance()
}
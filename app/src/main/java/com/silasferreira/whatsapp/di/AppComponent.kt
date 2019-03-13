package com.silasferreira.whatsapp.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule :: class])
interface AppComponent {


}
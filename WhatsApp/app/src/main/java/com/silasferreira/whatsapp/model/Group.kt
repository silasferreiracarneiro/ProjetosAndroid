package com.silasferreira.whatsapp.model

import java.io.Serializable

class Group(
    var id: String = "",
    var name: String = "",
    var photo: String = "",
    var users: ArrayList<Usuario> = arrayListOf()
) : Serializable {
}
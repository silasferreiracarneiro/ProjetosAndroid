package com.example.instagram.model

import java.io.Serializable

class Posting (var id: String = "",
               var idUser: String = "",
               var description: String = "",
               var pathPhoto: String = "") : Serializable{
}
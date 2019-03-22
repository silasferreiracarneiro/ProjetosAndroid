package com.silasferreira.whatsapp.ui.home.conversation

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.ui.base.BasePresenter
import com.silasferreira.whatsapp.utils.Base64Utils
import javax.inject.Inject

class ConversationPresenter<V: ConversationContract.View, I: ConversationContract.Integractor>
    @Inject constructor(var conversationIntegractor: I): ConversationContract.Presenter<V, I>,
    BasePresenter<V, I>(conversationIntegractor){


    override fun onViewPrepared() {

        var listUser: ArrayList<Usuario> = arrayListOf()

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach {
                    var user = it.getValue(Usuario::class.java)!!
                    listUser.add(user)
                }
                getMvpView().setNewListUser(listUser)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                getMvpView().onError("Erro ao buscar as conversas!")
            }
        }

        this.conversationIntegractor.getListUser().addValueEventListener(postListener)
    }
}
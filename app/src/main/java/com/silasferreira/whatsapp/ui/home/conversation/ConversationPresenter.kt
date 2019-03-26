package com.silasferreira.whatsapp.ui.home.conversation

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.silasferreira.whatsapp.model.Conversation
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.ui.base.BasePresenter
import javax.inject.Inject

class ConversationPresenter<V: ConversationContract.View, I: ConversationContract.Integractor>
    @Inject constructor(var conversationIntegractor: I): ConversationContract.Presenter<V, I>,
    BasePresenter<V, I>(conversationIntegractor){

    var listConversation: ArrayList<Conversation> = arrayListOf()

    override fun onViewPrepared() {

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                listConversation = arrayListOf()

                dataSnapshot.children.forEach {
                    var conversation = it.getValue(Conversation::class.java)!!
                    listConversation.add(conversation)
                }
                getMvpView().setNewListUser(listConversation)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                getMvpView().onError("Erro ao buscar as conversas!")
            }
        }

        this.conversationIntegractor.getListConversation().addValueEventListener(postListener)
    }

    override fun getUserSelect(position: Int): Usuario {
        return listConversation[position].usuario
    }

    override fun searchConversation(text: String) {
        var result = arrayListOf<Conversation>()
         listConversation.forEach{
            if(it.usuario.nome.toLowerCase().contains(text.toLowerCase()) || it.lastMessage.toLowerCase().contains(text.toLowerCase())){
                result.add(it)
            }
        }
        getMvpView().setNewListUser(result)
    }
}
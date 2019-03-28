package com.silasferreira.whatsapp.ui.chat

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.silasferreira.whatsapp.model.Conversation
import com.silasferreira.whatsapp.model.Group
import com.silasferreira.whatsapp.model.MessageUser
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.ui.base.BasePresenter
import com.silasferreira.whatsapp.utils.Base64Utils
import javax.inject.Inject

class ChatPresenter<V: ChatContract.View, I: ChatContract.Interactor>
    @Inject constructor(var chatInteractor: I):
    BasePresenter<V, I>(chatInteractor), ChatContract.Presenter<V, I> {

    var messages: ArrayList<MessageUser> = arrayListOf()

    override fun sendMessage(message: MessageUser) {
        var user = chatInteractor.getCurrencyUser()
        message.senderUser = Base64Utils.encode(user?.email)
        chatInteractor.sendMessage(message)
        savedConversion(if(message.image != "") "Foto" else message.message)
    }

    override fun sendMessageGroup(message: MessageUser, group: Group) {

        group.users.forEach{
            message.recipientUser = group.id
            message.senderUser = Base64Utils.encode(it.email)
            chatInteractor.sendMessage(message)
            savedConversion(if(message.image != "") "Foto" else message.message)
        }
    }

    private fun savedConversion(message: String){
        var conversation  = Conversation(
            "",
            "",
            message,
            Usuario(),
            Group())

        if(getMvpView().getGroupChat() != null){
            conversation.groupConversation = true
            conversation.idRecipient = getMvpView().getGroupChat()?.id!!
            conversation.group = getMvpView().getGroupChat()!!
        }else{
            conversation.usuario = getMvpView().getUserChat()!!
            conversation.idRecipient = Base64Utils.encode(getMvpView().getUserChat()?.email)
        }
        this.saveConversation(conversation)
    }

    override fun saveConversation(conversation: Conversation) {
        var user = chatInteractor.getCurrencyUser()
        conversation.idSender = Base64Utils.encode(user?.email)
        chatInteractor.saveConversation(conversation)
    }

    override fun getMessages(idRecipient: String) {
        var user = chatInteractor.getCurrencyUser()

        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                messages = arrayListOf()

                dataSnapshot.children.forEach {
                    var message = it.getValue(MessageUser::class.java)!!
                    messages.add(message)
                }
                getMvpView().setNewListMessage(messages)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                getMvpView().onError("Erro ao buscar os contatos!")
            }
        }

        chatInteractor.getMessages(idRecipient, Base64Utils.encode(user?.email))
            .addValueEventListener(listener)
    }
}
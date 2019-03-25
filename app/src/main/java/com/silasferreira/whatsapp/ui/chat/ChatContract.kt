package com.silasferreira.whatsapp.ui.chat

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.silasferreira.whatsapp.model.Conversation
import com.silasferreira.whatsapp.model.MessageUser
import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface ChatContract {

    interface View : MvpView{
        fun setNewListMessage(messages: ArrayList<MessageUser>)
        fun saveMessage(message: String)
    }

    interface Interactor : MvpInteractor{
        fun sendMessage(message: MessageUser)
        fun saveConversation(conversation: Conversation)
        fun getCurrencyUser(): FirebaseUser?
        fun getMessages(idRecipient: String, idSender: String): DatabaseReference
    }

    interface Presenter<V: ChatContract.View, I: ChatContract.Interactor>: MvpPresenter<V, I>{
        fun sendMessage(message: MessageUser)
        fun getMessages(idRecipient: String)
        fun saveConversation(conversation: Conversation)
    }
}
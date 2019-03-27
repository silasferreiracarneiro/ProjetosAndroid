package com.silasferreira.whatsapp.ui.home.conversation

import com.google.firebase.database.DatabaseReference
import com.silasferreira.whatsapp.model.Conversation
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface ConversationContract {

    interface View: MvpView{
        fun setNewListUser(list: ArrayList<Conversation>)
    }

    interface Presenter<V: ConversationContract.View, I: ConversationContract.Integractor> : MvpPresenter<V, I> {
        fun onViewPrepared()
        fun getUserSelect(position: Int): Conversation
        fun searchConversation(text: String)
    }

    interface Integractor : MvpInteractor{
        fun getListConversation(): DatabaseReference
    }
}
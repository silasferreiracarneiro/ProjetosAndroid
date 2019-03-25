package com.silasferreira.whatsapp.ui.chat

import com.silasferreira.whatsapp.model.MessageUser
import com.silasferreira.whatsapp.ui.base.BasePresenter
import com.silasferreira.whatsapp.utils.Base64Utils
import javax.inject.Inject

class ChatPresenter<V: ChatContract.View, I: ChatContract.Interactor>
    @Inject constructor(var chatInteractor: I):
    BasePresenter<V, I>(chatInteractor), ChatContract.Presenter<V, I> {


    override fun sendMessage(message: MessageUser) {
        var user = chatInteractor.getCurrencyUser()
        message.senderUser = Base64Utils.encode(user?.email)
        chatInteractor.sendMessage(message)
    }
}
package com.silasferreira.whatsapp.ui.chat

import com.silasferreira.whatsapp.ui.base.BasePresenter
import javax.inject.Inject

class ChatPresenter<V: ChatContract.View, I: ChatContract.Interactor>
    @Inject constructor(chatInteractor: I):
    BasePresenter<V, I>(chatInteractor), ChatContract.Presenter<V, I> {


}
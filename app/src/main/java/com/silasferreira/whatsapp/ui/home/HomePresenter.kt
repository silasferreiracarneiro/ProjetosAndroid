package com.silasferreira.whatsapp.ui.home

import com.silasferreira.whatsapp.ui.base.BasePresenter
import com.silasferreira.whatsapp.ui.home.conversation.ConversationContract
import javax.inject.Inject

class HomePresenter<V: HomeContract.View, I: HomeContract.Interactor>
    @Inject constructor(var homeInteractor: I,
                        var conversationPresenter: ConversationContract.Presenter<ConversationContract.View, ConversationContract.Integractor>):
    BasePresenter<V, I>(homeInteractor), HomeContract.Presenter<V, I>{

    override fun logout() {
        homeInteractor.logout()
        getMvpView().onFinish()
    }

    override fun searchListConversation(text: String) {
        conversationPresenter.searchConversation(text)
    }
}
package com.example.instagram.ui.register

import com.example.instagram.ui.base.BasePresenter
import javax.inject.Inject

class RegisterPresenter<V: RegisterContract.View, I: RegisterContract.Interactor>
    @Inject constructor(var interactorRegister: I):
    BasePresenter<V, I>(interactorRegister), RegisterContract.Presenter<V, I> {
}
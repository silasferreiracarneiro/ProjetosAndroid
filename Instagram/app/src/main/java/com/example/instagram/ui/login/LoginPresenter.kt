package com.example.instagram.ui.login

import com.example.instagram.R
import com.example.instagram.data.prefs.PreferencesHelper
import com.example.instagram.model.User
import com.example.instagram.ui.base.BasePresenter
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import javax.inject.Inject

class LoginPresenter<V: LoginContract.View, I: LoginContract.Interactor>
    @Inject constructor(var interactorLogin: I, var prefHelter: PreferencesHelper):
    BasePresenter<V, I>(interactorLogin), LoginContract.Presenter<V, I> {

    override fun signInUser(user: User) {
        if(user.email == "" || user.password == ""){
            getMvpView().onError(R.string.enter_the_data)
        }else{
            getMvpView().showLoading()
            interactorLogin.signInUser(user).addOnCompleteListener{
                if(it.isSuccessful){
                    getMvpView().goToHome()
                }else{
                    getMvpView().hideLoading()
                    var message = when (it.exception){
                        is FirebaseAuthInvalidCredentialsException -> "Usuário não cadastrado!"
                        is FirebaseAuthInvalidUserException -> "Usuário inválido!"
                        is FirebaseNetworkException -> "Erro de rede, tente mais tarde!"
                        else -> "Erro ao logar!"
                    }
                    it.exception?.printStackTrace()
                    getMvpView().showMessage(message)
                    getMvpView().hideLoading()
                }
            }.addOnFailureListener{
                getMvpView().onError(R.string.some_error)
                getMvpView().hideLoading()
            }
        }
    }

    override fun loggedIn() {
        if(interactorLogin.loggedIn() != null){
            getMvpView().goToHome()
        }
    }
}
package com.silasferreira.whatsapp.ui.login

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.FirebaseNetworkException
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.ui.base.BasePresenter
import javax.inject.Inject
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.silasferreira.whatsapp.data.prefs.PreferencesHelper
import com.silasferreira.whatsapp.utils.Base64Utils.encode


class LoginPresenter<V: LoginContract.View, I: LoginContract.Interactor>
    @Inject constructor(var contractInteractor: I, var prefHelter: PreferencesHelper):
    BasePresenter<V, I>(contractInteractor), LoginContract.Presenter<V, I> {

    override fun signIn(user: Usuario) {
        contractInteractor.signIn(user)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    prefHelter.setUserId(encode(user.email))
                    prefHelter.setNameUser(user.nome)
                    getMvpView().goHome()
                }else{

                    var message = when (it.exception){
                        is FirebaseAuthInvalidCredentialsException -> "Usuário não cadastrado!"
                        is FirebaseAuthInvalidUserException -> "Usuário inválido!"
                        is FirebaseNetworkException -> "Erro de rede, tente mais tarde!"
                        else -> "Erro ao logar!"
                    }
                    it.exception?.printStackTrace()
                    getMvpView().showMessage(message)
                }
            }.addOnFailureListener(OnFailureListener {
            it.printStackTrace()
            getMvpView().onError("Erro ao logar!")
        })
    }

    override fun loggedIn() {
        var user = contractInteractor.loggedIn()
        if(user != null){
            prefHelter.setUserId(encode(user.email))
            //prefHelter.setNameUser(user?.displayName!!)
            getMvpView().goHome()
        }
    }
}
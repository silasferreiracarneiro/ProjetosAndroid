package com.example.instagram.ui.register

import com.example.instagram.R
import com.example.instagram.model.User
import com.example.instagram.ui.base.BasePresenter
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import javax.inject.Inject

class RegisterPresenter<V: RegisterContract.View, I: RegisterContract.Interactor>
    @Inject constructor(var interactorRegister: I):
    BasePresenter<V, I>(interactorRegister), RegisterContract.Presenter<V, I> {

    override fun createUser(user: User) {
        if(!(user.name == "" || user.email == "" || user.password == "")){
            getMvpView().setVisibleProgress()
            interactorRegister.createUserInAuthentication(user).addOnCompleteListener{
                if(it.isSuccessful){
                    interactorRegister.createUser(user)
                    getMvpView().onFinish()
                }else{
                    var messageUser = when (it.exception) {
                        is FirebaseAuthWeakPasswordException -> "Digite uma senha mais forte!"
                        is FirebaseAuthInvalidCredentialsException -> "Por favor, digite um e-mail válido!"
                        is FirebaseAuthUserCollisionException -> "Esta conta já foi cadastrada!"
                        else -> "Erro ao cadastrar a conta!"
                    }
                    getMvpView().onError(messageUser)
                    getMvpView().setVisibleGoneProgress()
                }
            }.addOnFailureListener{
                getMvpView().setVisibleGoneProgress()
                getMvpView().onError(getMvpView().onGetString(R.string.error_create_user))
            }
        }else{
            getMvpView().onError(getMvpView().onGetString(R.string.enter_the_data))
        }
    }
}
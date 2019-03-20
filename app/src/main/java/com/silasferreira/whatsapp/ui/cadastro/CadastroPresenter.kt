package com.silasferreira.whatsapp.ui.cadastro

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.ui.base.BasePresenter

import javax.inject.Inject

class CadastroPresenter<V : CadastroContract.View, I : CadastroContract.Interactor>
    @Inject constructor(var contractInteractor: I) :
    BasePresenter<V, I>(contractInteractor), CadastroContract.Presenter<V, I> {

    override fun createUser(user: Usuario) {
        if(this.validateUser(user)){

            contractInteractor.createUser(user).addOnCompleteListener(OnCompleteListener {
                if(it.isSuccessful){
                    getMvpView().showMessage("Usuário salvo com sucesso!")
                    getMvpView().onFinish()
                }else{
                    var messageUser = when (it.exception) {
                        is FirebaseAuthWeakPasswordException -> "Digite uma senha mais forte!"
                        is FirebaseAuthInvalidCredentialsException -> "Por favor, digite um e-mail válido!"
                        is FirebaseAuthUserCollisionException -> "Esta conta já foi cadastrada!"
                        else -> "Erro ao cadastrar a conta!"
                    }
                    getMvpView().onError(messageUser)
                }

            }).addOnFailureListener(OnFailureListener {
                getMvpView().onError("Erro ao cadastrar a conta!")
            })
        }
    }

    private fun validateUser(user: Usuario) : Boolean{
        if(user.nome.isEmpty() || user.email.isEmpty() || user.senha.isEmpty())
            return false
        return true
    }
}
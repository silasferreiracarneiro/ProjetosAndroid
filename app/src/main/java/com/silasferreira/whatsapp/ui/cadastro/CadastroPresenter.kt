package com.silasferreira.whatsapp.ui.cadastro

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.ui.base.BasePresenter
import java.lang.Exception

import javax.inject.Inject

class CadastroPresenter<V : CadastroContract.View, I : CadastroContract.Interactor>
    @Inject constructor(var contractInteractor: I) :
    BasePresenter<V, I>(contractInteractor), CadastroContract.Presenter<V, I> {

    private val ERROR = "ERROR_SAVED_USER"

    override fun createUser(user: Usuario) {
        if(this.validateUser(user)){

            contractInteractor.createUser(user).addOnCompleteListener(OnCompleteListener {
                if(it.isSuccessful){
                    this.savedUser(user)
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

    override fun savedUser(user: Usuario) {
        try {
            contractInteractor.savedUser(user)
        }catch (e: Exception){
            this.deleteUserInAuthentication()
        }
    }

    override fun deleteUserInAuthentication() {
        contractInteractor.deleteUserInAuthentication()?.addOnCompleteListener(OnCompleteListener {
            if(it.isSuccessful){
                Log.e(ERROR, "Usuário deletado por algum erro!")
            } else {
                Log.e(ERROR, "Usuario não foi deletado!!")
            }
        })?.addOnFailureListener(OnFailureListener {
            Log.e(ERROR, "Usuario não foi deletado!!")
        })
    }

    private fun validateUser(user: Usuario) : Boolean{
        if(user.nome.isEmpty() || user.email.isEmpty() || user.senha.isEmpty())
            return false
        return true
    }
}
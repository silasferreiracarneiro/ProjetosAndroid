package com.silasferreira.whatsapp.ui.cadastro


import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.silasferreira.whatsapp.data.UsuarioDao
import com.silasferreira.whatsapp.domain.Usuario
import java.lang.Exception

class CadastroPresenter(private val view: CadastroContract.View) : CadastroContract.Presenter {

    private var usuarioDao: UsuarioDao = UsuarioDao()

    override fun createUser(user: Usuario) {
        var messageUser = ""
        if(this.validateUser(user)){
            try {
                var result = usuarioDao.savedUser(user.toDto(), view.getActivity())

                if(result == null){
                    messageUser = "Usuário cadastrado com sucesso!"
                }else{
                    throw result
                }
            }catch (e: FirebaseAuthWeakPasswordException){
                messageUser = "Digite uma senha mais forte!"
            }catch (e: FirebaseAuthInvalidCredentialsException){
                messageUser = "Por favor, digite um e-mail válido!"
            }catch (e: FirebaseAuthUserCollisionException){
                messageUser = "Esta conta já foi cadastrada!"
            }catch (e: Exception){
                messageUser = "Erro ao cadastrar a conta!"
            }
        }
        this.view.setMessageUser(messageUser)
    }

    private fun validateUser(user: Usuario) : Boolean{
        if(user.nome.isEmpty() || user.email.isEmpty() || user.senha.isEmpty())
            return false
        return true
    }
}
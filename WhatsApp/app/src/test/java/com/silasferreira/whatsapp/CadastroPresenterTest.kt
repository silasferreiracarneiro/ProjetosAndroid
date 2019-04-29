package com.silasferreira.whatsapp

import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.ui.register.CadastroContract
import com.silasferreira.whatsapp.ui.register.CadastroPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class CadastroPresenterTest {

    lateinit var cadastroPresenter: CadastroPresenter<CadastroContract.View, CadastroContract.Interactor>
    lateinit var cadastroView: CadastroContract.View
    lateinit var cadastroInteractor: CadastroContract.Interactor

    lateinit var userValid: Usuario
    lateinit var userNotValid: Usuario

    @Before
    fun initialization() {
        cadastroView = mock(CadastroContract.View::class.java)
        cadastroInteractor = mock(CadastroContract.Interactor::class.java)

        userValid = Usuario(
            "bWFyaW9AZ21haWwuY29t",
            "Mário Soares de Souza Cruz",
            "mario@gmail.com",
            "123456",
            "",
            "Mário Soares de Souza Cruz")

        userNotValid = Usuario(
            "",
            "",
            "",
            "",
            "",
            "")

        cadastroPresenter = CadastroPresenter(cadastroInteractor)
        cadastroPresenter.onAttach(cadastroView)
    }

    @Test
    fun createUserSucess(){
        cadastroPresenter.createUser(userValid)
        verify(cadastroView, never()).onError("Erro ao cadastrar a conta!")
    }

    @Test
    fun createUserError(){
        cadastroPresenter.createUser(userNotValid)
        verify(cadastroView, times(1)).onError("Informe um usuário válido!")
    }
}
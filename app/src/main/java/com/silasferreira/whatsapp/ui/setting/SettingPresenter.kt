package com.silasferreira.whatsapp.ui.setting

import com.google.firebase.database.ValueEventListener
import com.silasferreira.whatsapp.ui.base.BasePresenter
import java.lang.Exception
import javax.inject.Inject
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.utils.Base64Utils.decodeByte
import com.silasferreira.whatsapp.utils.Base64Utils.decodebase64InBitmap


class SettingPresenter<V: SettingContract.View, I: SettingContract.Interactor>
    @Inject constructor(var settingInteractor: I):
    BasePresenter<V, I>(settingInteractor), SettingContract.Presenter<V, I> {

    override fun uploadImage(image: ByteArray) {

        try {
            settingInteractor.uploadImage(image)
            getMvpView().setImageUser(decodebase64InBitmap(image))
            getMvpView().onError("Imagem salva com sucesso!")
        }catch (e: Exception){
            getMvpView().onError("Erro ao salvar a imagem!")
        }
    }

    override fun searchUserPhoto() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(Usuario::class.java)
                var byte = decodeByte(user?.foto)
                getMvpView().setImageUser(decodebase64InBitmap(byte))
            }

            override fun onCancelled(databaseError: DatabaseError) {
                getMvpView().onError("Erro ao buscar a foto!")
            }
        }
        this.settingInteractor.searchUserPhoto().addValueEventListener(postListener)
    }
}
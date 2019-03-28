package com.silasferreira.whatsapp.ui.registergroup

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.silasferreira.whatsapp.model.Group
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.ui.base.BasePresenter
import javax.inject.Inject

class RegisterGroupPresenter<V : RegisterGroupContract.View, I : RegisterGroupContract.Interactor>
    @Inject constructor(var registerGroupInteractor: I):
    BasePresenter<V, I>(registerGroupInteractor), RegisterGroupContract.Presenter<V, I>{

    override fun savedGroup(group: Group) {

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(Usuario::class.java)
                if(user != null){
                   group!!.users.add(user!!)
                }
                registerGroupInteractor.savedGroup(group)
                getMvpView().goChatGroup(group)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                getMvpView().onError("Erro ao buscar o usuário atual!")
            }
        }

        registerGroupInteractor.getUser().addValueEventListener(postListener)
    }
}
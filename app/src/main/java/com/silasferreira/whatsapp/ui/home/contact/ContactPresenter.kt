package com.silasferreira.whatsapp.ui.home.contact

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.ui.base.BasePresenter
import javax.inject.Inject

class ContactPresenter<V: ContactContract.View, I: ContactContract.Integractor>
        @Inject constructor(var contactInteractor: I): ContactContract.Presenter<V, I>,
        BasePresenter<V, I>(contactInteractor){

        override fun onViewPrepared() {
                var listUser: ArrayList<Usuario> = arrayListOf()
                var currencyUser = interactor.getCurrencyUser()

                val listener = object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                                dataSnapshot.children.forEach {
                                        var user = it.getValue(Usuario::class.java)!!
                                        if(!currencyUser?.email.equals(user.email)){
                                                listUser.add(user)
                                        }
                                }
                                getMvpView().setNewListUser(listUser)
                        }

                        override fun onCancelled(databaseError: DatabaseError) {
                                getMvpView().onError("Erro ao buscar os contatos!")
                        }
                }

                this.contactInteractor.getListUser().addValueEventListener(listener!!)
        }


}
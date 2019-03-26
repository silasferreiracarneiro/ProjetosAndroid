package com.silasferreira.whatsapp.ui.group

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.ui.base.BasePresenter
import javax.inject.Inject

class GroupPresenter<V: GroupContract.View, I: GroupContract.Interactor>
    @Inject constructor(var groupInteractor: I):
    BasePresenter<V, I>(groupInteractor), GroupContract.Presenter<V, I> {

    var listUser = arrayListOf<Usuario>()
    var listUserSelect = arrayListOf<Usuario>()

    override fun getListUser() {

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
                updateToolbar()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                getMvpView().onError("Erro ao buscar os contatos!")
            }
        }

        groupInteractor.getListUser().addValueEventListener(listener!!)
    }

    override fun getUserSelect(position: Int): Usuario {
        return listUser[position]
    }

    override fun userSelect(position: Int) {
        var user = this.getUserSelect(position)

        listUser.remove(user)
        getMvpView().setNewListUser(listUser)

        listUserSelect.add(user)
        getMvpView().setNewListUserSelect(listUserSelect)

        updateToolbar()
    }

    override fun userDeselect(position: Int) {
        var user = listUserSelect[position]

        listUser.add(user)
        getMvpView().setNewListUser(listUser)

        listUserSelect.remove(user)
        getMvpView().setNewListUserSelect(listUserSelect)

        updateToolbar()
    }

    override fun updateToolbar() {
        getMvpView().updateToolbar(this.listUser.size, this.listUserSelect.size)
    }

    override fun getListUserSelects(): ArrayList<Usuario> {
        return listUserSelect
    }
}
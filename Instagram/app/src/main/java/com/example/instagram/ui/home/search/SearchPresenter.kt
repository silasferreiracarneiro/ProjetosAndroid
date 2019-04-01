package com.example.instagram.ui.home.search

import com.example.instagram.model.User
import com.example.instagram.ui.base.BasePresenter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class SearchPresenter<V: SearchContract.View, I: SearchContract.Interactor>
    @Inject constructor(var interactorSearch: I):
    BasePresenter<V, I>(interactorSearch), SearchContract.Presenter<V, I> {

    var listUsers: ArrayList<User> = arrayListOf()

    override fun searchUser(caracter: String?) {

        interactorSearch.searchUser(caracter?.toUpperCase()!!).addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                listUsers = arrayListOf()
                p0.children.forEach{
                    listUsers.add(it.getValue(User::class.java)!!)
                }
                getMvpView().setListUser(listUsers)
            }

            override fun onCancelled(p0: DatabaseError) {
                getMvpView().onError(p0.message )
            }
        })
    }
}
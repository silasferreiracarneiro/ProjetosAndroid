package com.example.instagram.ui.home.profile

import com.example.instagram.R
import com.example.instagram.model.Posting
import com.example.instagram.model.User
import com.example.instagram.ui.base.BasePresenter
import com.example.instagram.utils.Base64Utils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class ProfilePresenter<V: ProfileContract.View, I: ProfileContract.Interactor>
    @Inject constructor(interactorProfile: I):
    BasePresenter<V, I>(interactorProfile), ProfileContract.Presenter<V, I> {

    var user: User? = null

    override fun getUser() {
        getMvpView().showLoading()
        interactor.getUser().addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) = if(p0.exists()){
                user = p0.getValue(User::class.java)!!
                getMvpView().setUser(user!!)
                getMvpView().hideLoading()
            }else{
                getMvpView().onError(R.string.error_load_user)
                getMvpView().hideLoading()
            }

            override fun onCancelled(p0: DatabaseError) {
                getMvpView().onError(R.string.error_load_user)
                getMvpView().hideLoading()
            }
        })
    }

    override fun loadPosting() {
        interactor.loadPosting(Base64Utils.encode(user!!.email)).addListenerForSingleValueEvent(object: ValueEventListener{

            override fun onDataChange(p0: DataSnapshot) {
                var postagens = arrayListOf<Posting>()
                p0.children.forEach{
                    postagens.add(it.getValue(Posting::class.java)!!)
                }
                getMvpView().setPosting(postagens)
            }

            override fun onCancelled(p0: DatabaseError) {
                getMvpView().onError("Erro ao buscar as postagens!")
            }
        })
    }
}
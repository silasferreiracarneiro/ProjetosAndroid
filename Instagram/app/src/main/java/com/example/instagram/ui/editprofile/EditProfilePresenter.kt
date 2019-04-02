package com.example.instagram.ui.editprofile

import com.example.instagram.model.User
import com.example.instagram.ui.base.BasePresenter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class EditProfilePresenter<V: EditProfileContract.View, I: EditProfileContract.Interactor>
    @Inject constructor(var interacorProfile: I):
    BasePresenter<V, I>(interacorProfile), EditProfileContract.Presenter<V, I> {

    override fun updateUser(user: User) {

        interacorProfile.uploadImage(getMvpView().getImageSelect())
            .addOnFailureListener{
                getMvpView().showMessage("Erro ao atualizado o usuário!")
        }.addOnSuccessListener {
                it.metadata!!.reference!!.downloadUrl!!.addOnSuccessListener {uri ->
                    user.photo = uri.toString()
                    interacorProfile.updateUser(user)
                    getMvpView().showMessage("Usuário atualizado!")
                    getMvpView().onFinish()
                }
        }
    }

    override fun getUser() {
        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                getMvpView().setInformationUser(dataSnapshot.getValue(User::class.java)!!)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                getMvpView().onError("Erro ao buscar o usuário!")
            }
        }

        interacorProfile.getUser().addValueEventListener(listener)
    }
}
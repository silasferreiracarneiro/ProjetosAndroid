package com.example.instagram.ui.editprofile

import com.example.instagram.data.network.repository.UserRepository
import com.example.instagram.model.User
import com.example.instagram.ui.base.BaseInteractor
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.UploadTask

class EditProfileInteractor(var repository: UserRepository): BaseInteractor(), EditProfileContract.Interactor {

    override fun uploadImage(photo: ByteArray): UploadTask {
        return repository.uploadImageUser(photo)
    }

    override fun updateUser(user: User) {
        repository.createUser(user)
    }

    override fun getUser(): DatabaseReference {
        return repository.getUser()
    }
}
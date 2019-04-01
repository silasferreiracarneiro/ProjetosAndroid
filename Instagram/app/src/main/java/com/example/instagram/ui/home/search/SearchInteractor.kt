package com.example.instagram.ui.home.search

import com.example.instagram.data.network.repository.UserRepository
import com.example.instagram.ui.base.BaseInteractor
import com.google.firebase.database.Query

class SearchInteractor(var repository: UserRepository): BaseInteractor(), SearchContract.Interactor {

    override fun searchUser(caracter: String): Query {
        return repository.searchUser(caracter)
    }
}
package com.example.instagram.ui.register

import com.example.instagram.data.network.repository.UserRepository
import com.example.instagram.ui.base.BaseInteractor

class RegisterInteractor(var repository: UserRepository) : BaseInteractor(), RegisterContract.Interactor {
}
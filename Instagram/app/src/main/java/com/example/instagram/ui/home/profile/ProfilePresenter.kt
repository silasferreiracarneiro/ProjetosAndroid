package com.example.instagram.ui.home.profile

import com.example.instagram.ui.base.BasePresenter
import javax.inject.Inject

class ProfilePresenter<V: ProfileContract.View, I: ProfileContract.Interactor>
    @Inject constructor(interactorProfile: I):
    BasePresenter<V, I>(interactorProfile), ProfileContract.Presenter<V, I> {
}
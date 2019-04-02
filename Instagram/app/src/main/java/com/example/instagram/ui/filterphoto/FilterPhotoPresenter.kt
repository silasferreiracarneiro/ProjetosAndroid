package com.example.instagram.ui.filterphoto

import com.example.instagram.ui.base.BasePresenter
import javax.inject.Inject

class FilterPhotoPresenter<V: FilterPhotoContract.View, I: FilterPhotoContract.Interactor>
    @Inject constructor(var interactorPhoto: I):
    BasePresenter<V, I>(interactorPhoto), FilterPhotoContract.Presenter<V, I> {


}
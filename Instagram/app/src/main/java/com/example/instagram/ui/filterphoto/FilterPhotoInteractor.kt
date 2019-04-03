package com.example.instagram.ui.filterphoto

import com.example.instagram.data.network.repository.PostingRepository
import com.example.instagram.ui.base.BaseInteractor

class FilterPhotoInteractor(var repository: PostingRepository): BaseInteractor(), FilterPhotoContract.Interactor {

}
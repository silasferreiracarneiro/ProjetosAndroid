package com.silasferreira.whatsapp.ui.setting

import com.silasferreira.whatsapp.ui.base.BasePresenter
import javax.inject.Inject

class SettingPresenter<V: SettingContract.View, I: SettingContract.Interactor>
    @Inject constructor(var settingInteractor: I):
    BasePresenter<V, I>(settingInteractor), SettingContract.Presenter<V, I> {


    override fun uploadImage() {
        settingInteractor.uploadImage()
    }

}
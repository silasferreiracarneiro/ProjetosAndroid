package com.silasferreira.youtube.di.module

import androidx.appcompat.app.AppCompatActivity
import com.silasferreira.youtube.data.RetrofitConfig
import com.silasferreira.youtube.data.network.VideoRepository
import com.silasferreira.youtube.data.network.request.AppVideoRepository
import com.silasferreira.youtube.di.PerActivity
import com.silasferreira.youtube.ui.home.HomeContract
import com.silasferreira.youtube.ui.home.HomeInteractor
import com.silasferreira.youtube.ui.home.HomePresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(appCompatActivity: AppCompatActivity)  {

    //HOME
    @Provides
    @PerActivity
    fun provideHomePresenter(interactor: HomeContract.Interactor): HomeContract.Presenter<HomeContract.View, HomeContract.Interactor>{
        return HomePresenter(interactor)
    }

    @Provides
    @PerActivity
    fun provideHomeInteractor(repository: VideoRepository): HomeContract.Interactor{
        return HomeInteractor(repository)
    }

    //Repository
    @Provides
    fun providePostingRepository(): VideoRepository {
        return AppVideoRepository()
    }
}
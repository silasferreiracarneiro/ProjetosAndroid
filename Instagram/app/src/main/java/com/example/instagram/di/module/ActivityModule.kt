package com.example.instagram.di.module

import androidx.appcompat.app.AppCompatActivity
import com.example.instagram.data.firebase.ConfigFirebaseContract
import com.example.instagram.data.network.model.FollowerRequest
import com.example.instagram.data.network.model.PostingRequest
import com.example.instagram.data.network.model.UserRequest
import com.example.instagram.data.network.repository.FollowerRepository
import com.example.instagram.data.network.repository.PostingRepository
import com.example.instagram.data.network.repository.UserRepository
import com.example.instagram.data.prefs.PreferencesHelper
import com.example.instagram.di.PerActivity
import com.example.instagram.ui.editprofile.EditProfileContract
import com.example.instagram.ui.editprofile.EditProfileInteractor
import com.example.instagram.ui.editprofile.EditProfilePresenter
import com.example.instagram.ui.filterphoto.FilterPhotoContract
import com.example.instagram.ui.filterphoto.FilterPhotoInteractor
import com.example.instagram.ui.filterphoto.FilterPhotoPresenter
import com.example.instagram.ui.friendprofile.FriendProfileContract
import com.example.instagram.ui.friendprofile.FriendProfileInteractor
import com.example.instagram.ui.friendprofile.FriendProfilePresenter
import com.example.instagram.ui.home.HomeContract
import com.example.instagram.ui.home.HomeInteractor
import com.example.instagram.ui.home.HomePresenter
import com.example.instagram.ui.home.feed.FeedContract
import com.example.instagram.ui.home.feed.FeedInteractor
import com.example.instagram.ui.home.feed.FeedPresenter
import com.example.instagram.ui.home.posting.PostingContract
import com.example.instagram.ui.home.posting.PostingInteractor
import com.example.instagram.ui.home.posting.PostingPresenter
import com.example.instagram.ui.home.profile.ProfileContract
import com.example.instagram.ui.home.profile.ProfileInteractor
import com.example.instagram.ui.home.profile.ProfilePresenter
import com.example.instagram.ui.home.search.SearchContract
import com.example.instagram.ui.home.search.SearchInteractor
import com.example.instagram.ui.home.search.SearchPresenter
import com.example.instagram.ui.login.LoginContract
import com.example.instagram.ui.login.LoginInteractor
import com.example.instagram.ui.login.LoginPresenter
import com.example.instagram.ui.register.RegisterContract
import com.example.instagram.ui.register.RegisterInteractor
import com.example.instagram.ui.register.RegisterPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(appCompatActivity: AppCompatActivity) {

    //REGISTER
    @Provides
    @PerActivity
    fun provideRegisterPresenter(interactorRegister: RegisterContract.Interactor): RegisterContract.Presenter<RegisterContract.View, RegisterContract.Interactor>{
        return RegisterPresenter(interactorRegister)

    }

    @Provides
    @PerActivity
    fun provideRegisterInteractor(userRepository: UserRepository): RegisterContract.Interactor {
        return RegisterInteractor(userRepository)
    }

    //LOGIN
    @Provides
    @PerActivity
    fun provideLoginPresenter(interactor: LoginContract.Interactor, prefHelter: PreferencesHelper): LoginContract.Presenter<LoginContract.View, LoginContract.Interactor>{
        return LoginPresenter(interactor, prefHelter)
    }

    @Provides
    @PerActivity
    fun provideLoginInteractor(userRepository: UserRepository): LoginContract.Interactor{
        return LoginInteractor(userRepository)
    }

    //HOME
    @Provides
    @PerActivity
    fun provideHomePresenter(interactor: HomeContract.Interactor): HomeContract.Presenter<HomeContract.View, HomeContract.Interactor>{
        return HomePresenter(interactor)
    }

    @Provides
    @PerActivity
    fun provideHomeInteractor(userRepository: UserRepository): HomeContract.Interactor{
        return HomeInteractor(userRepository)
    }

    //EDIT PROFILE
    @Provides
    @PerActivity
    fun provideEditProfilePresenter(interactor: EditProfileContract.Interactor): EditProfileContract.Presenter<EditProfileContract.View, EditProfileContract.Interactor>{
        return EditProfilePresenter(interactor)
    }

    @Provides
    @PerActivity
    fun provideEditProfileInteractor(repository: UserRepository): EditProfileContract.Interactor{
        return EditProfileInteractor(repository)
    }

    //SEARCH
    @Provides
    @PerActivity
    fun provideSearchPresenter(interactor: SearchContract.Interactor): SearchContract.Presenter<SearchContract.View, SearchContract.Interactor>{
        return SearchPresenter(interactor)
    }

    @Provides
    @PerActivity
    fun provideSearchInteractor(repository: UserRepository): SearchContract.Interactor {
        return SearchInteractor(repository)
    }

    //FRIEND PROFILE
    @Provides
    @PerActivity
    fun provideFriendProfilePresenter(interactor: FriendProfileContract.Interactor): FriendProfileContract.Presenter<FriendProfileContract.View, FriendProfileContract.Interactor>{
        return FriendProfilePresenter(interactor)
    }

    @Provides
    @PerActivity
    fun provideFriendProfileInteractor(repository: UserRepository, followerRepository: FollowerRepository, postingRepository: PostingRepository): FriendProfileContract.Interactor {
        return FriendProfileInteractor(repository, followerRepository, postingRepository)
    }

    //PROFILE
    @Provides
    @PerActivity
    fun provideProfilePresenter(interactor: ProfileContract.Interactor): ProfileContract.Presenter<ProfileContract.View, ProfileContract.Interactor>{
        return ProfilePresenter(interactor)
    }

    @Provides
    @PerActivity
    fun provideProfileInteractor(repository: UserRepository): ProfileContract.Interactor {
        return ProfileInteractor(repository)
    }

    //POSTING
    @Provides
    @PerActivity
    fun providePostingPresenter(interactor: PostingContract.Interactor): PostingContract.Presenter<PostingContract.View, PostingContract.Interactor>{
        return PostingPresenter(interactor)
    }

    @Provides
    @PerActivity
    fun providePostingInteractor(repository: UserRepository): PostingContract.Interactor {
        return PostingInteractor(repository)
    }

    //FEED
    @Provides
    @PerActivity
    fun provideFeedPresenter(interactor: FeedContract.Interactor): FeedContract.Presenter<FeedContract.View, FeedContract.Interactor>{
        return FeedPresenter(interactor)
    }

    @Provides
    @PerActivity
    fun provideFeedInteractor(repository: UserRepository): FeedContract.Interactor {
        return FeedInteractor(repository)
    }

    //FILTER PHOTO
    @Provides
    @PerActivity
    fun provideFilterPhotoPresenter(interactor: FilterPhotoContract.Interactor): FilterPhotoContract.Presenter<FilterPhotoContract.View, FilterPhotoContract.Interactor>{
        return FilterPhotoPresenter(interactor)
    }

    @Provides
    @PerActivity
    fun provideFilterPhotoInteractor(repository: PostingRepository, repositoryUser: UserRepository): FilterPhotoContract.Interactor {
        return FilterPhotoInteractor(repository, repositoryUser)
    }

    //REPOSITORY
    @Provides
    fun provideUserRepository(firebaseConfig: ConfigFirebaseContract): UserRepository{
        return UserRequest(firebaseConfig)
    }

    @Provides
    fun provideFollowerRepository(firebaseConfig: ConfigFirebaseContract): FollowerRepository {
        return FollowerRequest(firebaseConfig)
    }

    @Provides
    fun providePostingRepository(firebaseConfig: ConfigFirebaseContract): PostingRepository {
        return PostingRequest(firebaseConfig)
    }
}
package com.example.instagram.ui.friendprofile

import com.example.instagram.R
import com.example.instagram.model.Follower
import com.example.instagram.model.Posting
import com.example.instagram.model.User
import com.example.instagram.ui.base.BasePresenter
import com.example.instagram.utils.Base64Utils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class FriendProfilePresenter<V: FriendProfileContract.View, I: FriendProfileContract.Interactor>
    constructor(var interactorFriend: I):
    BasePresenter<V, I>(interactorFriend), FriendProfileContract.Presenter<V, I> {

    override fun following(user: User) {
        interactorFriend.following(user.email)?.addValueEventListener(object: ValueEventListener{

            var email = ""

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    var follow = p0.getValue(Follower::class.java)
                    email = follow?.user?.email!!
                    getMvpView().setTextButtonFollowing(getMvpView().onGetString(R.string.seguindo), false)
                }else{
                    email = user.email
                    getMvpView().setTextButtonFollowing(getMvpView().onGetString(R.string.seguir), true)
                }
                getUserKey(email)
            }

            override fun onCancelled(p0: DatabaseError) {
                getMvpView().onError("Erro ao verificar se o usuário possui following!")
            }
        })
    }

    override fun getUserKey(email: String) {
        interactor.getUserKey(email).addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                getMvpView().onError("Erro ao procurar o usuário!")
            }

            override fun onDataChange(p0: DataSnapshot) {
               if(p0.exists()){
                   var user = p0.getValue(User::class.java)
                   getMvpView().setUserPae(user)
               }
            }
        })
    }

    override fun savedFollower(follower: Follower) {
        interactorFriend.getUser().addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                var user = p0.getValue(User::class.java)

                user?.qtFollower = user?.qtFollowing!! + 1
                follower.user.qtFollowing = follower.user.qtFollower + 1
                follower.idFollower = Base64Utils.encode(user?.email)

                interactorFriend.savedFollower(follower)
                interactorFriend.upadteUser(user)
                interactorFriend.upadteUser(follower.user)

                interactorFriend.savedFolloweres(follower)

                getMvpView().setTextButtonFollowing(getMvpView().onGetString(R.string.seguindo), false)
            }

            override fun onCancelled(p0: DatabaseError) {
                getMvpView().onError("Erro ao buscar o usuário!")
            }
        })
    }

    override fun getAllPosting(identify: String) {
        interactorFriend.getAllPosting(Base64Utils.encode(identify)).addListenerForSingleValueEvent(object: ValueEventListener{

            override fun onDataChange(p0: DataSnapshot) {
                var postagens = arrayListOf<Posting>()
                p0.children.forEach{
                    postagens.add(it.getValue(Posting::class.java)!!)
                }
                getMvpView().setAllPosting(postagens)
            }

            override fun onCancelled(p0: DatabaseError) {
                getMvpView().onError("Erro ao buscar as postagens!")
            }
        })
    }
}
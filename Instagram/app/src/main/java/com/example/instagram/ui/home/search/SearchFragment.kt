package com.example.instagram.ui.home.search


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.instagram.R
import com.example.instagram.model.User
import com.example.instagram.ui.base.BaseFragment
import com.example.instagram.ui.friendprofile.FriendProfileActivity
import com.example.instagram.utils.AppConstants.Companion.USER_INTENT
import com.example.instagram.utils.RecyclerViewItemClickListener
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*
import javax.inject.Inject

class SearchFragment : BaseFragment(), SearchContract.View {

    @Inject lateinit var presenter: SearchContract.Presenter<SearchContract.View, SearchContract.Interactor>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_search, container, false)

        val component = getActivityComponent()

        if(component != null){
            component.inject(this)
            presenter.onAttach(this)
        }

        view.search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText?.length!! >= 3){
                    presenter.searchUser(newText)
                }
                setListUser(arrayListOf())
                return true
            }

        })

        view.resultSearch.setHasFixedSize(true)
        view.resultSearch.layoutManager = LinearLayoutManager(activity)

        view.resultSearch.addOnItemTouchListener(RecyclerViewItemClickListener(context!!, view.resultSearch, object:RecyclerViewItemClickListener.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                var user = presenter.getUserList(position)
                var i = Intent(context!!, FriendProfileActivity::class.java)
                i.putExtra(USER_INTENT, user)
                startActivity(i)
            }

            override fun onItemLongClick(view: View?, position: Int) {

            }
        }))

        return view
    }

    override fun setUp(view: View) { }

    override fun setListUser(list: ArrayList<User>) {
        view?.resultSearch?.adapter = SearchAdapter(list)
        (view?.resultSearch?.adapter as SearchAdapter).notifyDataSetChanged()
    }

    override fun setVisibleGoneProgress() {
        view?.progressBarListUser?.visibility = View.GONE
    }

    override fun setVisibleProgress() {
        view?.progressBarListUser?.visibility = View.VISIBLE
    }
}

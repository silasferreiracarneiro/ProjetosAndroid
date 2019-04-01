package com.example.instagram.ui.home.search


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.instagram.R
import com.example.instagram.model.User
import com.example.instagram.ui.base.BaseFragment
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

        return view
    }

    override fun setUp(view: View) { }

    override fun setListUser(list: ArrayList<User>) {
        view?.resultSearch?.adapter = SearchAdapter(list)
        (view?.resultSearch?.adapter as SearchAdapter).notifyDataSetChanged()
    }
}

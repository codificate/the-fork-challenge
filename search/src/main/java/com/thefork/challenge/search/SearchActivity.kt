package com.thefork.challenge.search

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.thefork.challenge.api.Api
import com.thefork.challenge.api.UserPreview
import com.thefork.challenge.search.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity(), SearchUsersContract.View {

    private lateinit var viewBinding: ActivitySearchBinding
    private lateinit var presenter: SearchUsersContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        presenter = SearchPresenter(this, Api.instance().userService)
        presenter.init()
    }

    override fun displayUsers(users: List<UserPreview>) {
        viewBinding.usersListRecyclerView.adapter =
            UsersAdapter(users, presenter as UsersAdapter.UserEventHandler)
    }

    override fun displayError() {
        Snackbar
            .make(viewBinding.usersListRecyclerView.rootView, R.string.error, Snackbar.LENGTH_LONG)
            .show()
    }

    override fun navigateToUser(user: UserPreview) {
        startActivity(Intent().apply {
            setClassName("com.thefork.challenge", "com.thefork.challenge.user.UserActivity")
            putExtra("USER", user)
        })
    }
}

package com.thefork.challenge.search

import com.thefork.challenge.api.Api
import com.thefork.challenge.api.UserService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchPresenter(
    private val view: SearchUsersContract.View,
    private val userService: UserService
) : SearchUsersContract.Presenter,
    SearchUsersContract.View.UIEvenHandler {

    private val scope = CoroutineScope(Dispatchers.Main)

    override fun init() {
        scope.launch {
            val response = withContext(Dispatchers.IO) { userService.getUsers(1u) }
            if (response.isSuccessful) {
                response.body()?.let {
                    view.displayUsers(it.data)
                }
            } else {
                view.displayError()
            }
        }
    }

    override fun onUserClicked(userId: String) {
        scope.launch {
            val response =
                withContext(Dispatchers.IO) { userService.fetchUserDetail(userId) }
            if (response.isSuccessful) {
                response.body()?.let { view.navigateToUser(it) }
                    ?: kotlin.run { view.displayError() }
            } else {
                view.displayError()
            }
        }
    }
}

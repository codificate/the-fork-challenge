package com.thefork.challenge.search

import com.thefork.challenge.api.UserPreview

interface SearchUsersContract {

    interface View {
        fun displayUsers(users: List<UserPreview>)
        fun displayError()
        fun navigateToUser(user: UserPreview)

        interface UIEvenHandler: UsersAdapter.UserEventHandler
    }

    interface Presenter {
        fun init()
    }

}
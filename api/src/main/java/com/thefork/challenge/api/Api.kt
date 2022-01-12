package com.thefork.challenge.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {

    val userService: UserService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        userService = retrofit.create(UserService::class.java)
    }

    companion object {

        private const val BASE_URL = "https://dummyapi.io/data/v1/"
        const val API_APP_ID = "61d9b2e753adab3ce8ab8176"

        @JvmStatic
        fun instance(): Api = Api()
    }

}

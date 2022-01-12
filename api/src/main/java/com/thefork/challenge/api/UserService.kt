package com.thefork.challenge.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    companion object {
        private const val APP_ID = "61d9b2e753adab3ce8ab8176"
    }

    @GET("user?limit=10")
    @Headers("app-id: $APP_ID")
    suspend fun getUsers(@Query("page") page: UInt): Response<Page<UserPreview>>

    @GET("user/{id}")
    @Headers("app-id: $APP_ID")
    suspend fun fetchUserDetail(@Path("id") id: String): Response<UserPreview>

}

package com.thefork.challenge.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("user?limit=10")
    @Headers("app-id: ${Api.API_APP_ID}")
    suspend fun getUsers(@Query("page") page: UInt): Response<Page<UserPreview>>

    @GET("user/{id}")
    @Headers("app-id: ${Api.API_APP_ID}")
    suspend fun fetchUserDetail(@Path("id") id: String): Response<UserPreview>

}

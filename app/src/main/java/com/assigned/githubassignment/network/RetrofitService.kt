package com.assigned.githubassignment.network

import com.assigned.githubassignment.model.UserModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


const val BASE_URL = "https://api.github.com/"

interface ApiInterface {

    @GET("users")
    open fun getAllCrews(): Call<ArrayList<UserModel>>
}

object RetrofitService {
    val apiInterface: ApiInterface

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiInterface = retrofit.create(ApiInterface::class.java)
    }

}
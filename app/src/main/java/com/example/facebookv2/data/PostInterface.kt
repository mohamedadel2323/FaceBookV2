package com.example.facebookv2.data

import com.example.facebookv2.pojo.PostModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET



private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface PostInterface {
    @GET("posts")
    fun getPosts(): Call<List<PostModel>>
}

object PostsApi{
    val retrofitService : PostInterface by lazy { retrofit.create(PostInterface::class.java) }
}
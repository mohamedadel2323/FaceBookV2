package com.example.facebookv2.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.facebookv2.data.PostInterface
import com.example.facebookv2.data.PostsApi
import com.example.facebookv2.pojo.PostModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await

class PostViewModel : ViewModel() {


    private val _posts = MutableLiveData<List<PostModel>>()
    val posts: LiveData<List<PostModel>>
        get() = _posts

    fun getPosts() {
//        viewModelScope.launch {
//            try {
//                var getPostsDeferred = PostsApi.retrofitService.getPosts()
//                var listResult =getPostsDeferred.await()
//                if (listResult.isNotEmpty()){
//                    _posts.value = listResult
//                }
//            }catch (t : Throwable){
//                Log.e("PostViewModel" , "${t.message}")
//            }
//        }
        PostsApi.retrofitService.getPosts().enqueue(object : Callback<List<PostModel>> {
            override fun onResponse(
                call: Call<List<PostModel>>,
                response: Response<List<PostModel>>
            ) {
                _posts.value = response.body()
            }

            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                Log.e("PostViewModel" , "${t.message}")
            }
        })
    }
}
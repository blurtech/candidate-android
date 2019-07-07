package blur.tech.candidate.network.api

import blur.tech.candidate.core.models.Initiative
import blur.tech.candidate.core.models.User
import blur.tech.candidate.network.Wrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface UserApi {

    @GET("/user/profile")
    fun getUser(@Header("Authorization") authHeader: String): Call<Wrapper<User>>

    @GET("/user/initiatives")
    fun getInitiatives(@Header("Authorization") authHeader: String): Call<Wrapper<ArrayList<Initiative>>>
}
package blur.tech.candidate.network.api

import blur.tech.candidate.core.models.AuthBody
import blur.tech.candidate.core.models.AuthRequestModel
import blur.tech.candidate.core.models.User
import blur.tech.candidate.network.Wrapper
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface SignInApi {

    @POST("user/")
    fun authUser(@Body authRequestModel: AuthRequestModel): Call<Wrapper<AuthBody>>

    @GET("user/")
    fun getUser(@Header("Authorization") authHeader: String): Call<Wrapper<User>>

}
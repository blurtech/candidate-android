package blur.tech.candidate.network.api

import blur.tech.candidate.core.models.Initiative
import blur.tech.candidate.core.models.Vote
import blur.tech.candidate.network.Wrapper
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface SwipeApi {

//    @GET("/swipe/next-swipes")
//    fun getSwipeBunch(@Header("Authorization") authHeader: String): Call<Wrapper<ArrayList<Initiative>>>

    @GET("/swipe/all")
    fun getSwipeAll(@Header("Authorization") authHeader: String): Call<Wrapper<ArrayList<Initiative>>>


    @POST("/swipe")
    fun swipe(@Header("Authorization") authHeader: String, @Body vote: Vote): Call<Wrapper<Void>>
}
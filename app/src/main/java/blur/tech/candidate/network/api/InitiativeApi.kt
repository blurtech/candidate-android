package blur.tech.candidate.network.api

import blur.tech.candidate.core.models.CreateInitiative
import blur.tech.candidate.core.models.Initiative
import blur.tech.candidate.core.models.OrgInitiative
import blur.tech.candidate.network.Wrapper
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface InitiativeApi {

    @POST("/initiative/create")
    fun createInitiative(@Header("Authorization") authHeader: String, @Body initiative: CreateInitiative): Call<Wrapper<Void>>

    @GET("/organization/initiatives")
    fun getInitiateves(@Header("Authorization") authHeader: String): Call<Wrapper<ArrayList<OrgInitiative>>>
}
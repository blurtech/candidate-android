package blur.tech.candidate.features.initiative_list.swipe

import android.content.SharedPreferences
import blur.tech.candidate.App
import blur.tech.candidate.core.PreferencesApi
import blur.tech.candidate.core.TokenBuilder
import blur.tech.candidate.core.models.Initiative
import blur.tech.candidate.core.models.Vote
import blur.tech.candidate.network.Wrapper
import blur.tech.candidate.network.api.SwipeApi
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

@InjectViewState
class SwipePresenter: MvpPresenter<SwipeView>() {

    private val api:SwipeApi

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var prefs: SharedPreferences

    init {
        App.INSTANCE.getAppComponent().inject(this)
        api = retrofit.create(SwipeApi::class.java)
    }

    fun getSwipeBunch(){
        api.getSwipeAll(TokenBuilder.build(PreferencesApi.getJwt(prefs)!!)).enqueue(object : Callback<Wrapper<ArrayList<Initiative>>>{

            override fun onResponse(
                call: Call<Wrapper<ArrayList<Initiative>>>,
                response: Response<Wrapper<ArrayList<Initiative>>>
            ) {
                if (response.isSuccessful && response.body() != null && response.body()!!.success){
                    viewState.onListReady(response.body()!!.data)
                }
            }

            override fun onFailure(call: Call<Wrapper<ArrayList<Initiative>>>, t: Throwable) {
                viewState.showMessage("Ошибка")
                t.printStackTrace()
            }
        })
    }

    fun onSwiped(item: Initiative, direction: String) {
        if (direction == "Left") onCanceled(item)
        else onAccepted(item)
    }

    private fun onAccepted(item: Initiative) {

        api.swipe(TokenBuilder.build(PreferencesApi.getJwt(prefs)!!), Vote(item._id, Vote.voteTypes.Like.toString()))
            .enqueue(object : Callback<Wrapper<Void>>{
                override fun onResponse(call: Call<Wrapper<Void>>, response: Response<Wrapper<Void>>) {
                    if (!response.isSuccessful)
                        viewState.showMessage("Ошибка")
                }

                override fun onFailure(call: Call<Wrapper<Void>>, t: Throwable) {
                    viewState.showMessage("Ошибка")
                    t.printStackTrace()
                }

            })
    }

    private fun onCanceled(item: Initiative) {
        api.swipe(TokenBuilder.build(PreferencesApi.getJwt(prefs)!!), Vote(item._id, Vote.voteTypes.Dislike.toString()))
            .enqueue(object : Callback<Wrapper<Void>>{
                override fun onResponse(call: Call<Wrapper<Void>>, response: Response<Wrapper<Void>>) {
                    if (!response.isSuccessful)
                        viewState.showMessage("Ошибка")
                }

                override fun onFailure(call: Call<Wrapper<Void>>, t: Throwable) {
                    viewState.showMessage("Ошибка")
                    t.printStackTrace()
                }
            })
    }

    fun paginate() {
        api.getSwipeAll(TokenBuilder.build(PreferencesApi.getJwt(prefs)!!)).enqueue(object : Callback<Wrapper<ArrayList<Initiative>>>{
            override fun onResponse(
                call: Call<Wrapper<ArrayList<Initiative>>>,
                response: Response<Wrapper<ArrayList<Initiative>>>
            ) {
                if (response.isSuccessful && response.body() != null && response.body()!!.success){
                    viewState.paginate(response.body()!!.data)
                    viewState.showMessage("p")
                }
            }

            override fun onFailure(call: Call<Wrapper<ArrayList<Initiative>>>, t: Throwable) {
                viewState.showMessage("Ошибка")
                t.printStackTrace()
            }

        })
    }

    fun refreshList() {
        api.getSwipeAll(TokenBuilder.build(PreferencesApi.getJwt(prefs)!!)).enqueue(object : Callback<Wrapper<ArrayList<Initiative>>>{
            override fun onResponse(
                call: Call<Wrapper<ArrayList<Initiative>>>,
                response: Response<Wrapper<ArrayList<Initiative>>>
            ) {
                if (response.isSuccessful && response.body() != null && response.body()!!.success){
                    viewState.refresh(response.body()!!.data)
                }
            }

            override fun onFailure(call: Call<Wrapper<ArrayList<Initiative>>>, t: Throwable) {
                viewState.showMessage("Ошибка")
                t.printStackTrace()
            }
        })
    }

}

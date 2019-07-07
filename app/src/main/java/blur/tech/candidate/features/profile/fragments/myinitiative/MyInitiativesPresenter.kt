package blur.tech.candidate.features.profile.fragments.myinitiative

import android.content.SharedPreferences
import blur.tech.candidate.App
import blur.tech.candidate.core.PreferencesApi
import blur.tech.candidate.core.TokenBuilder
import blur.tech.candidate.core.models.Initiative
import blur.tech.candidate.network.Wrapper
import blur.tech.candidate.network.api.UserApi
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.*
import javax.inject.Inject

@InjectViewState
class MyInitiativesPresenter : MvpPresenter<MyInitiativesView>() {
    @Inject
    lateinit var tripRetrofit: Retrofit

    @Inject
    lateinit var preferences: SharedPreferences

    internal var list: ArrayList<Initiative>? = ArrayList()

    init {
        App.INSTANCE.getAppComponent().inject(this)
    }

    private val userApi: UserApi = tripRetrofit.create(UserApi::class.java)

    fun loadInitiatives() {

        userApi.getInitiatives(TokenBuilder.build(PreferencesApi.getJwt(preferences)!!)).enqueue(object : Callback<Wrapper<ArrayList<Initiative>>> {
            override fun onFailure(call: Call<Wrapper<ArrayList<Initiative>>>, t: Throwable) {
                viewState.showMessage("Error")
            }

            override fun onResponse(call: Call<Wrapper<ArrayList<Initiative>>>, response: Response<Wrapper<ArrayList<Initiative>>>) {
                list = response.body()!!.data
                viewState.showTrips(list!!)
                viewState.hideProgress()
            }
        })
    }

    fun refresh() {
        loadInitiatives()
    }

    fun onTripClicked(initiative: Initiative) {
//        viewState.navigateTo(FragmentTags.SHOW_TRIP, trip)
    }

    fun onTripCreateClicked() {
//        viewState.navigateTo(FragmentTags.CREATE_TRIP, null)
    }

}
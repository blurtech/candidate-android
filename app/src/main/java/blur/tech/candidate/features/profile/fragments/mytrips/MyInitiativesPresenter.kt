package blur.tech.candidate.features.profile.fragments.mytrips

import android.content.SharedPreferences
import blur.tech.candidate.App
import blur.tech.candidate.core.PreferencesApi
import blur.tech.candidate.core.models.Initiative
import blur.tech.candidate.features.profile.fragments.mytrips.MyTripsView
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
class MyInitiativesPresenter : MvpPresenter<MyTripsView>() {
    @Inject
    lateinit var tripRetrofit: Retrofit

    @Inject
    lateinit var preferences: SharedPreferences

    internal var list: List<Initiative>? = ArrayList()

    init {
        App.INSTANCE.getAppComponent().inject(this)
    }

    val profileApi: UserApi = tripRetrofit.create(UserApi::class.java)

    fun loadTrips() {

//        profileApi.getMyTrips(PreferencesApi.getJwt(preferences)!!).enqueue(object : Callback<List<Initiative>> {
//            override fun onFailure(call: Call<List<Initiative>>, t: Throwable) {
//                viewState.showMessage("Error")
//            }
//
//            override fun onResponse(call: Call<List<Initiative>>, response: Response<List<Initiative>>) {
//                list = response.body()
////                viewState.showTrips(list!!)
//                viewState.hideProgress()
//            }
//        })
    }

    fun refresh() {
        loadTrips()
    }

    fun onTripClicked(initiative: Initiative) {
//        viewState.navigateTo(FragmentTags.SHOW_TRIP, trip)
    }

    fun onTripCreateClicked() {
//        viewState.navigateTo(FragmentTags.CREATE_TRIP, null)
    }

}
package blur.tech.candidate.features.initiative_list.list

import android.content.SharedPreferences
import blur.tech.candidate.App
import blur.tech.candidate.core.PreferencesApi
import blur.tech.candidate.core.TokenBuilder
import blur.tech.candidate.core.models.OrgInitiative
import blur.tech.candidate.network.Wrapper
import blur.tech.candidate.network.api.InitiativeApi
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

@InjectViewState
class InitiativesListPresenter: MvpPresenter<InitiativesListView>() {

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var prefs:SharedPreferences

    val api: InitiativeApi

    init {
        App.INSTANCE.getAppComponent().inject(this)
        api = retrofit.create(InitiativeApi::class.java)
    }

    fun onRefresh(){
        api.getInitiateves(TokenBuilder.build(PreferencesApi.getJwt(prefs)!!)).enqueue(object : Callback<Wrapper<ArrayList<OrgInitiative>>>{
            override fun onResponse(
                call: Call<Wrapper<ArrayList<OrgInitiative>>>,
                response: Response<Wrapper<ArrayList<OrgInitiative>>>
            ) {
                if (response.isSuccessful && response.body() != null)
                    viewState.onListReady(response.body()!!.data)
            }

            override fun onFailure(call: Call<Wrapper<ArrayList<OrgInitiative>>>, t: Throwable) {
                viewState.showMessage("Ошибка")
                t.printStackTrace()
            }

        })
    }
}

package blur.tech.candidate.features.initiative

import android.content.SharedPreferences
import blur.tech.candidate.App
import blur.tech.candidate.core.PreferencesApi
import blur.tech.candidate.core.TokenBuilder
import blur.tech.candidate.core.models.CreateInitiative
import blur.tech.candidate.core.models.Geo
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
class CreateInitiativePresenter : MvpPresenter<CreateInitiativeView>() {


    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var prefs:SharedPreferences

    var title = ""
    var desc = ""

    val api: InitiativeApi

    init {
        App.INSTANCE.getAppComponent().inject(this)
        api = retrofit.create(InitiativeApi::class.java)
    }

    fun createInitiative() {
        if (title.isNotBlank() && desc.isNotBlank() && title.length <= 100) {
            api.createInitiative(TokenBuilder.build(PreferencesApi.getJwt(prefs)!!),CreateInitiative(title, desc, Geo())).enqueue(object : Callback<Wrapper<Void>> {

                override fun onResponse(call: Call<Wrapper<Void>>, response: Response<Wrapper<Void>>) {
                    if (response.isSuccessful)
                        viewState.createSuccessful()
                }

                override fun onFailure(call: Call<Wrapper<Void>>, t: Throwable) {
                    viewState.showMessage("Ошибка")
                    t.printStackTrace()
                }
            })
        }
    }

}

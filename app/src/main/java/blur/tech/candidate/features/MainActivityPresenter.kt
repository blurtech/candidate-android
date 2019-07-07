package blur.tech.candidate.features

import android.content.SharedPreferences
import blur.tech.candidate.App
import blur.tech.candidate.core.PreferencesApi
import blur.tech.candidate.core.TokenBuilder
import blur.tech.candidate.core.models.User
import blur.tech.candidate.network.Wrapper
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import blur.tech.candidate.network.api.SignInApi
import javax.inject.Inject

@InjectViewState
class MainActivityPresenter : MvpPresenter<MainActivityView>() {

    @Inject
    lateinit var prefs: SharedPreferences

    @Inject
    lateinit var retrofit: Retrofit

    private val api: SignInApi

    init {
        App.INSTANCE.getAppComponent().inject(this)
        api = retrofit.create(SignInApi::class.java)
    }

    fun checkAuth() {
        val token = PreferencesApi.getJwt(prefs)
        if (token != null)
            api.getUser(TokenBuilder.build(token)).enqueue(object : Callback<Wrapper<User>>{

                override fun onResponse(call: Call<Wrapper<User>>, response: Response<Wrapper<User>>) {
                    if (response.body() != null && response.isSuccessful && response.body()!!.success) viewState.authSuccessful()
                    else viewState.authFailed()
                }

                override fun onFailure(call: Call<Wrapper<User>>, t: Throwable) {
                    viewState.showMessage("Check your internet connection")
                    t.printStackTrace()
                }

            })
        else {
            viewState.authFailed()
        }

    }

}

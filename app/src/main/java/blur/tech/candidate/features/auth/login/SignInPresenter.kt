package tech.blur.redline.features.signin

import android.content.SharedPreferences
import blur.tech.candidate.App
import blur.tech.candidate.core.PreferencesApi
import blur.tech.candidate.core.models.AuthBody
import blur.tech.candidate.core.models.AuthRequestModel
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
class SignInPresenter: MvpPresenter<SignInView>(){

    var login = ""
    var password = ""

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var prefs:SharedPreferences

    val api: SignInApi

    init {
        App.INSTANCE.getAppComponent().inject(this)
        api = retrofit.create(SignInApi::class.java)
    }

    fun signInUser() {
        if (login.isNotBlank() && password.isNotBlank())
            api.authUser(AuthRequestModel(login, password)).enqueue(object : Callback<Wrapper<AuthBody>> {
                override fun onFailure(call: Call<Wrapper<AuthBody>>, t: Throwable) {
                    viewState.showMessage("Неверные данные")
                }

                override fun onResponse(call: Call<Wrapper<AuthBody>>, response: Response<Wrapper<AuthBody>>) {
                    PreferencesApi.setUser(prefs, response.body()!!.data.user)
                    PreferencesApi.setJwt(prefs, response.body()!!.data.token)
                    viewState.onUserAuthDone()
                }

            })
    }
}
package blur.tech.candidate.features.profile

import android.content.SharedPreferences
import blur.tech.candidate.App
import blur.tech.candidate.core.PreferencesApi
import blur.tech.candidate.core.TokenBuilder
import blur.tech.candidate.core.models.User
import blur.tech.candidate.network.Wrapper
import blur.tech.candidate.network.api.UserApi
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

@InjectViewState
class ProfilePresenter : MvpPresenter<ProfileView>() {

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var prefs: SharedPreferences

    val api: UserApi

    init {
        App.INSTANCE.getAppComponent().inject(this)
        api = retrofit.create(UserApi::class.java)
    }

    fun loadUser() {
        api.getUser(TokenBuilder.build(PreferencesApi.getJwt(prefs)!!)).enqueue(object : Callback<Wrapper<User>> {

            override fun onResponse(call: Call<Wrapper<User>>, response: Response<Wrapper<User>>) {
               if (response.isSuccessful && response.body() != null){
                   viewState.setUser(response.body()!!.data)
               }
            }

            override fun onFailure(call: Call<Wrapper<User>>, t: Throwable) {
                viewState.showMessage("Ошибка")
                t.printStackTrace()
            }

        })
    }

}

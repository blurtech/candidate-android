package blur.tech.candidate.features.initiative.show

import android.content.SharedPreferences
import blur.tech.candidate.App
import blur.tech.candidate.core.PreferencesApi
import blur.tech.candidate.core.TokenBuilder
import blur.tech.candidate.core.models.Initiative
import blur.tech.candidate.core.models.Vote
import blur.tech.candidate.features.initiative.orgshow.OrgInitiativeScreenView
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
class InitiativeScreenPresenter : MvpPresenter<OrgInitiativeScreenView>() {

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var prefs: SharedPreferences

    private val api: SwipeApi

    init {
        App.INSTANCE.getAppComponent().inject(this)
        api = retrofit.create(SwipeApi::class.java)
    }

    fun checkInitiative(initiative: Initiative): Boolean =
        PreferencesApi.getUser(prefs)!!.username == initiative.creator

    fun superLike(initiative: Initiative) {
        api.swipe(
            TokenBuilder.build(PreferencesApi.getJwt(prefs)!!),
            Vote(initiative._id, Vote.voteTypes.Superlike.toString())
        ).enqueue(object : Callback<Wrapper<Void>>{

            override fun onResponse(call: Call<Wrapper<Void>>, response: Response<Wrapper<Void>>) {
                if (response.isSuccessful)
                    viewState.likeDone()
            }

            override fun onFailure(call: Call<Wrapper<Void>>, t: Throwable) {
                viewState.showMessage("Ошибка")
            }
        })
    }

}
package blur.tech.candidate.core


import blur.tech.candidate.core.modules.ApiModule
import blur.tech.candidate.core.modules.SharedPreferencesModule
import blur.tech.candidate.features.MainActivityPresenter
import blur.tech.candidate.features.initiative.create.CreateInitiativePresenter
import blur.tech.candidate.features.profile.ProfilePresenter
import blur.tech.candidate.features.profile.fragments.menu.ProfileMenuFragment
import blur.tech.candidate.features.profile.fragments.mytrips.MyInitiativesPresenter
import blur.tech.candidate.features.swipe.SwipePresenter
import dagger.Component
import tech.blur.redline.features.signin.SignInPresenter
import javax.inject.Singleton


@Singleton
@Component(modules = [ApiModule::class, SharedPreferencesModule::class])
interface AppComponent {
    fun inject(signInPresenter: SignInPresenter)
    fun inject(mainActivityPresenter: MainActivityPresenter)
    fun inject(createInitiativePresenter: CreateInitiativePresenter)
    fun inject(swipePresenter: SwipePresenter)
    fun inject(profilePresenter: ProfilePresenter)
    fun inject(myInitiativesPresenter: MyInitiativesPresenter)
    fun inject(profileMenuFragment: ProfileMenuFragment)
}


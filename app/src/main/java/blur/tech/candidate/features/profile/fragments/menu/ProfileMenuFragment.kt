package blur.tech.candidate.features.profile.fragments.menu

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import blur.tech.candidate.App
import blur.tech.candidate.R
import blur.tech.candidate.core.PreferencesApi
import blur.tech.candidate.features.MainFlowFragment
import kotlinx.android.synthetic.main.fragment_profile_menu.view.*
import tech.blur.redline.features.BaseFragment
import tech.blur.redline.features.signin.SignInFragment
import tripple.me.features.profile.fragments.menu.ProfileMenuView
import javax.inject.Inject

class ProfileMenuFragment : BaseFragment(), ProfileMenuView {

    @Inject
    lateinit var prefs:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.INSTANCE.getAppComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(getLayoutID(), container, false)

        v.menu_edit_logout.setOnClickListener {
            logout()
        }

        return v
    }

    private fun logout() {
        PreferencesApi.delData(prefs)
        activity!!.supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, SignInFragment.newInstance(), "SIGN_IN")
            .commit()
    }

    override fun navigateTo(screen: String) {
//        lateinit var fr: Fragment
//        when (screen) {
//            FragmentTags.EDIT_PROFILE -> fr = EditProfileFragment.newInstance()
//
//        }
//        activity!!.supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.mainContainer, fr, screen)
//            .addToBackStack(null)
//            .commit()
    }

    override fun getLayoutID() = R.layout.fragment_profile_menu
}
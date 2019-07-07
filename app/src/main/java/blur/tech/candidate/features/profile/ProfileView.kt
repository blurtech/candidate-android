package blur.tech.candidate.features.profile

import blur.tech.candidate.core.models.User
import com.arellomobile.mvp.MvpView

interface ProfileView: MvpView {
    fun setUser(body: User)
    fun showMessage(s: String)

}

package blur.tech.candidate.features

import com.arellomobile.mvp.MvpView

interface MainActivityView: MvpView {
    fun authSuccessful()
    fun authFailed()
    fun showMessage(s: String)
}
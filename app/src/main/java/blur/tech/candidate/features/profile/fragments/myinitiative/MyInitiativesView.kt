package blur.tech.candidate.features.profile.fragments.myinitiative

import blur.tech.candidate.core.models.Initiative
import com.arellomobile.mvp.MvpView

interface MyInitiativesView : MvpView {
    fun showProgress()
    fun showMessage(s: String)
    fun showTrips(list: ArrayList<Initiative>)
    fun hideProgress()
//    fun navigateTo(screen: String, trip: Trip?)
}
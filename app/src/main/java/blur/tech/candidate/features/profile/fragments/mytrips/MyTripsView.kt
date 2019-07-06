package blur.tech.candidate.features.profile.fragments.mytrips

import com.arellomobile.mvp.MvpView

interface MyTripsView : MvpView {
    fun showProgress()
    fun showMessage(s: String)
//    fun showTrips(list: List<Trip>)
    fun hideProgress()
//    fun navigateTo(screen: String, trip: Trip?)
}
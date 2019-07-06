package blur.tech.candidate.features.initiative

import com.arellomobile.mvp.MvpView

interface CreateInitiativeView: MvpView{
    fun showMessage(s: String)
    fun createSuccessful()

}

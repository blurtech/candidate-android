package blur.tech.candidate.features.initiative.orgshow

import com.arellomobile.mvp.MvpView

interface OrgInitiativeScreenView: MvpView{
    fun likeDone()
    fun showMessage(s: String)

}

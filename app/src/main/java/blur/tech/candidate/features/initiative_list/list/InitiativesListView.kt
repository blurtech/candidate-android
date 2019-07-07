package blur.tech.candidate.features.initiative_list.list

import blur.tech.candidate.core.models.OrgInitiative
import com.arellomobile.mvp.MvpView

interface InitiativesListView: MvpView {
    fun showMessage(s: String)
    fun onListReady(body: ArrayList<OrgInitiative>)

}

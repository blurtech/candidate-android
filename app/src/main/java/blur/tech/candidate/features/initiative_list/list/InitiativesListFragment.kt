package blur.tech.candidate.features.initiative_list.list

import blur.tech.candidate.R
import tech.blur.redline.features.BaseFragment

class InitiativesListFragment: BaseFragment(), InitiativesListView{
    override fun getLayoutID() = R.layout.fragment_initiative_list

    companion object{
        fun newInstance() = InitiativesListFragment()
    }
}
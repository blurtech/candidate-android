package blur.tech.candidate.features.initiative_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import blur.tech.candidate.R
import blur.tech.candidate.features.initiative_list.list.InitiativesListFragment
import blur.tech.candidate.features.initiative_list.swipe.SwipeFragment
import kotlinx.android.synthetic.main.fragment_flow_list.view.*
import tech.blur.redline.features.BaseFragment

class InitiativesFlowFragment : BaseFragment() {

    private enum class Screen { SWIPE, ORG }

    private var currentScreen = Screen.SWIPE


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutID(), container, false)

        childFragmentManager.beginTransaction()
            .replace(R.id.initiativeContainer, SwipeFragment.newInstance())
            .commit()

        view.screenSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked)
                childFragmentManager.beginTransaction()
                    .replace(R.id.initiativeContainer, InitiativesListFragment.newInstance())
                    .commit()
            else
                childFragmentManager.beginTransaction()
                    .replace(R.id.initiativeContainer, SwipeFragment.newInstance())
                    .commit()
        }
//            currentScreen = if (currentScreen == Screen.SWIPE) {
//                childFragmentManager.beginTransaction()
//                    .replace(R.id.initiativeContainer, InitiativesListFragment.newInstance())
//                    .commit()
//                Screen.ORG
//            } else {
//                childFragmentManager.beginTransaction()
//                    .replace(R.id.initiativeContainer, SwipeFragment.newInstance())
//                    .commit()
//                Screen.SWIPE
//            }


        return view
    }

    override fun getLayoutID() = R.layout.fragment_flow_list


    companion object {
        fun newInstance() = InitiativesFlowFragment()
    }
}

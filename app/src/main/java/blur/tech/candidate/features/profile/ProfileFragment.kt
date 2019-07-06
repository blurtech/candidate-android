package blur.tech.candidate.features.profile

import blur.tech.candidate.R
import tech.blur.redline.features.BaseFragment

class ProfileFragment: BaseFragment(), ProfileView {
    override fun getLayoutID() = R.layout.fragment_profile

    companion object{
        fun newInstance() = ProfileFragment()
    }
}
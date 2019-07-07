package blur.tech.candidate.features.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import blur.tech.candidate.R
import blur.tech.candidate.core.models.User
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_profile.view.*
import tech.blur.redline.features.BaseFragment
import blur.tech.candidate.features.profile.fragments.menu.ProfileMenuFragment
import blur.tech.candidate.features.profile.fragments.mytrips.MyInitiativesFragment

class ProfileFragment : BaseFragment(), ProfileView {

    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    lateinit var login: TextView
    lateinit var city: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutID(), container, false)

        login = view.profile_username
        city = view.profile_user_rank

        presenter.loadUser()

        val adapter = ProfileFragmentPagerAdapter(childFragmentManager)
        adapter.addFragment(MyInitiativesFragment())
        adapter.addFragment(ProfileMenuFragment())
        view.profile_pager.adapter = adapter
        view.profile_tabs.setupWithViewPager(view.profile_pager)
        view.profile_tabs.getTabAt(0)!!.setIcon(R.drawable.ic_my_trips)
        view.profile_tabs.getTabAt(1)!!.setIcon(R.drawable.ic_profile_menu)

        return view
    }

    override fun showMessage(s: String) {
        Toast.makeText(activity, s, Toast.LENGTH_SHORT).show()
    }

    override fun setUser(body: User) {
        login.text = body.username
        city.text = "Шоха"// body.geo.city
    }

    override fun getLayoutID() = R.layout.fragment_profile

    inner class ProfileFragmentPagerAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val PAGE_COUNT = 2

        private val tabList = mutableListOf<Fragment>()

        fun addFragment(fragment: Fragment) {
            tabList.add(fragment)
            notifyDataSetChanged()
        }

        override fun getCount(): Int {
            return PAGE_COUNT
        }

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> tabList[0]
                1 -> tabList[1]
                else -> tabList[0]
            }
        }


    }

    companion object {
        fun newInstance() = ProfileFragment()
    }
}
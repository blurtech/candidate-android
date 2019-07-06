package blur.tech.candidate.features

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import blur.tech.candidate.R
import blur.tech.candidate.core.NonSwipeableViewPager
import blur.tech.candidate.features.initiative.CreateInitiativeFragment
import blur.tech.candidate.features.profile.ProfileFragment
import blur.tech.candidate.features.swipe.SwipeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_main_flow.*
import kotlinx.android.synthetic.main.fragment_main_flow.view.*
import tech.blur.redline.features.BaseFragment

class MainFlowFragment : BaseFragment() {

    lateinit var pager: NonSwipeableViewPager
    private lateinit var adapter: ViewPagerAdapter

    private enum class Screen { MAIN_FEED, PROFILE }
    private var currentScreen = Screen.MAIN_FEED

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter = ViewPagerAdapter(childFragmentManager)
    }

    override fun onResume() {
        super.onResume()
        navigation.menu.findItem(R.id.navigation_new).isChecked = false
        if (currentScreen == Screen.MAIN_FEED)
            navigation.menu.findItem(R.id.navigation_feed).isChecked = true
        else
            navigation.menu.findItem(R.id.navigation_profile).isChecked = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutID(), container, false)

        pager = view.mainPager

        view.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        pager.adapter = adapter

        return view
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_feed -> {
                if (currentScreen != Screen.MAIN_FEED) {
                    pager.setCurrentItem(0, false)
                    currentScreen = Screen.MAIN_FEED
                    return@OnNavigationItemSelectedListener true
                }
            }
            R.id.navigation_new -> {
                activity!!.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.mainContainer, CreateInitiativeFragment.newInstance(), "CREATE_INITIATIVE")
                    .addToBackStack(null)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                if (currentScreen != Screen.PROFILE) {
                    pager.setCurrentItem(1, false)
                    currentScreen = Screen.PROFILE
                    return@OnNavigationItemSelectedListener true
                }
            }
        }

        false

    }

    override fun getLayoutID() = R.layout.fragment_main_flow

    inner class ViewPagerAdapter(manager: FragmentManager) :
        FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        private val mFragmentList = ArrayList<Fragment>()

        init {
            mFragmentList.add(SwipeFragment.newInstance())
            mFragmentList.add(ProfileFragment.newInstance())

        }

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun setFragment(fm: Fragment) {
            mFragmentList.add(fm)
        }

        fun clearFragments() {
            mFragmentList.clear()
        }
    }

    companion object {
        fun newInstance() = MainFlowFragment()
    }

}
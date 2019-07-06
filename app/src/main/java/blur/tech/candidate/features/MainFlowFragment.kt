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
import blur.tech.candidate.features.swipe.SwipeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_main_flow.view.*
import tech.blur.redline.features.BaseFragment

class MainFlowFragment : BaseFragment() {

    lateinit var pager: NonSwipeableViewPager
    private lateinit var adapter: ViewPagerAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter = ViewPagerAdapter(childFragmentManager)
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
            R.id.navigation_home -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {

                return@OnNavigationItemSelectedListener true
            }
        }

        false

    }

    override fun getLayoutID() = R.layout.fragment_main_flow

    inner class ViewPagerAdapter(manager: FragmentManager) :
        FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        private val mFragmentList = ArrayList<Fragment>()

        init {
//            mFragmentList.add(ProfileFragment.newInstance())
//            mFragmentList.add(SwipeFragment.newInstance())
//            mFragmentList.add(CreateInitiativeFragment.newInstance())
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
        fun NewInstance() = MainFlowFragment()
    }

}
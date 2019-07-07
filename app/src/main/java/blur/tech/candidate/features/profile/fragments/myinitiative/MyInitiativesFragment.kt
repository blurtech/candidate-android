package blur.tech.candidate.features.profile.fragments.myinitiative

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import blur.tech.candidate.R
import blur.tech.candidate.core.models.Initiative
import blur.tech.candidate.features.MainActivity
import blur.tech.candidate.features.initiative.show.InitiativeScreenFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.profile_my_innitiatives.view.*
import tech.blur.redline.features.BaseFragment

class MyInitiativesFragment : BaseFragment(), MyInitiativesView {


    lateinit var activityContext: MainActivity

    private lateinit var initiativeAdapter: InitiativeAdapter
    private lateinit var profileRecyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    @InjectPresenter
    lateinit var myInitiativesPresenter: MyInitiativesPresenter


    private lateinit var emptyTrips: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(getLayoutID(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileRecyclerView = view.findViewById(R.id.my_trips_recycler)
        swipeRefreshLayout = view.findViewById(R.id.my_trips_swipe_refresh)
        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        emptyTrips = view.profile_mytrips_empty

        emptyTrips.visibility = View.VISIBLE

        initiativeAdapter = InitiativeAdapter(object : InitiativeAdapter.InitiativeClickListener {
            override fun onInitiativeClickListener(initiative: Initiative) {
                activity!!.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.mainContainer, InitiativeScreenFragment.newInstance(initiative))
                    .addToBackStack(null)
                    .commit()
            }
        })

        swipeRefreshLayout.setOnRefreshListener { myInitiativesPresenter.refresh() }
        profileRecyclerView.layoutManager = layoutManager
        myInitiativesPresenter.loadInitiatives()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityContext = context as MainActivity
    }

//    override fun navigateTo(screen: String, trip: Trip?) {
////        val fr = TripScreenFragment.newInstance(trip!!)
////
////        activity!!.supportFragmentManager
////            .beginTransaction()
////            .replace(R.id.main_container, fr, screen)
////            .addToBackStack(null)
////            .commit()
//    }

    override fun getLayoutID() = R.layout.profile_my_innitiatives

    override fun showMessage(s: String) {
        Toast.makeText(activity, s, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun showTrips(list: ArrayList<Initiative>) {
        if (list.isEmpty()) {
            emptyTrips.visibility = View.VISIBLE
        } else {
            emptyTrips.visibility = View.GONE
            initiativeAdapter.setInitiatives(list)
            profileRecyclerView.adapter = initiativeAdapter
            initiativeAdapter.notifyDataSetChanged()
        }
    }

    override fun hideProgress() {
        swipeRefreshLayout.isRefreshing = false
    }
}
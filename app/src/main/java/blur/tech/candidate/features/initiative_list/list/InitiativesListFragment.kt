package blur.tech.candidate.features.initiative_list.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import blur.tech.candidate.R
import blur.tech.candidate.core.InitiativeAdapter
import blur.tech.candidate.core.models.Initiative
import blur.tech.candidate.core.models.OrgInitiative
import blur.tech.candidate.features.initiative.orgshow.OrgInitiativeScreenFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_initiative_list.*
import kotlinx.android.synthetic.main.fragment_initiative_list.view.*
import tech.blur.redline.features.BaseFragment
import javax.inject.Inject

class InitiativesListFragment: BaseFragment(), InitiativesListView{

    @InjectPresenter
    lateinit var presenter: InitiativesListPresenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var initiativeAdapter: OrgInitiativeAdapter

    private var init: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutID(), container, false)

        recyclerView = view.recycler
        swipeRefresh = view.swipeRefresh
        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        recyclerView.layoutManager = layoutManager

        if (!init) {
            initiativeAdapter = OrgInitiativeAdapter(object : OrgInitiativeAdapter.InitiativeClickListener {
                override fun onInitiativeClickListener(initiative: OrgInitiative) {
                    activity!!.supportFragmentManager.beginTransaction()
                        .replace(R.id.mainContainer, OrgInitiativeScreenFragment.newInstance(initiative))
                        .addToBackStack(null)
                        .commit()
                }
            })
            presenter.onRefresh()
        }

        recyclerView.adapter = initiativeAdapter

        return view
    }
    override fun showMessage(s: String) {
        Toast.makeText(activity, s, Toast.LENGTH_SHORT).show()
    }

    override fun onListReady(body: ArrayList<OrgInitiative>) {
        init = true
        initiativeAdapter.clear()
        initiativeAdapter.setInitiatives(body)
        initiativeAdapter.notifyDataSetChanged()
    }



    override fun getLayoutID() = R.layout.fragment_initiative_list

    companion object{
        fun newInstance() = InitiativesListFragment()
    }
}
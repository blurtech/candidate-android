package blur.tech.candidate.features.initiative.show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import blur.tech.candidate.R
import blur.tech.candidate.core.models.Initiative
import blur.tech.candidate.features.initiative.orgshow.OrgInitiativeScreenView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_initiative_screen.view.*
import tech.blur.redline.features.BaseFragment

class InitiativeScreenFragment : BaseFragment(), OrgInitiativeScreenView {


    @InjectPresenter
    lateinit var presenter: InitiativeScreenPresenter

    lateinit var initiative: Initiative

    private lateinit var superLikeButton: Button


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutID(), container, false)

        superLikeButton = view.buttonSuperLike

        val bundle = this.arguments
        if (bundle != null) {
            val tmpJson = bundle.getString("Trip")
            initiative = Gson().fromJson(tmpJson, Initiative::class.java)
            view.initiativeScreenTitle.text = initiative.title
            view.initiativeScreenDesc.text = initiative.describe
            if (presenter.checkInitiative(initiative))
                superLikeButton.visibility = View.GONE
            else
                superLikeButton.setOnClickListener{
                    presenter.superLike(initiative)
                }
        }

        return view
    }

    override fun likeDone() {
        superLikeButton.isEnabled = false
    }

    override fun showMessage(s: String) {
        Toast.makeText(activity!!, s, Toast.LENGTH_SHORT).show()
    }

    override fun getLayoutID() = R.layout.fragment_initiative_screen

    companion object {
        fun newInstance(initiative: Initiative): InitiativeScreenFragment {
            val fragment = InitiativeScreenFragment()
            val bundle = Bundle()
            bundle.putString("Trip", Gson().toJson(initiative))
            fragment.arguments = bundle
            return fragment
        }
    }
}
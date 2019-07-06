package blur.tech.candidate.features.initiative

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import blur.tech.candidate.R
import blur.tech.candidate.core.DefaultTextWatcher
import blur.tech.candidate.features.MainActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_new_initiative.view.*
import tech.blur.redline.features.BaseFragment

class CreateInitiativeFragment: BaseFragment(), CreateInitiativeView {

    @InjectPresenter
    lateinit var presenter: CreateInitiativePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutID(), container, false)

        view.create_initiative_desc_text.addTextChangedListener(object: DefaultTextWatcher(){
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               presenter.desc = s!!.toString()
            }
        })

        view.create_initiative_desc_text.addTextChangedListener(object: DefaultTextWatcher(){
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.title = s!!.toString()
            }
        })

        view.create_initiative_button.setOnClickListener{presenter.createInitiative()}

        return view
    }

    override fun showMessage(s: String) {
        Toast.makeText(activity, s, Toast.LENGTH_SHORT).show()
    }

    override fun createSuccessful() {
        (activity as MainActivity).onBackPressed()
    }


    override fun getLayoutID() = R.layout.fragment_new_initiative

    companion object {
        fun newInstance() = CreateInitiativeFragment()
    }
}
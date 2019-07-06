package tech.blur.redline.features.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import blur.tech.candidate.R
import blur.tech.candidate.core.DefaultTextWatcher
import blur.tech.candidate.features.MainFlowFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_auth.view.button_signin
import kotlinx.android.synthetic.main.fragment_signin.view.*
import tech.blur.redline.features.BaseFragment

class SignInFragment : BaseFragment(), SignInView {

    @InjectPresenter
    lateinit var presenter: SignInPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutID(), container, false)

        view.edit_signin_login.addTextChangedListener(object : DefaultTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.login = s!!.toString()
            }
        })

        view.edit_signin_password.addTextChangedListener(object : DefaultTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.password = s!!.toString()
            }
        })

        view.button_signin.setOnClickListener {
            presenter.signInUser()
        }

        return view
    }

    override fun showMessage(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }

    override fun onUserAuthDone() {
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, MainFlowFragment.newInstance(), "MAIN_FLOW")
            .commit()
    }


    override fun getLayoutID() = R.layout.fragment_signin

    companion object {
        fun newInstance() = SignInFragment()
    }

}
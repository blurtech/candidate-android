package blur.tech.candidate.features.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import blur.tech.candidate.R
import kotlinx.android.synthetic.main.fragment_auth.view.*
import tech.blur.redline.features.BaseFragment
import tech.blur.redline.features.signin.SignInFragment


class AuthFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(getLayoutID(), container, false)


        view.button_gos.setOnClickListener{

        }

        view.button_signin.setOnClickListener{
            fragmentManager!!.beginTransaction()
                .replace(R.id.mainContainer, SignInFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }

        return view
    }

    override fun getLayoutID() = R.layout.fragment_auth

    companion object {
        fun newInstance() = AuthFragment()
    }

}


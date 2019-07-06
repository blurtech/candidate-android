package blur.tech.candidate.features

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import blur.tech.candidate.R
import blur.tech.candidate.features.auth.AuthFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import tech.blur.redline.features.BaseActivity

class MainActivity : BaseActivity(), MainActivityView {

    @InjectPresenter
    lateinit var preseter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preseter.checkAuth()

    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount

        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    override fun authSuccessful() {
        mainProgressBar.visibility = View.GONE
        navigate(true)
    }

    private fun navigate(isAuth: Boolean) {

        val fragment = if (isAuth){
            MainFlowFragment.newInstance()
        } else {
            AuthFragment.newInstance()
        }

        supportFragmentManager.beginTransaction()
            .add(getContainerId(), fragment, if (fragment is MainFlowFragment) "MAIN_FLOW_FRAGMENT" else "AUTH_FRAGMENT")
            .commit()

    }

    override fun authFailed() {
        mainProgressBar.visibility = View.GONE
        navigate(false)
    }

    override fun showMessage(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show()
    }

    override fun getLayoutId() = R.layout.activity_main

    override fun getContainerId() = R.id.mainContainer
}

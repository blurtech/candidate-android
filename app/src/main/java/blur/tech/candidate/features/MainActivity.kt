package blur.tech.candidate.features

import android.os.Bundle
import blur.tech.candidate.R
import blur.tech.candidate.features.auth.AuthFragment
import tech.blur.redline.features.BaseActivity
import tech.blur.redline.features.signin.SignInFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(getContainerId(), AuthFragment.newInstance(), "MAIN_FRAGMENT_FLOW")
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStack()
    }

    override fun getLayoutId() = R.layout.activity_main

    override fun getContainerId() = R.id.mainContainer
}

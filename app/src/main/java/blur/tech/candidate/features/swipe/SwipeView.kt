package blur.tech.candidate.features.swipe

import blur.tech.candidate.core.models.Initiative
import blur.tech.candidate.network.Wrapper
import com.arellomobile.mvp.MvpView

interface SwipeView: MvpView{
    fun onListReady(list: ArrayList<Initiative>)
    fun showMessage(s: String)
    fun addLast(list: ArrayList<Initiative>)
    fun paginate(list: ArrayList<Initiative>)
    fun refresh(data: ArrayList<Initiative>)

}

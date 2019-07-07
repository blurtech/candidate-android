package blur.tech.candidate.features.initiative_list.swipe

import blur.tech.candidate.core.models.Initiative
import com.arellomobile.mvp.MvpView

interface SwipeView: MvpView{
    fun onListReady(list: ArrayList<Initiative>)
    fun showMessage(s: String)
    fun addLast(list: ArrayList<Initiative>)
    fun paginate(list: ArrayList<Initiative>)
    fun refresh(data: ArrayList<Initiative>)

}

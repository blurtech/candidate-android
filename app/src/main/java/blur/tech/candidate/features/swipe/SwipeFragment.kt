package blur.tech.candidate.features.swipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import blur.tech.candidate.R
import blur.tech.candidate.core.models.Initiative
import blur.tech.candidate.features.initiative.show.InitiativeScreenFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.yuyakaido.android.cardstackview.*
import kotlinx.android.synthetic.main.fragment_swipe.view.*
import tech.blur.redline.features.BaseFragment

class SwipeFragment : BaseFragment(), SwipeView, CardStackListener {

    @InjectPresenter
    lateinit var presenter: SwipePresenter

    lateinit var cardStack: CardStackView

    lateinit var adapter: CardStackAdapter

    private var isInit = false

    private val manager by lazy { CardStackLayoutManager(activity, this) }

    override fun onResume() {
        super.onResume()
        if (isInit) presenter.refreshList()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutID(), container, false)

        cardStack = view.swipeList

        if (!isInit)
            presenter.getSwipeBunch()

        return view
    }

    override fun addLast(list: ArrayList<Initiative>) {
        val old = adapter.getInitiatives()
        val new = mutableListOf<Initiative>().apply {
            addAll(old)
            addAll(list)
        }
        val callback = InitiativeDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setInitiatives(new)
        result.dispatchUpdatesTo(adapter)
    }

    override fun onListReady(list: ArrayList<Initiative>) {
        adapter = CardStackAdapter(list, object : CardStackAdapter.InitiativeClickListener {
            override fun onInitiativeClicked(initiative: Initiative) {
                activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.mainContainer, InitiativeScreenFragment.newInstance(initiative))
                    .addToBackStack(null)
                    .commit()
            }

        })
        cardStack.adapter = adapter
        if (!isInit) {

            initialize()
        }

        isInit = true
    }

    override fun refresh(data: ArrayList<Initiative>) {
        adapter.setInitiatives(data)
        adapter.notifyDataSetChanged()
    }


    override fun paginate(list: ArrayList<Initiative>) {
        val old = adapter.getInitiatives()
        val new = ArrayList<Initiative>()//old.plus(list)
        list.forEach {
            if (!old.contains(it)) new.add(it)
        }
        val callback = InitiativeDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setInitiatives(new)
        adapter.notifyDataSetChanged()
//        result.dispatchUpdatesTo(adapter)
    }

    private fun initialize() {
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setStackFrom(StackFrom.None)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setSwipeableMethod(SwipeableMethod.Manual)
        manager.setOverlayInterpolator(LinearInterpolator())
        cardStack.layoutManager = manager
        cardStack.adapter = adapter
        cardStack.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
        cardStack.setOnClickListener {

        }
    }


    override fun showMessage(s: String) {
        Toast.makeText(activity, s, Toast.LENGTH_SHORT).show()
    }

    override fun getLayoutID() = R.layout.fragment_swipe

    override fun onCardSwiped(direction: Direction?) {
        presenter.onSwiped(adapter.getInitiative(manager.topPosition - 1), direction.toString())
        if (manager.topPosition == adapter.itemCount - 2) {
            presenter.paginate()
        }
    }

    override fun onCardCanceled() {}

    override fun onCardDisappeared(view: View?, position: Int) {}

    override fun onCardDragging(direction: Direction?, ratio: Float) {}

    override fun onCardAppeared(view: View?, position: Int) {}

    override fun onCardRewound() {}

    companion object {
        fun newInstance() = SwipeFragment()
    }


    inner class InitiativeDiffCallback(
        private val old: List<Initiative>,
        private val new: List<Initiative>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return old.size
        }

        override fun getNewListSize(): Int {
            return new.size
        }

        override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
            return old[oldPosition]._id == new[newPosition]._id
        }

        override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
            return old[oldPosition] == new[newPosition]
        }

    }

}
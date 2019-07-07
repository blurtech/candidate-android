package blur.tech.candidate.features.profile.fragments.myinitiative

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import blur.tech.candidate.R
import blur.tech.candidate.core.models.Initiative
import java.util.*

class InitiativeAdapter(private val inititativeClickListener: InitiativeClickListener) :
    RecyclerView.Adapter<InitiativeAdapter.MainFeedHolder>() {
    private val initiatives = ArrayList<Initiative>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFeedHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_initiative, parent, false)
        return MainFeedHolder(itemView, inititativeClickListener)
    }

    fun setInitiatives(eventList: List<Initiative>?) {
        initiatives.clear()
        if (eventList != null) initiatives.addAll(eventList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MainFeedHolder, position: Int) {
        holder.bind(initiatives[position])
    }

    override fun getItemCount(): Int {
        return initiatives.size
    }

    inner class MainFeedHolder(itemView: View, internal var initiativeClickListener: InitiativeClickListener) :
        RecyclerView.ViewHolder(itemView) {
        private val description: TextView = itemView.findViewById(R.id.itemDesc)
        private val title: TextView = itemView.findViewById(R.id.itemTitle)
        private val rating: TextView = itemView.findViewById(R.id.itemRating)
        private val cardView: CardView = itemView.findViewById(R.id.itemCardView)

        internal fun bind(initiative: Initiative) {
            title.text = initiative.title
            description.text = initiative.describe
            rating.text = initiative.rating.toString()

            when (initiative.rating) {
                in Int.MIN_VALUE..-1 -> rating.setTextColor(Color.parseColor("#FFF44336"))
                0 -> rating.setTextColor(Color.parseColor("#8D000000"))
                else -> rating.setTextColor(Color.parseColor("#FF4CAF50"))
            }

            cardView.setOnClickListener { initiativeClickListener.onInitiativeClickListener(initiative) }

        }
    }

    interface InitiativeClickListener {
        fun onInitiativeClickListener(initiative: Initiative)
    }


}

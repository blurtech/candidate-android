package blur.tech.candidate.features.initiative_list.swipe

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import blur.tech.candidate.R
import blur.tech.candidate.core.models.Initiative

class CardStackAdapter(
    private var initiatives: ArrayList<Initiative> = ArrayList(
        emptyList()
    )

) : RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_initiative_swipe, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val initiative = initiatives[position]
        holder.title.text = initiative.title
        holder.desc.text = initiative.describe
        holder.rating.text = initiative.rating.toString()
        when (initiative.rating) {
            in Int.MIN_VALUE..-1 -> holder.rating.setTextColor(Color.parseColor("#FFF44336"))
            0 -> holder.rating.setTextColor(Color.parseColor("#8D000000"))
            else -> holder.rating.setTextColor(Color.parseColor("#FF4CAF50"))
        }
    }

    override fun getItemCount(): Int {
        return initiatives.size
    }

    fun getInitiative(pos: Int) = initiatives[pos]

    fun setInitiatives(initiative: List<Initiative>) {
        this.initiatives.addAll(initiative)
    }

    fun getInitiatives(): List<Initiative> {
        return initiatives
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.itemTitle)
        val desc: TextView = view.findViewById(R.id.itemDesc)
        val rating: TextView = view.findViewById(R.id.itemRating)


    }

    interface InitiativeClickListener {
        fun onInitiativeClicked(initiative: Initiative)
    }
}

package com.synrgy.wefly.ui.flight

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.synrgy.wefly.data.api.json.flight.FlightContent
import com.synrgy.wefly.databinding.ItemFlightBinding

class FlightAdapter(
    private var listItem: List<FlightContent>,
    private val listener: FlightListener
): RecyclerView.Adapter<FlightAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: ItemFlightBinding) : RecyclerView.ViewHolder(binding.root)

    interface FlightListener {
        fun onItemClick(item: FlightContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            ItemFlightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val context = holder.itemView.context
        holder.binding.apply {
            val itemPosition = listItem[position]
            tvAirportId.text = itemPosition.flight.arrivalAirport?.id.toString()
            tvAirline.text = itemPosition.flight.airplane?.airline?.name
            tvSeatClass.text = itemPosition.seatClass
            tvDepartCity.text = itemPosition.flight.departureAirport?.city
            tvDepartTime.text = itemPosition.flight.departureTime
            tvArrivalCity.text = itemPosition.flight.arrivalAirport?.city
            tvArrivalTime.text = itemPosition.flight.arrivalTime
            tvPrice.text = itemPosition.flight.basePrice.toString()
            root.setOnClickListener { listener.onItemClick(itemPosition) }
        }
    }
}
package com.synrgy.wefly.ui.flight

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.synrgy.wefly.data.api.flight.FlightContent
import com.synrgy.wefly.databinding.ItemFlightBinding

class FlightAdapter(
    private var listItem: List<FlightContent>
): RecyclerView.Adapter<FlightAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: ItemFlightBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            ItemFlightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val context = holder.itemView.context
        holder.binding.apply {
            val itemPosition = listItem[position]
            tvAirline.text = itemPosition.flight.airplane.airline.name
            tvSeatClass.text = itemPosition.seatClass
            tvDepartCity.text = itemPosition.flight.arrivalAirport.city
            tvDepartTime.text = itemPosition.flight.departureTime
            tvArrivalCity.text = itemPosition.flight.arrivalAirport.city
            tvDepartTime.text = itemPosition.flight.arrivalTime
        }
    }
}
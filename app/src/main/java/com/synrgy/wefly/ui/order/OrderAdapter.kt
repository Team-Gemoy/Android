package com.synrgy.wefly.ui.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.synrgy.wefly.data.api.json.transaction.TransactionListResponse
import com.synrgy.wefly.databinding.ItemOrderBinding

class OrderAdapter(
    private var listItem: List<TransactionListResponse>,
    private val listener: OrderListener
): RecyclerView.Adapter<OrderAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root)

    interface OrderListener {
        fun onItemClick(item: TransactionListResponse)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val context = holder.itemView.context
        holder.binding.apply {
            val itemPosition = listItem[position]
            tvAirportId.text = itemPosition.orderer.firstName //.flight.arrivalAirport?.id.toString()
           /* tvAirline.text = itemPosition.flight.airplane?.airline?.name
            tvSeatClass.text = itemPosition.seatClass
            tvDepartCity.text = itemPosition.flight.departureAirport?.city
            tvDepartTime.text = itemPosition.flight.departureTime
            tvArrivalCity.text = itemPosition.flight.departureAirport?.city
            tvArrivalTime.text = itemPosition.flight.arrivalTime
            tvPrice.text = itemPosition.flight.basePrice.toString()*/
            root.setOnClickListener { listener.onItemClick(itemPosition) }
        }
    }
}
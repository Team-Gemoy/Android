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
            tvFullName.text = "${itemPosition.orderer.firstName} ${itemPosition.orderer.lastName}"
            tvEmail.text = itemPosition.orderer.email
            tvPhoneNumber.text = itemPosition.orderer.phoneNumber
            val transactionId = itemPosition.id
            tvTitle.text = "Transaction #$transactionId"
           // tv.text = itemPosition.paymentProof
         //   tvDepartTime.text = itemPosition.passengers[0].firstName
          /*  tvArrivalCity.text = itemPosition.flight.departureAirport?.city
            tvArrivalTime.text = itemPosition.flight.arrivalTime*/
            tvPrice.text = itemPosition.totalPrice.toString()
            root.setOnClickListener { listener.onItemClick(itemPosition) }
        }
    }
}
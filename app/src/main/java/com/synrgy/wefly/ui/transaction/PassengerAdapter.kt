package com.synrgy.wefly.ui.transaction

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.synrgy.wefly.data.api.transaction.Passenger
import com.synrgy.wefly.databinding.ItemPassengerBinding

class PassengerAdapter(
    private var listItem: List<Passenger>,
    private val listener: PassengerListener
): RecyclerView.Adapter<PassengerAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: ItemPassengerBinding) : RecyclerView.ViewHolder(binding.root)

    interface PassengerListener {
        fun onItemClick(item: Passenger)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            ItemPassengerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val context = holder.itemView.context
        holder.binding.apply {
            val itemPosition = listItem[position]
            tvFirstName.text = itemPosition.firstName
            tvLastName.text = itemPosition.lastName
            tvDateOfBirthPassenger.text = itemPosition.dateOfBirth
            tvNationalityPassenger.text = itemPosition.nationality
            root.setOnClickListener { listener.onItemClick(itemPosition) }
        }
    }
}
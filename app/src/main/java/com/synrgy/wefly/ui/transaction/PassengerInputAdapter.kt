package com.synrgy.wefly.ui.transaction

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.synrgy.wefly.common.showDatePickerDialog
import com.synrgy.wefly.data.api.json.transaction.Passenger
import com.synrgy.wefly.databinding.ItemPassengerInputBinding

class PassengerInputAdapter(
    private var listItem: MutableList<Passenger>,
    private val listener: PassengerListener,
): RecyclerView.Adapter<PassengerInputAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: ItemPassengerInputBinding) : RecyclerView.ViewHolder(binding.root)

    interface PassengerListener {
        fun onItemClick(item: Passenger)
    }

    fun updateData(newList: List<Passenger>) {
        listItem.clear()
        listItem.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            ItemPassengerInputBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val context = holder.itemView.context
        holder.binding.apply {
            val itemPosition = listItem[position]

            etDateOfBirthPassenger.setOnClickListener {
                showDatePickerDialog(context, etDateOfBirthPassenger)
            }

            itemPosition.firstName = etFirstName.text.toString()
            itemPosition.lastName = etLastName.text.toString()
            itemPosition.dateOfBirth = etDateOfBirthPassenger.text.toString()
           // itemPosition.nationality = etNationalityPassenger.text.toString()

            root.setOnClickListener {
                listener.onItemClick(itemPosition)
            }
        }
    }
}
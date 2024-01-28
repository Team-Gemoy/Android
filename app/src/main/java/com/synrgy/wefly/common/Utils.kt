package com.synrgy.wefly.common

import android.R
import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun LifecycleOwner.repeatCollectionOnCreated(block: suspend () -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.CREATED) {
            block()
        }
    }
}


fun showDatePickerDialog(context: Context, birth: TextView) {

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, monthOfYear, dayOfMonth ->

            val selectedDate = Calendar.getInstance().apply {
                set(Calendar.YEAR, year)
                set(Calendar.MONTH, monthOfYear)
                set(Calendar.DAY_OF_MONTH, dayOfMonth)

            }
            selectedDate.set(year, monthOfYear, dayOfMonth)

            val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val formattedDate = dateFormat.format(selectedDate.time)
            birth.text = formattedDate

        },
        Calendar.getInstance().get(Calendar.YEAR),
        Calendar.getInstance().get(Calendar.MONTH),
        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    )

    datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
    datePickerDialog.show()
}

fun spinnerAdapter(array: Array<*>, spinner: Spinner, context: Context, onItemSelected: (position: Int) -> Unit){
    val arrayAdapter = ArrayAdapter(context, R.layout.simple_spinner_dropdown_item, array)
    spinner.adapter = arrayAdapter
    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
         //   Log.d("neotica", array[position].toString())
            onItemSelected(position)
        }
        override fun onNothingSelected(parent: AdapterView<*>?) {
            Log.d("neotica", "Nothing is selected")
        }
    }
}
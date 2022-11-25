package com.devajaykr.birthaycalculator

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    var tvSelectedDate: TextView? = null
    var tvAgeInMin: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(/* layoutResID = */ R.layout.activity_main)
        val datepicker: Button = findViewById(R.id.datepicker)
        tvSelectedDate = findViewById(R.id.tvSelectDate)
        tvAgeInMin = findViewById(R.id.tvAgeInMin)
        datepicker.setOnClickListener { datepickerclick() }
    }
    private fun datepickerclick() {
        val myCalander = Calendar.getInstance()
        val myYear = myCalander.get(Calendar.YEAR)
        val myMonth = myCalander.get(Calendar.MONTH)
        val myDate = myCalander.get((Calendar.DAY_OF_MONTH))
        val dpd = DatePickerDialog(
            this,
            /*DatePickerDialog.OnDateSetListener */{ view, selectedYear, selectedMonth, SelectedDayOfMonth ->
//                Toast.makeText(
//                    this,
//                    "Year was $selectedYear month is $selectedMonth and day is$SelectedDayOfMonth",
//                    Toast.LENGTH_SHORT
//                ).show()
                val selectedtext = "$selectedYear/$selectedMonth /$SelectedDayOfMonth"
                tvSelectedDate?.text = selectedtext

                val theDate = sdf.parse(selectedtext)
                if (theDate != null) {
                    Toast.makeText(this,theDate.toString(),Toast.LENGTH_SHORT).show()
                }
                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 6000
                    val currentTime = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentTime?.let {
                        val currentTimeInMin = currentTime.time / 6000
                        val timeDifference = currentTimeInMin - selectedDateInMinutes
                        tvAgeInMin?.text = timeDifference.toString()//"$timeDifference"
                    }
                }
            },
            myYear,
            myMonth,
            myDate
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}
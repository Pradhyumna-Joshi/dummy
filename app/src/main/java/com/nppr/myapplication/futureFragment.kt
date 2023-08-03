package com.nppr.myapplication

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

class futureFragment : Fragment(R.layout.fragment_future) {
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        val et1: EditText =itemView.findViewById(R.id.etDes)
        val til: TextInputLayout =itemView.findViewById(R.id.til_des)

        //val two: TextView =itemView.findViewById(R.id.tv_d2)
        et1.setOnClickListener {
            clickDataPicker(it)
        }
    }
        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view=inflater.inflate(R.layout.fragment_future, container, false)
        return view
    }
    private fun clickDataPicker(view: View) {
        /**
         * This Gets a calendar using the default time zone and locale.
         * The calender returned is based on the current time
         * in the default time zone with the default.
         */

        val et1: EditText =view.findViewById(R.id.etDes)
        val c = Calendar.getInstance()

        val year =
            c.get(Calendar.YEAR) // Returns the value of the given calendar field. This indicates YEAR
        val month = c.get(Calendar.MONTH) // This indicates the Month
        val day = c.get(Calendar.DAY_OF_MONTH) // This indicates the Day

        /**
         * Creates a new date picker dialog for the specified date using the parent
         * context's default date picker dialog theme.
         */
        val dpd = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                /**
                 * The listener used to indicate the user has finished selecting a date.
                 */

                /**
                 * The listener used to indicate the user has finished selecting a date.
                 */

                /**
                 *Here the selected date is set into format i.e : day/Month/Year
                 * And the month is counted in java is 0 to 11 so we need to add +1 so it can be as selected.
                 * */
                /**
                 *Here the selected date is set into format i.e : day/Month/Year
                 * And the month is counted in java is 0 to 11 so we need to add +1 so it can be as selected.
                 * */
                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"

                // Selected date it set to the TextView to make it visible to user.
                et1.setText(selectedDate)
            /*
                /**
                 * Here we have taken an instance of Date Formatter as it will format our
                 * selected date in the format which we pass it as an parameter and Locale.
                 * Here I have passed the format as dd/MM/yyyy.
                 */
                /**
                 * Here we have taken an instance of Date Formatter as it will format our
                 * selected date in the format which we pass it as an parameter and Locale.
                 * Here I have passed the format as dd/MM/yyyy.
                 */
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                // The formatter will parse the selected date in to Date object
                // so we can simply get date in to milliseconds.
                val theDate = sdf.parse(selectedDate)

                /** Here we have get the time in milliSeconds from Date object
                 * And as we know the formula as milliseconds can be converted to second by dividing it by 1000.
                 * And the seconds can be converted to minutes by dividing it by 60.
                 * So now in the selected date into minutes.
                 */
                /** Here we have get the time in milliSeconds from Date object
                 * And as we know the formula as milliseconds can be converted to second by dividing it by 1000.
                 * And the seconds can be converted to minutes by dividing it by 60.
                 * So now in the selected date into minutes.
                 */
                val selectedDateToMinutes = theDate!!.time / 60000

                // Here we have parsed the current date with the Date Formatter which is used above.
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                // Current date in to minutes.
                val currentDateToMinutes = currentDate!!.time / 60000

                /**
                 * Now to get the difference into minutes.
                 * We will subtract the selectedDateToMinutes from currentDateToMinutes.
                 * Which will provide the difference in minutes.
                 */
                /**
                 * Now to get the difference into minutes.
                 * We will subtract the selectedDateToMinutes from currentDateToMinutes.
                 * Which will provide the difference in minutes.
                 */
                val differenceInMinutes = currentDateToMinutes - selectedDateToMinutes
*/
                // Set the difference in minutes to textview to show the user.
                //tvSelectedDateInMinutes.setText(differenceInMinutes.toString())
            },
            year,
            month,
            day
        )

        /**
         * Sets the maximal date supported by this in
         * milliseconds since January 1, 1970 00:00:00 in time zone.
         *
         * @param maxDate The maximal supported date.
         */
        // 86400000 is milliseconds of 24 Hours. Which is used to restrict the user to select today and future day.
        dpd.datePicker.setMinDate(Date().time)
        dpd.show() // It is used to show the datePicker Dialog.*/
    }
}

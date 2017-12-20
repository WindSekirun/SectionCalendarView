package pyxis.uzuki.live.sectioncalendarview.demo

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sample.*

/**
 * SectionCalendarView
 * Class: KotlinActivity
 * Created by Pyxis on 2017-12-20.
 *
 * Description:
 */

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        init()
    }

    @SuppressLint("SetTextI18n")
    private fun init() {
        calendarView.setDateFormat("yyyy MMM")
        calendarView.setErrToastMessage("You can not select the previous date.")
        calendarView.setOnDaySelectedListener { startDay, endDay ->
            txtStartDay.text = startDay
            txtEndDay.text = endDay
        }
        calendarView.buildCalendar()

        btnClear.setOnClickListener { v -> calendarView.clearDate() }
        btnDone.setOnClickListener { v -> calendarView.done() }
    }
}
package pyxis.uzuki.live.sectioncalendarview.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import pyxis.uzuki.live.pyxinjector.annotation.BindView;
import pyxis.uzuki.live.pyxinjector.annotation.OnClick;
import pyxis.uzuki.live.pyxinjector.base.InjectActivity;
import pyxis.uzuki.live.sectioncalendarview.SectionCalendarView;

/**
 * SectionCalendarView
 * Class: JavaActivity
 * Created by Pyxis on 2017-12-20.
 */

public class JavaActivity extends InjectActivity {
    private @BindView TextView txtStartDay;
    private @BindView TextView txtEndDay;
    private @BindView SectionCalendarView calendarView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        init();
    }

    private void init() {
        calendarView.setDateFormat("yyyy MMM");
        calendarView.setErrToastMessage("You can not select the previous date.");
        calendarView.setOnDaySelectedListener((startDay, endDay) -> {
            txtStartDay.setText(startDay);
            txtEndDay.setText(endDay);
        });
        calendarView.buildCalendar();
    }

    @OnClick(R.id.btnClear)
    private void clickClear() {
        calendarView.clearDate();
    }

    @OnClick(R.id.btnDone)
    private void clickDone() {
        calendarView.sendNowValue();
    }
}

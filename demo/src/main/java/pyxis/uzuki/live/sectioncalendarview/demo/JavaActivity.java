package pyxis.uzuki.live.sectioncalendarview.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pyxis.uzuki.live.pyxinjector.annotation.BindView;
import pyxis.uzuki.live.pyxinjector.base.InjectActivity;
import pyxis.uzuki.live.sectioncalendarview.SectionCalendarView;
import pyxis.uzuki.live.sectioncalendarview.impl.OnDaySelectedListener;

/**
 * SectionCalendarView
 * Class: JavaActivity
 * Created by Pyxis on 2017-12-20.
 * <p>
 * Description:
 */

public class JavaActivity extends InjectActivity {
    private @BindView TextView txtStartDay;
    private @BindView TextView txtEndDay;
    private @BindView Button btnClear;
    private @BindView Button btnDone;
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
        calendarView.setOnDaySelectedListener(new OnDaySelectedListener() {
            @Override
            public void onDaySelected(String startDay, String endDay) {
                txtStartDay.setText(startDay);
                txtEndDay.setText(endDay);
            }
        });
        calendarView.buildCalendar();

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.clearDate();
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.done();
            }
        });
    }
}

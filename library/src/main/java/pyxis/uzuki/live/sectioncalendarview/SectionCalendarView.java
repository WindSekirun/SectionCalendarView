package pyxis.uzuki.live.sectioncalendarview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import pyxis.uzuki.live.pyxinjector.PyxInjector;
import pyxis.uzuki.live.pyxinjector.annotation.BindView;
import pyxis.uzuki.live.sectioncalendarview.adapter.CalendarAdapter;
import pyxis.uzuki.live.sectioncalendarview.impl.OnDaySelectedListener;
import pyxis.uzuki.live.sectioncalendarview.model.DayData;
import pyxis.uzuki.live.sectioncalendarview.utils.CommonEx;
import pyxis.uzuki.live.sectioncalendarview.utils.InternalEx;

/**
 * SectionCalendarView
 * Class: SectionCalendarView
 * Created by Pyxis on 2017-12-20.
 * <p>
 * Description:
 */

public class SectionCalendarView extends LinearLayout implements AdapterView.OnItemClickListener {
    private PyxInjector injector = new PyxInjector();
    private OnDaySelectedListener onDaySelectedListener;

    private @BindView ImageButton btnPrevMonth;
    private @BindView TextView txtDayText;
    private @BindView ImageButton btnNextMonth;
    private @BindView GridView gridView;

    private Calendar mCalendar = null;
    private CalendarAdapter mAdapter = null;

    private String dateFormatStr = "yyyy MMM";

    private int mTodayYearInt = 0;
    private int mTodayMonthInt = 0;
    private int mTodayInt = 0;
    private String mStartDay = "";
    private String mEndDay = "";
    private String mNowYearMonth = null;
    private String mNowFullDay = null;
    private ArrayList<DayData> mList = new ArrayList<>();

    public SectionCalendarView(Context context) {
        super(context);
        init();
    }

    public SectionCalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * Gets the selection date with {@link OnDaySelectedListener}
     * {@link OnDaySelectedListener} 를 통해 선택하는 날짜를 가져옵니다.
     *
     * @param onDaySelectedListener Object which implements {@link OnDaySelectedListener}
     *                              {@link OnDaySelectedListener} 를 구현하는 객체
     */
    public void setOnDaySelectedListener(OnDaySelectedListener onDaySelectedListener) {
        this.onDaySelectedListener = onDaySelectedListener;
    }

    /**
     * Create and display a calendar without given parameter
     * 주어진 파라미터 없이 달력을 만들어서 보여줍니다.
     */
    public void buildCalendar() {
        buildCalendar("", "");
    }

    /**
     * Create and display a calendar with given two parameter, one is StartDay, other is EndDay
     * 시작일, 종료일을 설정하여 달력을 만들어서 보여줍니다.
     *
     * @param startDay StartDay / 시작일
     * @param endDay   EndDay / 종료일
     */
    public void buildCalendar(String startDay, String endDay) {
        mStartDay = startDay;
        mEndDay = endDay;

        mAdapter = new CalendarAdapter(getContext());
        gridView.setAdapter(mAdapter);
        makeCalendar();

        gridView.setOnItemClickListener(this);
        btnPrevMonth.setOnClickListener(v -> goToPrevMonth());
        btnNextMonth.setOnClickListener(v -> goToNextMonth());
    }

    /**
     * Sets the dateFormat of the currently displayed month. Default is yyyy MMM
     * 위에 표시되는 현재 보여지고 있는 달의 날짜 포맷을 설정합니다. 기본적으로 yyyy MMM이 사용됩니다.
     * <p>
     * The actual display format changes depending on which language setting the current device is in.
     * 현재 기기가 어떤 언어 설정으로 되어있는지에 따라 실제 표시되는 형식이 변경됩니다.
     *
     * @param dateFormat DateFormat / 날짜 포맷
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormatStr = dateFormat;
    }

    /**
     * Create and display a calendar of the previous month
     * 이전 달의 달력을 만들어서 보여줍니다.
     */
    public void goToPrevMonth() {
        mCalendar.add(Calendar.MONTH, -1);
        makeCalendar();
    }

    /**
     * Create and display a calendar of the next month
     * 다음 달의 달력을 만들어서 보여줍니다.
     */
    public void goToNextMonth() {
        mCalendar.add(Calendar.MONTH, +1);
        makeCalendar();
    }

    /**
     * Clear Calendar state
     * 캘린더 상태를 초기화합니다.
     */
    public void clearDate() {
        if (!CommonEx.notEmptyString(mStartDay)) {
            clearState();
        }
    }

    /**
     * Sent via {@link OnDaySelectedListener} with the information you currently have.
     * 현재 가지고 있는 정보를 설정한 {@link OnDaySelectedListener} 로 통해 전송됩니다.
     */
    public void done() {
        if (CommonEx.notEmptyString(mStartDay, mEndDay)) {
            if (onDaySelectedListener != null) {
                onDaySelectedListener.onDaySelected(mStartDay, mEndDay);
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DayData data = mAdapter.getItem(position);
        if (mAdapter.getItem(position) == null || TextUtils.isEmpty(data.getFullDay())) {
            return;
        }

        if (!mAdapter.isStart()) {
            if (CommonEx.compareLess(data.getFullDay(), mNowFullDay)) { // 현재 달에서 오늘보다 전 날짜를 선택할 때
                Toast.makeText(getContext(), R.string.cant_select_prev_date, Toast.LENGTH_SHORT).show();
                return;
            }

            mAdapter.setStartPosition(position);
            mAdapter.setStart(!mAdapter.isStart());
            mStartDay = String.format("%s.%s.%s", mCalendar.get(Calendar.YEAR), (mCalendar.get(Calendar.MONTH) + 1), data.getDayStr());
            mAdapter.notifyDataSetChanged();
            mAdapter.setStartDay(mStartDay);

            if (onDaySelectedListener != null) {
                onDaySelectedListener.onDaySelected(mStartDay, mEndDay);
            }
            return;
        }

        if (InternalEx.compareDayEqual(mStartDay, data.getFullDay())) { // 시작 날짜를 다시 선택했을 경우
            clearState();
            return;
        }

        if (CommonEx.compareLess(data.getFullDay(), mNowFullDay)) { // 현재 달에서 오늘보다 전 날짜를 선택할 때
            Toast.makeText(getContext(), R.string.cant_select_prev_date, Toast.LENGTH_SHORT).show();
            return;
        }

        if (InternalEx.compareDayGreat(mStartDay, data.getFullDay())) {
            Toast.makeText(getContext(), R.string.cant_select_prev_date, Toast.LENGTH_SHORT).show();
            return;
        }

        mAdapter.setEndPosition(position);
        mEndDay = String.format("%s.%s.%s", mCalendar.get(Calendar.YEAR), (mCalendar.get(Calendar.MONTH) + 1), data.getDayStr());
        mAdapter.setEnd(true);
        mAdapter.notifyDataSetChanged();
        mAdapter.setEndDay(mEndDay);

        if (onDaySelectedListener != null) {
            onDaySelectedListener.onDaySelected(mStartDay, mEndDay);
        }
    }

    private void init() {
        inflate(getContext(), R.layout.calendar_view, this);
        injector.execute(getContext(), this, this);

        mCalendar = Calendar.getInstance();
        mTodayYearInt = mCalendar.get(Calendar.YEAR);
        mTodayMonthInt = mCalendar.get(Calendar.MONTH) + 1;
        mTodayInt = mCalendar.get(Calendar.DAY_OF_MONTH);

        mNowYearMonth = String.valueOf(mTodayInt);
        mNowYearMonth += InternalEx.assignPad10(mTodayMonthInt);
        mNowFullDay = String.valueOf(mTodayYearInt);
        mNowFullDay += InternalEx.assignPad10(mTodayMonthInt);
        mNowFullDay += InternalEx.assignPad10(mTodayInt);
    }

    private void makeCalendar() {
        mList.clear();

        Calendar calendar = (Calendar) mCalendar.clone();
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr, Locale.getDefault());
        txtDayText.setText(dateFormat.format(mCalendar.getTime()));

        calendar.set(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), 1);
        int dayNum = calendar.get(Calendar.DAY_OF_WEEK);

        /*
         * add whitespace for matching day of the week.
         * 요일 매칭 위해 공백 추가
         */
        for (int i = 1; i < dayNum; i++) {
            DayData dayData = new DayData();
            dayData.setDayStr("");
            mList.add(dayData);
        }

        setCalendarDate(mCalendar.get(Calendar.MONTH) + 1);
        mAdapter.notifyDataSetChanged(mList);
        mAdapter.setViewMonth(calendar.get(Calendar.MONTH) + 1);
    }

    private void setCalendarDate(int desireMonth) {
        mCalendar.set(Calendar.MONTH, desireMonth - 1);
        for (int i = 0, icnt = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH); i < icnt; i++) {
            int year = mCalendar.get(Calendar.YEAR);
            int month = mCalendar.get(Calendar.MONTH) + 1;
            String monthStr = InternalEx.assignPad10(month);
            String dayStr = InternalEx.assignPad10(i + 1);

            DayData dayData = new DayData();
            dayData.setYear(year);
            dayData.setMonth(month);
            dayData.setDay(i + 1);
            dayData.setDayStr(String.valueOf(i + 1));
            dayData.setFullDay(String.format("%s%s%s", year, monthStr, dayStr));
            mList.add(dayData);
        }
    }

    private void clearState() {
        mAdapter.setStart(false);
        mAdapter.setEnd(false);
        mStartDay = "";
        mEndDay = "";
        mAdapter.setStartPosition(-1);
        mAdapter.setEndPosition(-1);
        mAdapter.notifyDataSetChanged();
        mAdapter.setStartDay("");
        mAdapter.setEndDay("");

        if (onDaySelectedListener != null) {
            onDaySelectedListener.onDaySelected(mStartDay, mEndDay);
        }
    }
}

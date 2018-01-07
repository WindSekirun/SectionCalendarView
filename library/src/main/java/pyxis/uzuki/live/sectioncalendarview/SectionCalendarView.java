package pyxis.uzuki.live.sectioncalendarview;

import android.content.Context;
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
import java.util.Date;
import java.util.Locale;

import pyxis.uzuki.live.pyxinjector.PyxInjector;
import pyxis.uzuki.live.pyxinjector.annotation.BindView;
import pyxis.uzuki.live.sectioncalendarview.adapter.CalendarAdapter;
import pyxis.uzuki.live.sectioncalendarview.impl.OnDaySelectedListener;
import pyxis.uzuki.live.sectioncalendarview.model.ColorData;
import pyxis.uzuki.live.sectioncalendarview.model.DayData;
import pyxis.uzuki.live.sectioncalendarview.utils.CommonEx;
import pyxis.uzuki.live.sectioncalendarview.utils.InternalEx;

/**
 * SectionCalendarView
 * Class: SectionCalendarView
 * Created by Pyxis on 2017-12-20.
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
    private String mDateFormatStr = "yyyy MMM";
    private String mErrToastStr = "";
    private ColorData mColorData;
    private String mStartDay = "";
    private String mEndDay = "";
    private String mNowFullDay = null;
    private ArrayList<DayData> mList = new ArrayList<>();

    public SectionCalendarView(Context context) {
        super(context);
        init();
    }

    public SectionCalendarView(Context context, AttributeSet attrs) {
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

        mAdapter = new CalendarAdapter(getContext(), mColorData);
        gridView.setAdapter(mAdapter);
        makeCalendar();

        gridView.setOnItemClickListener(this);

        btnPrevMonth.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPrevMonth();
            }
        });

        btnNextMonth.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextMonth();
            }
        });
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
        this.mDateFormatStr = dateFormat;
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
        clearState();
    }

    /**
     * Sent via {@link OnDaySelectedListener} with the information you currently have.
     * 현재 가지고 있는 정보를 설정한 {@link OnDaySelectedListener} 로 통해 전송됩니다.
     */
    public void sendNowValue() {
        if (CommonEx.notEmptyString(mStartDay, mEndDay)) {
            sendCallback();
        }
    }

    /**
     * Change message of Toast to be displayed if an incorrect value is selected.
     * 잘못된 값을 선택했을 경우 표시되는 Toast의 메세지를 변경합니다.
     *
     * @param message message / 메세지
     */
    public void setErrToastMessage(String message) {
        this.mErrToastStr = message;
    }

    /**
     * Change message of Toast to be displayed if an incorrect value is selected.
     * 잘못된 값을 선택했을 경우 표시되는 Toast의 메세지를 변경합니다.
     *
     * @param messageRes resources of message / 메세지 리소스
     */
    public void setErrToastMessage(int messageRes) {
        this.mErrToastStr = getContext().getString(messageRes);
    }

    /**
     * Use the {@link ColorData} object to set the color.
     * {@link ColorData} 객체를 활용하여 색을 설정합니다.
     *
     * @param colorData {@link ColorData}
     */
    public void setColorData(ColorData colorData) {
        this.mColorData = colorData;
    }

    /**
     * Set StartDay manually
     * StartDay를 수동으로 설정합니다.
     *
     * @param startDay {@link Date}
     */
    public void setStartDay(Date startDay) {
        mStartDay = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).format(startDay);
        mAdapter.setStart(true);
        mAdapter.setStartDay(mStartDay);
        mAdapter.notifyDataSetChanged();
        sendCallback();
    }

    /**
     * Set EndDay manually
     * EndDay를 수동으로 설정합니다.
     *
     * @param endDay {@link Date}
     */
    public void setEndDay(Date endDay) {
        mEndDay = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).format(endDay);
        mAdapter.setEnd(true);
        mAdapter.setEndDay(mEndDay);
        mAdapter.notifyDataSetChanged();
        sendCallback();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DayData data = mAdapter.getItem(position);
        if (mAdapter.getItem(position) == null || TextUtils.isEmpty(data.getFullDay())) {
            return;
        }

        String dayFormatted = String.format("%s.%s.%s", mCalendar.get(Calendar.YEAR),
                InternalEx.assignPad10(mCalendar.get(Calendar.MONTH) + 1), InternalEx.assignPad10(data.getDayStr()));

        if (!mAdapter.isStart()) {
            if (CommonEx.compareLess(data.getFullDay(), mNowFullDay)) { // 현재 달에서 오늘보다 전 날짜를 선택할 때
                showErrToast();
                return;
            }

            mAdapter.setStart(!mAdapter.isStart());
            mStartDay = dayFormatted;
            mAdapter.notifyDataSetChanged();
            mAdapter.setStartDay(mStartDay);

            sendCallback();
            return;
        }

        if (InternalEx.compareDayEqual(mStartDay, data.getFullDay())) { // 시작 날짜를 다시 선택했을 경우
            clearState();
            return;
        }

        if (CommonEx.compareLess(data.getFullDay(), mNowFullDay)) { // 현재 달에서 오늘보다 전 날짜를 선택할 때
            showErrToast();
            return;
        }

        if (InternalEx.compareDayGreat(mStartDay, data.getFullDay())) { // 시작 날짜보다 전 날짜를 선택했을 때
            showErrToast();
            return;
        }

        mEndDay = dayFormatted;
        mAdapter.setEnd(true);
        mAdapter.setEndDay(mEndDay);
        mAdapter.notifyDataSetChanged();
        sendCallback();
    }

    private void init() {
        inflate(getContext(), R.layout.calendar_view, this);
        injector.execute(getContext(), this, this);

        mColorData = new ColorData.Builder().build();
        mCalendar = Calendar.getInstance();
        mNowFullDay = String.valueOf(mCalendar.get(Calendar.YEAR));
        mNowFullDay += InternalEx.assignPad10(mCalendar.get(Calendar.MONTH) + 1);
        mNowFullDay += InternalEx.assignPad10(mCalendar.get(Calendar.DAY_OF_MONTH));
    }

    private void makeCalendar() {
        mList.clear();
        Calendar calendar = (Calendar) mCalendar.clone();
        SimpleDateFormat dateFormat = new SimpleDateFormat(mDateFormatStr, Locale.getDefault());
        txtDayText.setText(dateFormat.format(mCalendar.getTime()));

        calendar.set(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), 1);
        int dayNum = calendar.get(Calendar.DAY_OF_WEEK);

        // 요일 매칭 위해 공백 추가
        for (int i = 1; i < dayNum; i++) {
            mList.add(new DayData());
        }

        mCalendar.set(Calendar.MONTH, mCalendar.get(Calendar.MONTH));

        for (int i = 0, icnt = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH); i < icnt; i++) {
            int year = mCalendar.get(Calendar.YEAR);
            int month = mCalendar.get(Calendar.MONTH) + 1;
            String monthStr = InternalEx.assignPad10(month);
            String dayStr = InternalEx.assignPad10(i + 1);

            DayData dayData = new DayData(year, month, i + 1, String.valueOf(i + 1), String.format("%s%s%s", year, monthStr, dayStr));
            mList.add(dayData);
        }

        mAdapter.notifyDataSetChanged(mList);
    }

    private void clearState() {
        mAdapter.setStart(false);
        mAdapter.setEnd(false);
        mStartDay = "";
        mEndDay = "";
        mAdapter.notifyDataSetChanged();
        mAdapter.setStartDay("");
        mAdapter.setEndDay("");
        sendCallback();
    }

    private void showErrToast() {
        String message = getContext().getString(R.string.cant_select_prev_date);
        if (!TextUtils.isEmpty(mErrToastStr)) {
            message = mErrToastStr;
        }

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void sendCallback() {
        if (onDaySelectedListener != null) {
            onDaySelectedListener.onDaySelected(mStartDay, mEndDay);
        }
    }
}
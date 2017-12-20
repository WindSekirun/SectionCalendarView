package pyxis.uzuki.live.sectioncalendarview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Calendar;

import pyxis.uzuki.live.sectioncalendarview.model.DayData;

/**
 * SectionCalendarView
 * Class: CalendarAdapter
 * Created by Pyxis on 2017-12-20.
 * <p>
 * Description:
 */

public class CalendarAdapter extends BaseAdapter {
    private Context mContext = null;
    private Calendar mCalendar = null;
    private LayoutInflater mInflater = null;
    private int mViewMonth = 0;
    private int mViewYear = 0;
    private int mStartPosition = -1;
    private int mEndPosition = -1;
    private int mTodayYearInt = 0;
    private int mTodayMonthInt = 0;
    private int mTodayInt = 0;
    private String mStratDay = "";
    private String mEndDay = "";
    private String mEndStr = "";
    private String mToday = null;
    private String mNowFullDay = null;
    private String mNowYearMonth = null;
    private String[] mStartArr = new String[3];
    private String[] mEndArr = new String[3];
    private boolean isStart = false;
    private boolean isEnd = false;
    private ArrayList<DayData> mList = new ArrayList<>();

    public CalendarAdapter(Context mContext) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        mCalendar = Calendar.getInstance();

        mTodayYearInt = mCalendar.get(Calendar.YEAR);
        mTodayMonthInt = mCalendar.get(Calendar.MONTH) + 1;
        mTodayInt = mCalendar.get(Calendar.DAY_OF_MONTH);
        mToday = String.valueOf(mTodayInt);
        mNowYearMonth = String.valueOf(mTodayInt);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}

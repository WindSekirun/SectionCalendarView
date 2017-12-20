package pyxis.uzuki.live.sectioncalendarview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import pyxis.uzuki.live.sectioncalendarview.R;
import pyxis.uzuki.live.sectioncalendarview.model.DayData;
import pyxis.uzuki.live.sectioncalendarview.utils.CommonEx;
import pyxis.uzuki.live.sectioncalendarview.utils.InternalEx;

/**
 * SectionCalendarView
 * Class: CalendarAdapter
 * Created by Pyxis on 2017-12-20.
 * <p>
 * Description:
 */

@SuppressWarnings("ConstantConditions")
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
    private String mStartDay = "";
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
        mNowYearMonth += InternalEx.assignPad10(mTodayMonthInt);
        mNowFullDay = String.valueOf(mTodayYearInt);
        mNowFullDay += InternalEx.assignPad10(mTodayMonthInt);
        mNowFullDay += InternalEx.assignPad10(mTodayInt);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public DayData getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void notifyDataSetChanged(ArrayList<DayData> list) {
        mList.clear();
        mList.addAll(list);

        notifyDataSetChanged();
    }

    /**
     * 시작 날짜 설정
     *
     * @param startDay
     */
    public void setStartDay(String startDay) {
        mStartDay = startDay;
        mStartArr = mStartDay.split("\\.");
    }

    /**
     * 끝나는 날짜 설정
     */
    public void setEndDay(String endDay) {
        mEndDay = endDay;
        mEndArr = mEndDay.split("\\.");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.calendar_item, null);
            holder.dayText = convertView.findViewById(R.id.txtDay);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (holder == null) {
            return convertView;
        }

        DayData data = mList.get(position);
        String day = data.getDayStr();

        holder.dayText.setTextColor(Color.parseColor("#999999"));
        if (mNowFullDay.equals(data.getFullDay())) { // 오늘 날짜
            holder.dayText.setTextColor(Color.parseColor("#222222"));
        } else {
            if (CommonEx.notEmptyString(data.getFullDay()) && CommonEx.compareGreater(data.getFullDay(), mNowFullDay)) {
                holder.dayText.setTextColor(Color.parseColor("#999999"));
            } else {
                holder.dayText.setTextColor(Color.parseColor("#EEEEEE"));
            }
        }

        holder.dayText.setBackgroundColor(Color.parseColor("#ffffff"));
        if (CommonEx.notEmptyString(mStartDay, data.getFullDay()) && isStart &&
                InternalEx.compareDayEqual(mStartDay, data.getFullDay())) {
            holder.dayText.setBackgroundColor(Color.parseColor("#628DE5"));
            holder.dayText.setTextColor(Color.WHITE);
        } else {
            if (CommonEx.notEmptyString(mStartDay, data.getFullDay()) && isEnd &&
                    InternalEx.compareDayEqual(mEndDay, data.getFullDay())) {
                holder.dayText.setBackgroundColor(Color.parseColor("#EC3C3C"));
                holder.dayText.setTextColor(Color.WHITE);
                mEndStr = String.valueOf(mList.get(mEndPosition).getDay());
            } else {
                if (CommonEx.notEmptyString(mStartDay, data.getFullDay(), mEndDay) &&
                        InternalEx.compareDayLessEqaul(mStartDay, data.getFullDay()) &&
                        InternalEx.compareDayGreatEqaul(mEndDay, data.getFullDay())) {
                    holder.dayText.setBackgroundColor(Color.parseColor("#EEEEEE"));
                }
            }
        }

        holder.dayText.setText(day);

        return convertView;
    }

    public int getViewMonth() {
        return mViewMonth;
    }

    public void setViewMonth(int mViewMonth) {
        this.mViewMonth = mViewMonth;
    }

    public int getStartPosition() {
        return mStartPosition;
    }

    public void setStartPosition(int mStartPosition) {
        this.mStartPosition = mStartPosition;
    }

    public int getEndPosition() {
        return mEndPosition;
    }

    public void setEndPosition(int mEndPosition) {
        this.mEndPosition = mEndPosition;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public String getEndStr() {
        return mEndStr;
    }

    private class ViewHolder {
        TextView dayText;
    }
}
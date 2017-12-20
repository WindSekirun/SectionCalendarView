package pyxis.uzuki.live.sectioncalendarview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import pyxis.uzuki.live.sectioncalendarview.R;
import pyxis.uzuki.live.sectioncalendarview.model.ColorData;
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
    private LayoutInflater mInflater = null;
    private String mStartDay = "";
    private String mEndDay = "";
    private String mNowFullDay = null;
    private boolean isStart = false;
    private boolean isEnd = false;
    private ArrayList<DayData> mList = new ArrayList<>();
    private ColorData mColorData;

    public CalendarAdapter(Context mContext, ColorData colorData) {
        this.mColorData = colorData;
        this.mInflater = LayoutInflater.from(mContext);

        Calendar mCalendar = Calendar.getInstance();
        int mTodayYearInt = mCalendar.get(Calendar.YEAR);
        int mTodayMonthInt = mCalendar.get(Calendar.MONTH) + 1;
        int mTodayInt = mCalendar.get(Calendar.DAY_OF_MONTH);
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

    public void setStartDay(String startDay) {
        mStartDay = startDay;
    }

    public void setEndDay(String endDay) {
        mEndDay = endDay;
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

        if (mNowFullDay.equals(data.getFullDay())) { // 오늘 날짜
            holder.dayText.setTextColor(mColorData.getTodayTextColor());
        } else if (CommonEx.compareGreater(data.getFullDay(), mNowFullDay)) {
            holder.dayText.setTextColor(mColorData.getDefaultTextColor());
        } else {
            holder.dayText.setTextColor(mColorData.getPrevDayTextColor());
        }

        holder.dayText.setBackgroundColor(mColorData.getDefaultBgColor());
        if (isStart && InternalEx.compareDayEqual(mStartDay, data.getFullDay())) {
            holder.dayText.setBackgroundColor(mColorData.getStartDayBgColor());
            holder.dayText.setTextColor(mColorData.getStartDayTextColor());
        } else if (CommonEx.notEmptyString(mStartDay, data.getFullDay()) && isEnd && InternalEx.compareDayEqual(mEndDay, data.getFullDay())) {
            holder.dayText.setBackgroundColor(mColorData.getEndDayBgColor());
            holder.dayText.setTextColor(mColorData.getEndDayTextColor());
        } else if (InternalEx.compareDayLessEqual(mStartDay, data.getFullDay()) && InternalEx.compareDayGreatEqual(mEndDay, data.getFullDay())) {
            holder.dayText.setBackgroundColor(mColorData.getSelectedDayBgColor());
        }

        holder.dayText.setText(day);

        return convertView;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    private class ViewHolder {
        TextView dayText;
    }
}
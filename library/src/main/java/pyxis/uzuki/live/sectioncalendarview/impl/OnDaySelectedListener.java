package pyxis.uzuki.live.sectioncalendarview.impl;

/**
 * SectionCalendarView
 * Class: OnDaySelectedListener
 * Created by Pyxis on 2017-12-20.
 * <p>
 * Description:
 */

public interface OnDaySelectedListener {
    /**
     * Return selected date.
     * 선택한 날짜를 반환합니다.
     *
     * If the value is empty, the value is not selected. Be sure to handle exceptions.
     * 값이 비어있는 경우, 해당 값을 선택하지 않은 상태입니다. 반드시 예외 처리를 하세요.
     *
     * @param startDay StartDay / 시작일
     * @param endDay EndDay / 종료일
     */
    public void onDaySelected(String startDay, String endDay);
}

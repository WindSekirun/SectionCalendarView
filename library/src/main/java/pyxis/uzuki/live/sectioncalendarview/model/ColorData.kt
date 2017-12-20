package pyxis.uzuki.live.sectioncalendarview.model

import android.graphics.Color

/**
 * SectionCalendarView
 * Class: ColorData
 * Created by Pyxis on 2017-12-20.
 *
 * Description:
 */

data class ColorData(val defaultBgColor: Int = 0xFFFFFF, val defaultTextColor: Int = 0x999999,
                     val todayTextColor: Int = 0x222222, val prevDayTextColor: Int = 0xEEEEEE,
                     val startDayBgColor: Int = 0x628DE5, val startDayTextColor: Int = 0xFFFFFF,
                     val endDayBgColor: Int = 0xEC3C3C, val endDayTextColor: Int = 0xFFFFFF,
                     val selectedDayBgColor: Int = 0xEEEEEE) {

    class Builder {
        private var defaultBgColor: Int = Color.parseColor("#FFFFFF")
        private var defaultTextColor: Int = Color.parseColor("#999999")
        private var todayTextColor: Int = Color.parseColor("#222222")
        private var prevDayTextColor: Int = Color.parseColor("#EEEEEE")
        private var startDayBgColor: Int = Color.parseColor("#628DE5")
        private var startDayTextColor: Int = Color.parseColor("#FFFFFF")
        private var endDayBgColor: Int = Color.parseColor("#EC3C3C")
        private var endDayTextColor: Int = Color.parseColor("#FFFFFF")
        private var selectedDayBgColor: Int = Color.parseColor("#EEEEEE")

        /**
         * Set default background color
         * 기본 배경색 설정
         *
         * @param color default background color / 기본 배경색
         */
        fun setDefaultBgColor(color: String) = apply { this.defaultBgColor = Color.parseColor(color) }

        /**
         * Set default background color
         * 기본 배경색 설정
         *
         * @param color default background color / 기본 배경색
         */
        fun setDefaultBgColor(color: Int) = apply { this.defaultBgColor = color }

        /**
         * Set default text color
         * 기본 글자 색상 설정
         *
         * @param color default text color /  기본 글자 색상
         */
        fun setDefaultTextColor(color: String) = apply { this.defaultTextColor = Color.parseColor(color) }

        /**
         * Set default text color
         * 기본 글자 색상 설정
         *
         * @param color default text color /  기본 글자 색상
         */
        fun setDefaultTextColor(color: Int) = apply { this.defaultTextColor = color }

        /**
         * Set today's date color
         * 오늘 날짜 글자 색상 설정
         *
         * @param color today's date color / 오늘 날짜 글자 색상
         */
        fun setTodayTextColor(color: String) = apply { this.todayTextColor = Color.parseColor(color) }

        /**
         * Set today's date color
         * 오늘 날짜 글자 색상 설정
         *
         * @param color today's date color / 오늘 날짜 글자 색상
         */
        fun setTodayTextColor(color: Int) = apply { this.todayTextColor = color }

        /**
         * Set past date color
         * 지나간 날짜 글자 색상 설정
         *
         * @param color past date color / 지나간 날짜 글자 색상
         */
        fun setPrevDayTextColor(color: String) = apply { this.prevDayTextColor = Color.parseColor(color) }

        /**
         * Set past date color
         * 지나간 날짜 글자 색상 설정
         *
         * @param color past date color / 지나간 날짜 글자 색상
         */
        fun setPrevDayTextColor(color: Int) = apply { this.prevDayTextColor = color }

        /**
         * Set selected StartDay background color
         * 선택된 시작일 배경색 설정
         *
         * @param color StartDay background color / 시작일 배경색
         */
        fun setStartDayBgColor(color: String) = apply { this.startDayBgColor = Color.parseColor(color) }

        /**
         * Set selected StartDay background color
         * 선택된 시작일 배경색 설정
         *
         * @param color StartDay background color / 시작일 배경색
         */
        fun setStartDayBgColor(color: Int) = apply { this.startDayBgColor = color }

        /**
         * Set selected StartDay text color
         * 선택된 시작일 글자 색상 설정
         *
         * @param color StartDay text color / 시작일 글자 색상
         */
        fun setStartDayTextColor(color: String) = apply { this.startDayTextColor = Color.parseColor(color) }

        /**
         * Set selected StartDay text color
         * 선택된 시작일 글자 색상 설정
         *
         * @param color StartDay text color / 시작일 글자 색상
         */
        fun setStartDayTextColor(color: Int) = apply { this.startDayTextColor = color }

        /**
         * Set selected EndDay background color
         * 선택된 종료일 배경색 설정
         *
         * @param color EndDay background color / 종료일 배경색
         */
        fun setEndDayBgColor(color: String) = apply { this.endDayBgColor = Color.parseColor(color) }

        /**
         * Set selected EndDay background color
         * 선택된 종료일 배경색 설정
         *
         * @param color EndDay background color / 종료일 배경색
         */
        fun setEndDayBgColor(color: Int) = apply { this.endDayBgColor = color }

        /**
         * Set selected EndDay text color
         * 선택된 종료일 글자 색상 설정
         *
         * @param color EndDay text color / 종료일 글자 색상
         */
        fun setEndDayTextColor(color: String) = apply { this.endDayTextColor = Color.parseColor(color) }

        /**
         * Set selected EndDay text color
         * 선택된 종료일 글자 색상 설정
         *
         * @param color EndDay text color / 종료일 글자 색상
         */
        fun setEndDayTextColor(color: Int) = apply { this.endDayTextColor = color }

        /**
         * Set selected day background color
         * 선택된 날짜 배경색 설정
         *
         * @param color day text color / 날짜 배경색
         */
        fun setSelectedDayBgColor(color: String) = apply { this.selectedDayBgColor = Color.parseColor(color) }

        /**
         * Set selected day background color
         * 선택된 날짜 배경색 설정
         *
         * @param color day text color / 날짜 배경색
         */
        fun setSelectedDayBgColor(color: Int) = apply { this.selectedDayBgColor = color }

        fun build() = ColorData(defaultBgColor, defaultTextColor, todayTextColor,
                prevDayTextColor, startDayBgColor, startDayTextColor, endDayBgColor, endDayTextColor,
                selectedDayBgColor)
    }
}

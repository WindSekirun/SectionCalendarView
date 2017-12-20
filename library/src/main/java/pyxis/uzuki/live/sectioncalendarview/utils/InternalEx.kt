@file:JvmName("InternalEx")
@file:JvmMultifileClass

package pyxis.uzuki.live.sectioncalendarview.utils

import pyxis.uzuki.live.richutilskt.utils.toDateString

/**
 * SectionCalendarView
 * Class: InternalEx
 * Created by Pyxis on 2017-12-20.
 */
fun assignPad10(number: Int) = if (number < 10) number.toString().padStart(2, '0') else number.toString()

fun assignPad10(number: String) = if (number.toInt() < 10) number.padStart(2, '0') else number

fun compareDayEqual(a: String, b: String) = compareEqual(a.toDateString("yyyy.MM.dd", "yyyyMMdd"), b)

fun compareDayGreatEqual(a: String, b: String) = compareGreaterEqual(a.toDateString("yyyy.MM.dd", "yyyyMMdd"), b)

fun compareDayLessEqual(a: String, b: String) = compareLessEqual(a.toDateString("yyyy.MM.dd", "yyyyMMdd"), b)

fun compareDayGreat(a: String, b: String) = compareGreater(a.toDateString("yyyy.MM.dd", "yyyyMMdd"), b)

fun compareDayLess(a: String, b: String) = compareLess(a.toDateString("yyyy.MM.dd", "yyyyMMdd"), b)
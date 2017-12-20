@file:JvmName("CommonEx")
@file:JvmMultifileClass

package pyxis.uzuki.live.sectioncalendarview.utils

/**
 * SectionCalendarView
 * Class: CommonEx
 * Created by Pyxis on 2017-12-20.
 *
 * Description:
 */

@JvmOverloads
fun padStart(inputStr: String, length: Int, character: Char = '0') = inputStr.padStart(length, character)
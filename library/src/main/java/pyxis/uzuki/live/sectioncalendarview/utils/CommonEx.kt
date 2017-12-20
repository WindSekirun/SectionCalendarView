@file:JvmName("CommonEx")
@file:JvmMultifileClass

package pyxis.uzuki.live.sectioncalendarview.utils

import pyxis.uzuki.live.richutilskt.utils.isEmpty

/**
 * SectionCalendarView
 * Class: CommonEx
 * Created by Pyxis on 2017-12-20.
 *
 * Description:
 */

@JvmOverloads
fun padStart(inputStr: String, length: Int, character: Char = '0') = inputStr.padStart(length, character)

fun compareGreater(a: String, b: String) = if (notEmptyString(a, b)) a.toInt() > b.toInt() else false

fun compareEqual(a: String, b: String) = if (notEmptyString(a, b)) a.toInt() == b.toInt() else false

fun compareGreaterEqual(a: String, b: String) = if (notEmptyString(a, b)) a.toInt() >= b.toInt() else false

fun compareLessEqual(a: String, b: String) = if (notEmptyString(a, b)) a.toInt() <= b.toInt() else false

fun notEmptyString(vararg args: String) = args.map { !it.isEmpty() }.all { it }
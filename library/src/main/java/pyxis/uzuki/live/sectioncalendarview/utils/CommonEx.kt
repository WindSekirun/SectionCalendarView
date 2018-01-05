@file:JvmName("CommonEx")
@file:JvmMultifileClass

package pyxis.uzuki.live.sectioncalendarview.utils

import pyxis.uzuki.live.richutilskt.utils.isEmpty

/**
 * SectionCalendarView
 * Class: CommonEx
 * Created by Pyxis on 2017-12-20.
 */

fun notEmptyString(vararg args: String) = args.map { !it.isEmpty() }.all { it }


fun compareGreater(a: String, b: String) = if (notEmptyString(a, b)) a.toIntOrZero() > b.toIntOrZero() else false

fun compareLess(a: String, b: String) = if (notEmptyString(a, b)) a.toIntOrZero() < b.toIntOrZero() else false

fun compareEqual(a: String, b: String): Boolean = if (notEmptyString(a, b)) a.toIntOrZero() == b.toIntOrZero() else false

fun compareGreaterEqual(a: String, b: String) = if (notEmptyString(a, b)) a.toIntOrZero() >= b.toIntOrZero() else false

fun compareLessEqual(a: String, b: String) = if (notEmptyString(a, b)) a.toIntOrZero() <= b.toIntOrZero() else false

fun String.toIntOrZero(): Int = if (!this.isEmpty()) this.toInt() else 0
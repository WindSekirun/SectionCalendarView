# SectionCalendarView [![](https://jitpack.io/v/WindSekirun/SectionCalendarView.svg)](https://jitpack.io/#WindSekirun/SectionCalendarView) [![CircleCI](https://circleci.com/gh/WindSekirun/SectionCalendarView.svg?style=svg)](https://circleci.com/gh/WindSekirun/SectionCalendarView)

[![Kotlin](https://img.shields.io/badge/kotlin-1.2.0-blue.svg)](http://kotlinlang.org)	[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

<img src="https://github.com/WindSekirun/SectionCalendarView/blob/master/sample.png" width="202" height="360">

Custom CalendarView with selecting StartDay, EndDay for Android Application, written in Java and [Kotlin](http://kotlinlang.org)

### Usages

*rootProject/build.gradle*
```	
allprojects {
    repositories {
	    maven { url 'https://jitpack.io' }
    }
}
```

*app/build.gradle*
```
dependencies {
    implementation 'com.github.WindSekirun:SectionCalendarView:1.0.0'
}
```

#### XML

```XML
<pyxis.uzuki.live.sectioncalendarview.SectionCalendarView
        android:layout_width="match_parent"
        android:layout_height="340dp"
        android:layout_marginTop="10dp"
        android:id="@+id/calendarView" />
```

#### Kotlin

```Kotlin
calendarView.setDateFormat("yyyy MMM")
calendarView.setErrToastMessage("You can not select the previous date.")
calendarView.setOnDaySelectedListener { startDay, endDay ->
    txtStartDay.text = startDay
    txtEndDay.text = endDay
}
calendarView.buildCalendar()
```

#### Java

```Java
calendarView.setDateFormat("yyyy MMM");
calendarView.setErrToastMessage("You can not select the previous date.");
calendarView.setOnDaySelectedListener((startDay, endDay) -> {
    txtStartDay.setText(startDay);
    txtEndDay.setText(endDay);
});
calendarView.buildCalendar();
```

### License 

```
Copyright 2017 WindSekirun (DongGil, Seo)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

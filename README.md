# Android APP Project - SoList

### Project INTRO
This project is a todo list checking app development project.  
I'm developing an app now.  
and then I'm doing a lot of trial and error.  
I hope this project will be completed by this year.  

### Android Studio Version
#####
    Android Studio 3.5.1  
    Build #AI-191.8026.42.35.5900203, built on September 26, 2019  
    JRE: 1.8.0_202-release-1483-b03 amd64  
    JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o  
    Windows 10 10.0  

### Gradle Scripts
    // recyclerView set
    implementation 'com.android.support:recyclerview-v7:29.0.0'
    // cardView set
    implementation 'com.android.support:cardview-v7:29.0.0'

    // calendar library
    implementation 'com.github.prolificinteractive:material-calendarview:1.4.3'

    implementation "com.android.support:appcompat-v7:29.0.0"
    implementation 'com.android.support:design:29.1.1'

    // lifecycle set
    def lifecycle_version = "2.1.0"

    // ViewModel and LiveData
    implementation "androidx.lifecycle:lifecycle-extensions:$lifecycle_version"
    // alternatively - just ViewModel
    implementation "androidx.lifecycle:lifecycle-viewmodel:$lifecycle_version"
    // For Kotlin use lifecycle-viewmodel-ktx
    // alternatively - just LiveData
    implementation "androidx.lifecycle:lifecycle-livedata:$lifecycle_version"
    // alternatively - Lifecycles only (no ViewModel or LiveData). Some UI
    //     AndroidX libraries use this lightweight import for Lifecycle
    implementation "androidx.lifecycle:lifecycle-runtime:$lifecycle_version"

    annotationProcessor "androidx.lifecycle:lifecycle-compiler:$lifecycle_version"
    // For Kotlin use kapt instead of annotationProcessor
    // alternately - if using Java8, use the following instead of lifecycle-compiler
    implementation "androidx.lifecycle:lifecycle-common-java8:$lifecycle_version"

    // optional - ReactiveStreams support for LiveData
    implementation "androidx.lifecycle:lifecycle-reactivestreams:$lifecycle_version"
    // For Kotlin use lifecycle-reactivestreams-ktx

    // optional - Test helpers for LiveData
    testImplementation "androidx.arch.core:core-testing:$lifecycle_version"

    // room set
    def room_version = "2.2.0-beta01"
    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"
    // For Kotlin use kapt instead of annotationProcessor

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation "androidx.room:room-ktx:$room_version"
    // optional - RxJava support for Room
    implementation "androidx.room:room-rxjava2:$room_version"
    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation "androidx.room:room-guava:$room_version"
    // Test helpers
    testImplementation "androidx.room:room-testing:$room_version"


### Project LOG 
<details>
    <summary>VIEW LOG</summary>

* 191009 - created github repository and started anroid app dev
* 191018 - created fragment on Activity and then added custom listview on fragment.
* 191024 - applied realm with DBhelper (get list and insert list in listFragment) and applied recyclerView with onBindViewHolder.
* 191026 - created delete list function and source code refactoring (data set , adapter source , etc)
* 191101 - applied function of edit list and delete list, applied ViewPager with Fragment, set CalendarView on another fragment.
* 191102 - got date on listFragment, removed title bar and then put the date in that.
* 191103 - update ViewPager and TabLayout, changed EditText form and style and then applied function that hide keyboard when click add Button and layout. finally, changed date format when input Database.
* 191109 - applied open calendar on CalendarFragment and then get list for date on ListFragment, but calendar incomplete.
* 191112 - thank what apps i want to develop
* 191125 - applied list status. but, i found the wrong source between app and database. so stupid...
* 191126 - have tried it many times but nothing has changed. so i created a new project to follow from the start.
* 191127 - applied the MVVM architecture (RecyclerView, Adapter, ViewModel, LiveData, Room). and i changed DB to realm from room.
* 191129 - added list status options and set unfinished list on HomeFragment.
* 191204 - changed list get function from allList to allListForDate. so could get the list for date.
* 191206 - temporarily, applied various charts on SettingFragment. so i'm going to think how to get finished list count for each date.
</details>


### Project TODO
<details>
    <summary>VIEW TODO</summary>

* 191024 - ~~apply recyclerview (complete)~~ and create function that change and delete list (trying)
* 191026 - create change and ~~delete~~ function ~~and then code refactoring (all source)~~
* 191101 - ~~apply edit list function and change delete list function on recylerView~~, ~~apply ViewPager~~ and ~~set calendar on another fragment.~~
* 191102 - think about how to connect calendar and list and then how to show listView functions
* 191103 - ~~update list Fragment~~ and do things that didn't yesterday
* 191109 - ~~get list for date on calendar~~
* 191112 - ~~calendar sources analysis and find another soultions.~~
* 191119 - create custom calendar on new fragment
* 191125 - ~~apply status in list~~ and create the list complete graph for todo list analysis
* 191126 - ~~i will find the wrong parts in soruce and change that.~~
* 191127 - ~~change recyclerview on ListFragment and apply MVVM architecture components.~~
* 191129 - ~~add list status function and display unfinished list on HomeFragment~~
* 191204 - ~~get list for date on ListFragment~~
* 191206 - apply various charts
* 191213 - update list get function and unfinished list
</details>


### LICENSE's

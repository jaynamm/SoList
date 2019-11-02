# Android APP Project - ToDoList

### Project INTRODUCE
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

### Library
##### Project
    // realm
    classpath "io.realm:realm-gradle-plugin:3.5.0"
##### Module
    // recyclerView
    implementation 'com.android.support:recyclerview-v7:29.0.0'
    // cardView
    implementation 'com.android.support:cardview-v7:29.0.0'
    // support design
    implementation 'com.android.support:design:29.1.1'

### Project LOG
* 191009 - created github repository and started anroid app dev
* 191018 - created fragment on Activity and then added custom listview on fragment.
* 191024 - applied realm with DBhelper (get list and insert list in listFragment) and applied recyclerView with onBindViewHolder.
* 191026 - created delete list function and source code refactoring (data set , adapter source , etc)
* 191101 - applied function of edit list and delete list, applied ViewPager with Fragment, set CalendarView on another fragment.
* 191102 - got date on listFragment, removed title bar and then put the date in that.

### Project TODO
* 191024 - ~~apply recyclerview (complete)~~ and create function that change and delete list (trying)
* 191026 - create change and ~~delete~~ function ~~and then code refactoring (all source)~~
* 191101 - ~~apply edit list function and change delete list function on recylerView~~, ~~apply ViewPager~~ and ~~set calendar on another fragment.~~
* 191102 - think about how to connect calendar and list and then how to show listView functions

# Android APP Project

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

### Project LOG
* 191009 - create github repository and start anroid app dev
* 191018 - create fragment on Activity and then added custom listview on fragment.
* 191024 - apply realm with DBhelper (get list and insert list in listFragment) and apply recyclerView with onBindViewHolder.
* 191026 - create delete list function and code refectoring (data set , adapter source , etc)

### Project TODO
* 191024 - ~~apply recyclerview (complete)~~ and create function that change and delete list (trying)
* 191026 - create change and ~~delete~~ function ~~and then code refectoring (all source)~~
* 191101 - apply edit list and delete list function on recylerView, apply ViewPager and set calender

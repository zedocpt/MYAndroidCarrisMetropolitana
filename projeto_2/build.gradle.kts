// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id ("com.google.dagger.hilt.android") version "2.48" apply false

  //  id("com.android.application") version "8.3.1" apply false
   // id("com.android.library") version "8.4.0" apply false
   // id("org.jetbrains.kotlin.android") version "1.9.23" apply false


}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        mavenCentral()
    }
}
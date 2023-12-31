@file:Suppress("UNUSED_EXPRESSION")

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        ("com.android.tools.build:gradle:8.1.0")
    }
}
buildscript {
    // ...
    dependencies {
        // ...
        classpath ("com.google.gms:google-services:4.4.0")
    }
}

allprojects {
    // ...
    repositories {
        // ...
        google()
    }
}
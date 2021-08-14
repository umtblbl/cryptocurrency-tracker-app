import org.gradle.kotlin.dsl.`kotlin-dsl`

object Dependencies {
    const val AndroidBuildTools = "com.android.tools.build:gradle:4.2.1"
    const val kotlin = "script-runtime"
}

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(kotlin(Dependencies.kotlin))
    implementation(Dependencies.AndroidBuildTools)
}

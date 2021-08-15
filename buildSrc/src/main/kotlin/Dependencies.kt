object Apps {
    const val compileSdk = 29
    const val buildTools = "30.0.3"
    const val minSdk = 26
    const val targetSdk = 29
    const val applicationId = "com.umit.cryptocurrencytrackerapp"
    const val versionCode = 1
    const val versionName = "1.0"
    const val jvmTarget = "1.8"
}

object Versions {
    const val annotation = "1.2.0"
    const val appCompat = "1.3.0"
    const val constraintLayout = "2.0.4"
    const val coreKtx = "1.5.0"
    const val gradle = "4.2.1"
    const val fragment = "1.3.4"
    const val kotlin = "1.5.10"
    const val ktLint = "0.41.0"
    const val lifecycleViewModel = "2.3.1"
    const val lifecycleExtensions = "2.2.0"
    const val legacy = "1.0.0"
    const val googleServices = "4.3.8"
    const val legacySupport = "1.0.0"
    const val materialDesign = "1.4.0"

    // Rx
    const val rxBinding = "4.0.0"
    const val rxKotlin = "3.0.1"
    const val rxRelay = "3.0.0"

    // Navigation
    const val navigation = "2.3.5"

    // Gson
    const val gson = "2.8.7"

    // Retrofit
    const val retrofit = "2.9.0"
    const val retrofitConverterGson = "2.9.0"
    const val retrofitLoggingInterceptor = "4.9.1"
    const val rxJavaRetrofitAdapter = "3.0.0"

    // Glide
    const val glide = "4.12.0"

    // Lottie
    const val lottie = "3.7.0"

    // test
    const val androidJunit = "1.1.2"
    const val espresso = "3.3.0"
    const val junit = "4.13.2"

    // Dagger
    const val dagger = "2.38.1"

    // Room
    const val room = "2.3.0"

    // ViewPager2
    const val viewPager2 = "1.0.0"

    // Firebase
    const val firebase = "21.0.1"
}

object Libs {
    const val annotation = "androidx.annotation:annotation:${Versions.annotation}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val fragment = "androidx.fragment:fragment:${Versions.fragment}"
    const val ktLint = "com.pinterest:ktlint:${Versions.ktLint}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewModel}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}"
    const val googleMaterial = "com.google.android.material:material:${Versions.appCompat}"
    const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"

    // Rx
    const val rxBinding = "com.jakewharton.rxbinding4:rxbinding:${Versions.rxBinding}"
    const val rxBindingCore = "com.jakewharton.rxbinding4:rxbinding-core:${Versions.rxBinding}"
    const val rxBindingAppCompat = "com.jakewharton.rxbinding4:rxbinding-appcompat:${Versions.rxBinding}"
    const val rxRelay = "com.jakewharton.rxrelay3:rxrelay:${Versions.rxRelay}"
    const val rxKotlin = "io.reactivex.rxjava3:rxkotlin:${Versions.rxKotlin}"

    // Navigation
    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUIKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    // Gson
    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    // Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitConverterGson}"
    const val retrofitLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.retrofitLoggingInterceptor}"
    const val rxJavaRetrofitAdapter = "com.github.akarnokd:rxjava3-retrofit-adapter:${Versions.rxJavaRetrofitAdapter}"

    // Glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"

    // Lottie
    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"

    // Dagger
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    // Room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val room = "androidx.room:room-ktx:${Versions.room}"
    const val roomKapt = "androidx.room:room-compiler:${Versions.room}"

    // ViewPager2
    const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"

    // Firebase Auth
    const val firebaseAuth = "com.google.firebase:firebase-auth:${Versions.firebase}"
}

object Tests {
    const val jUnit = "junit:junit:${Versions.junit}"
    const val androidJunit = "androidx.test.ext:junit:${Versions.androidJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val roomTest = "androidx.room:room-testing:${Versions.room}"
}

object Paths {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val googleServices = "com.google.gms:google-services:${Versions.googleServices}"
    const val navigationArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}

object Plugins {
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "android"
    const val kotlinExtensions = "android.extensions"
    const val googleServices = "com.google.gms.google-services"
    const val kotlinKapt = "kapt"
    const val compilation = "scripts.compilation"
    const val ktLint = "scripts.ktlint"
    const val variants = "scripts.variants"
    const val navigationArgs = "androidx.navigation.safeargs.kotlin"
}
buildscript {

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Paths.gradle)
        classpath(Paths.kotlin)
        classpath(Paths.googleServices)
        classpath(Paths.navigationArgs)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.20")
    }
}


allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

subprojects {
    afterEvaluate {
        apply(plugin = Plugins.ktLint)
    }
}
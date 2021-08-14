package scripts

plugins { id("core.android") }

object BuildTypes {
    const val DEBUG = "debug"
    const val RELEASE = "release"
}

private object FlavorDimensions {
    const val DEFAULT = "default"
}

private object VariantConstants {
    const val rootUrl = "ROOT_URL"
    const val appName = "app_name"
}

private object SourceSets {
    val test = Pair("test", "src/unitTest")
}

fun createProductFlavor(variant: models.Variant) {
    android.productFlavors.create(variant.name) {
        // Manifest
        resValue("string", VariantConstants.appName, variant.appName)
        // Build Config
        buildConfigField("String", VariantConstants.rootUrl, "\"${variant.rootUrl}\"")

        versionName = variant.versionName
        versionCode = variant.versionCode
    }
}

android {
    buildTypes {
        getByName(BuildTypes.DEBUG) {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            isDebuggable = true
        }
        getByName(BuildTypes.RELEASE) {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    flavorDimensions(FlavorDimensions.DEFAULT)
    productFlavors {
        createProductFlavor(variants.ProdVariant)
    }

    sourceSets {
        getByName(SourceSets.test.first) {
            setRoot(SourceSets.test.second)
        }
    }
}

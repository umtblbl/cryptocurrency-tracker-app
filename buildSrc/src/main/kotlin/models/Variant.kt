package models

interface Variant {
    val name: String
    val appName: String
    val versionCode: Int
    val versionName: String
    val rootUrl: String
}
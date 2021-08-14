package variants

object ProdVariant: models.Variant {
    override val name = "Prod"
    override val appName = "@string/app_name_prod"
    override val versionCode = 1
    override val versionName = "1.0"
    override val rootUrl = "https://api.coingecko.com/api/v3/"
}
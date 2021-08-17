package com.umit.cryptocurrencytrackerapp.data.remote.api.model

import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CoinDetailModel(
    val id: String,
    val symbol: String,
    val name: String,

    @SerializedName("asset_platform_id")
    val assetPlatformID: String?,

    val platforms: Platforms,

    @SerializedName("block_time_in_minutes")
    val blockTimeInMinutes: Long,

    val categories: List<String>,

    @SerializedName("additional_notices")
    val additionalNotices: JsonArray,

    val localization: Tion,
    val description: Tion,
    val links: CoinDetailModelLinks,
    val image: Image,

    @SerializedName("country_origin")
    val countryOrigin: String,

    @SerializedName("contract_address")
    val contractAddress: String,

    @SerializedName("sentiment_votes_up_percentage")
    val sentimentVotesUpPercentage: Double,

    @SerializedName("sentiment_votes_down_percentage")
    val sentimentVotesDownPercentage: Double,

    @SerializedName("ico_data")
    val icoData: IcoData,

    @SerializedName("market_cap_rank")
    val marketCapRank: Long,

    @SerializedName("coingecko_rank")
    val coingeckoRank: Long,

    @SerializedName("coingecko_score")
    val coingeckoScore: Double,

    @SerializedName("developer_score")
    val developerScore: Double,

    @SerializedName("community_score")
    val communityScore: Double,

    @SerializedName("liquidity_score")
    val liquidityScore: Double,

    @SerializedName("public_interest_score")
    val publicInterestScore: Double,

    @SerializedName("market_data")
    val marketData: MarketData,

    @SerializedName("developer_data")
    val developerData: DeveloperData,

    @SerializedName("public_interest_stats")
    val publicInterestStats: PublicInterestStats,

    @SerializedName("status_updates")
    val statusUpdates: JsonArray,

    @SerializedName("last_updated")
    val lastUpdated: String,

    val tickers: List<Ticker>
)

data class Tion(
    val en: String,
    val de: String,
    val es: String,
    val fr: String,
    val it: String,
    val pl: String,
    val ro: String,
    val hu: String,
    val nl: String,
    val pt: String,
    val sv: String,
    val vi: String,
    val tr: String,
    val ru: String,
    val ja: String,
    val zh: String,

    @SerializedName("zh-tw")
    val zhTw: String,

    val ko: String,
    val ar: String,
    val th: String,
    val id: String
) : Serializable

data class DeveloperData(
    val forks: Long,
    val stars: Long,
    val subscribers: Long,

    @SerializedName("total_issues")
    val totalIssues: Long,

    @SerializedName("closed_issues")
    val closedIssues: Long,

    @SerializedName("pull_requests_merged")
    val pullRequestsMerged: Long,

    @SerializedName("pull_request_contributors")
    val pullRequestContributors: Long,

    @SerializedName("commit_count_4_weeks")
    val commitCount4_Weeks: Long,

    @SerializedName("last_4_weeks_commit_activity_series")
    val last4_WeeksCommitActivitySeries: JsonArray
) : Serializable

data class IcoData(

    @SerializedName("short_desc")
    val shortDesc: String,

    val description: HashMap<String, String>? = null,
    val links: IcoDataLinks,

    @SerializedName("softcap_currency")
    val softcapCurrency: String,

    @SerializedName("hardcap_currency")
    val hardcapCurrency: String,

    @SerializedName("total_raised_currency")
    val totalRaisedCurrency: String,

    @SerializedName("quote_pre_sale_currency")
    val quotePreSaleCurrency: String,

    @SerializedName("quote_public_sale_currency")
    val quotePublicSaleCurrency: String,

    @SerializedName("base_public_sale_amount")
    val basePublicSaleAmount: Double,

    @SerializedName("quote_public_sale_amount")
    val quotePublicSaleAmount: Double,

    @SerializedName("accepting_currencies")
    val acceptingCurrencies: String,

    @SerializedName("country_origin")
    val countryOrigin: String,

    @SerializedName("whitelist_url")
    val whitelistURL: String,

    @SerializedName("bounty_detail_url")
    val bountyDetailURL: String,

    @SerializedName("kyc_required")
    val kycRequired: Boolean,

    @SerializedName("pre_sale_ended")
    val preSaleEnded: Boolean
) : Serializable

class IcoDataLinks

data class Image(
    val thumb: String,
    val small: String,
    val large: String
) : Serializable

data class CoinDetailModelLinks(
    val homepage: List<String>,

    @SerializedName("blockchain_site")
    val blockchainSite: List<String>,

    @SerializedName("official_forum_url")
    val officialForumURL: List<String>,

    @SerializedName("chat_url")
    val chatURL: List<String>,

    @SerializedName("announcement_url")
    val announcementURL: List<String>,

    @SerializedName("twitter_screen_name")
    val twitterScreenName: String,

    @SerializedName("facebook_username")
    val facebookUsername: String,

    @SerializedName("telegram_channel_identifier")
    val telegramChannelIdentifier: String,

    @SerializedName("repos_url")
    val reposURL: ReposURL
) : Serializable

data class ReposURL(
    val github: JsonArray,
    val bitbucket: JsonArray
)

data class MarketData(
    @SerializedName("current_price")
    val currentPrice: Map<String, Double>,

    val roi: Roi,
    val ath: Map<String, Double>,

    @SerializedName("ath_change_percentage")
    val athChangePercentage: Map<String, Double>,

    @SerializedName("ath_date")
    val athDate: Map<String, String>,

    val atl: Map<String, Double>,

    @SerializedName("atl_change_percentage")
    val atlChangePercentage: Map<String, Double>,

    @SerializedName("atl_date")
    val atlDate: Map<String, String>,

    @SerializedName("market_cap")
    val marketCap: Map<String, Double>,

    @SerializedName("market_cap_rank")
    val marketCapRank: Long,

    @SerializedName("fully_diluted_valuation")
    val fullyDilutedValuation: Map<String, Double>,

    @SerializedName("total_volume")
    val totalVolume: Map<String, Double>,

    @SerializedName("high_24h")
    val high24H: Map<String, Double>,

    @SerializedName("low_24h")
    val low24H: Map<String, Double>,

    @SerializedName("price_change_24h")
    val priceChange24H: Double,

    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24H: Double,

    @SerializedName("price_change_percentage_7d")
    val priceChangePercentage7D: Double,

    @SerializedName("price_change_percentage_14d")
    val priceChangePercentage14D: Double,

    @SerializedName("price_change_percentage_30d")
    val priceChangePercentage30D: Double,

    @SerializedName("price_change_percentage_60d")
    val priceChangePercentage60D: Double,

    @SerializedName("price_change_percentage_200d")
    val priceChangePercentage200D: Double,

    @SerializedName("price_change_percentage_1y")
    val priceChangePercentage1Y: Double,

    @SerializedName("market_cap_change_24h")
    val marketCapChange24H: Double,

    @SerializedName("market_cap_change_percentage_24h")
    val marketCapChangePercentage24H: Double,

    @SerializedName("price_change_24h_in_currency")
    val priceChange24HInCurrency: Map<String, Double>,

    @SerializedName("price_change_percentage_1h_in_currency")
    val priceChangePercentage1HInCurrency: Map<String, Double>,

    @SerializedName("price_change_percentage_24h_in_currency")
    val priceChangePercentage24HInCurrency: Map<String, Double>,

    @SerializedName("price_change_percentage_7d_in_currency")
    val priceChangePercentage7DInCurrency: Map<String, Double>,

    @SerializedName("price_change_percentage_14d_in_currency")
    val priceChangePercentage14DInCurrency: Map<String, Double>,

    @SerializedName("price_change_percentage_30d_in_currency")
    val priceChangePercentage30DInCurrency: Map<String, Double>,

    @SerializedName("price_change_percentage_60d_in_currency")
    val priceChangePercentage60DInCurrency: Map<String, Double>,

    @SerializedName("price_change_percentage_200d_in_currency")
    val priceChangePercentage200DInCurrency: Map<String, Double>,

    @SerializedName("price_change_percentage_1y_in_currency")
    val priceChangePercentage1YInCurrency: Map<String, Double>,

    @SerializedName("market_cap_change_24h_in_currency")
    val marketCapChange24HInCurrency: Map<String, Double>,

    @SerializedName("market_cap_change_percentage_24h_in_currency")
    val marketCapChangePercentage24HInCurrency: Map<String, Double>,

    @SerializedName("total_supply")
    val totalSupply: Double,

    @SerializedName("max_supply")
    val maxSupply: Double,

    @SerializedName("circulating_supply")
    val circulatingSupply: Double,

    @SerializedName("last_updated")
    val lastUpdated: String
) : Serializable

data class Roi(
    val times: Double,
    val currency: String,
    val percentage: Double
) : Serializable

data class Platforms(
    val ethereum: String
) : Serializable

data class PublicInterestStats(
    @SerializedName("alexa_rank")
    val alexaRank: Double,
) : Serializable

data class Ticker(
    val base: String,
    val target: String,
    val market: Market,
    val last: Double,
    val volume: Double,

    @SerializedName("converted_last")
    val convertedLast: Map<String, Double>,

    @SerializedName("converted_volume")
    val convertedVolume: Map<String, Double>,

    @SerializedName("bid_ask_spread_percentage")
    val bidAskSpreadPercentage: Double,

    val timestamp: String,

    @SerializedName("last_traded_at")
    val lastTradedAt: String,

    @SerializedName("last_fetch_at")
    val lastFetchAt: String,

    @SerializedName("is_anomaly")
    val isAnomaly: Boolean,

    @SerializedName("is_stale")
    val isStale: Boolean,

    @SerializedName("trade_url")
    val tradeURL: String? = null,

    @SerializedName("token_info_url")
    val tokenInfoURL: String? = null,

    @SerializedName("coin_id")
    val coinID: String,

    @SerializedName("target_coin_id")
    val targetCoinID: String? = null
) : Serializable

data class Market(
    val name: String,
    val identifier: String,

    @SerializedName("has_trading_incentive")
    val hasTradingIncentive: Boolean
) : Serializable

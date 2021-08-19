package com.example.starwarsredditapp.MainActivity.models.RedditModel


import com.fasterxml.jackson.annotation.JsonProperty

data class AllAwarding(
    @JsonProperty("award_sub_type")
    val awardSubType: String,
    @JsonProperty("award_type")
    val awardType: String,
    @JsonProperty("awardings_required_to_grant_benefits")
    val awardingsRequiredToGrantBenefits: Any?,
    @JsonProperty("coin_price")
    val coinPrice: Int,
    @JsonProperty("coin_reward")
    val coinReward: Int,
    @JsonProperty("count")
    val count: Int,
    @JsonProperty("days_of_drip_extension")
    val daysOfDripExtension: Int,
    @JsonProperty("days_of_premium")
    val daysOfPremium: Int,
    @JsonProperty("description")
    val description: String,
    @JsonProperty("end_date")
    val endDate: Any?,
    @JsonProperty("giver_coin_reward")
    val giverCoinReward: Any?,
    @JsonProperty("icon_format")
    val iconFormat: Any?,
    @JsonProperty("icon_height")
    val iconHeight: Int,
    @JsonProperty("icon_url")
    val iconUrl: String,
    @JsonProperty("icon_width")
    val iconWidth: Int,
    @JsonProperty("id")
    val id: String,
    @JsonProperty("is_enabled")
    val isEnabled: Boolean,
    @JsonProperty("is_new")
    val isNew: Boolean,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("penny_donate")
    val pennyDonate: Any?,
    @JsonProperty("penny_price")
    val pennyPrice: Any?,
    @JsonProperty("resized_icons")
    val resizedIcons: List<ResizedIcon>,
    @JsonProperty("resized_static_icons")
    val resizedStaticIcons: List<ResizedStaticIcon>,
    @JsonProperty("start_date")
    val startDate: Any?,
    @JsonProperty("static_icon_height")
    val staticIconHeight: Int,
    @JsonProperty("static_icon_url")
    val staticIconUrl: String,
    @JsonProperty("static_icon_width")
    val staticIconWidth: Int,
    @JsonProperty("subreddit_coin_reward")
    val subredditCoinReward: Int,
    @JsonProperty("subreddit_id")
    val subredditId: Any?,
    @JsonProperty("tiers_by_required_awardings")
    val tiersByRequiredAwardings: Any?
)
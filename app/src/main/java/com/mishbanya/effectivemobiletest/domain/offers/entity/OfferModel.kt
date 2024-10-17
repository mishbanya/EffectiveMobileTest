package com.mishbanya.effectivemobiletest.domain.offers.entity

import com.google.gson.annotations.SerializedName

data class OfferModel(
    @SerializedName("id") val id: String?,
    @SerializedName("title") val title: String,
    @SerializedName("button") val buttonModel: ButtonModel?,
    @SerializedName("link") val link: String
)

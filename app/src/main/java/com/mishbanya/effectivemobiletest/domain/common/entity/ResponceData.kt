package com.mishbanya.effectivemobiletest.domain.common.entity

import com.google.gson.annotations.SerializedName
import com.mishbanya.effectivemobiletest.domain.offers.entity.Offer
import com.mishbanya.effectivemobiletest.domain.vacancies.entity.Vacancy

data class ResponseData(
    @SerializedName("offers") val offers: List<Offer>,
    @SerializedName("vacancies") val vacancies: List<Vacancy>
)

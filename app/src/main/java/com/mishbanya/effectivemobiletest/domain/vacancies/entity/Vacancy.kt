package com.mishbanya.effectivemobiletest.domain.vacancies.entity

import com.google.gson.annotations.SerializedName

data class Vacancy(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("address") val address: Address,
    @SerializedName("company") val company: String,
    @SerializedName("experience") val experience: Experience,
    @SerializedName("publishedDate") val publishedDate: String,
    @SerializedName("isFavorite") val isFavorite: Boolean,
    @SerializedName("salary") val salary: Salary,
    @SerializedName("schedules") val schedules: List<String>,
    @SerializedName("description") val description: String
)

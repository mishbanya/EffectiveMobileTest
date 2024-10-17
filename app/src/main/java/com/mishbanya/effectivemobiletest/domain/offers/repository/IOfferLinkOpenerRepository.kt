package com.mishbanya.effectivemobiletest.domain.offers.repository

import android.content.Context

interface IOfferLinkOpenerRepository {
    fun offerLinkOpen(link: String, context: Context)
}
package com.mishbanya.effectivemobiletest.presentation.viewmodels

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mishbanya.effectivemobiletest.domain.common.entity.ResponseData
import com.mishbanya.effectivemobiletest.domain.common.repository.IOffersAndVacanciesRepository
import com.mishbanya.effectivemobiletest.domain.offers.entity.OfferModel
import com.mishbanya.effectivemobiletest.domain.offers.repository.IOfferLinkOpenerRepository
import com.mishbanya.effectivemobiletest.domain.vacancies.entity.VacancyModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val offersAndVacanciesRepository: IOffersAndVacanciesRepository,
    private val offerLinkOpenerRepository: IOfferLinkOpenerRepository
) :ViewModel() {
    private val disposables = CompositeDisposable()
    private val _offers = MutableLiveData<List<OfferModel>?>()
    private val _vacancies = MutableLiveData<List<VacancyModel>?>()

    val offers: MutableLiveData<List<OfferModel>?>
        get() = _offers

    val vacancies: MutableLiveData<List<VacancyModel>?>
        get() = _vacancies
    private fun setResponse(responseData: ResponseData?) {
        if (responseData != null) {
            _offers.value = responseData.offerModels
            _vacancies.value = responseData.vacancies
        }
    }

    fun getOffersAndVacancies(){
        disposables.clear()
        val disposable = offersAndVacanciesRepository.getOffersAndVacancies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                if (response.isSuccessful) {
                    response.body()?.let { setResponse(it) }
                } else {
                    Log.e("loginProcess", "login failed: ${response.code()}")
                    setResponse(null)
                }
            }, { error ->
                Log.e("loginProcess", "login failed", error)

                if (error is HttpException) {
                    Log.d("loginProcess", "HTTP Error: ${error.code()}")
                } else {
                    Log.d("loginProcess", "Error: ${error.message}")
                }
                setResponse(null)
            })
        disposables.add(disposable)
    }

    fun offerClick(context: Context, position: Int) {
        val offerList = _offers.value?.toList()
        if (offerList != null) {
            val link = offerList[position].link
            offerLinkOpenerRepository.offerLinkOpen(link, context)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
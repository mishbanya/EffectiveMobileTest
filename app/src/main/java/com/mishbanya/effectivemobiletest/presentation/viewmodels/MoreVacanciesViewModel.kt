package com.mishbanya.effectivemobiletest.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mishbanya.effectivemobiletest.domain.common.repository.IOffersAndVacanciesRepository
import com.mishbanya.effectivemobiletest.domain.vacancies.entity.VacancyModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MoreVacanciesViewModel @Inject constructor(
):ViewModel(){
    private val disposables = CompositeDisposable()
    private val _vacancies = MutableLiveData<List<VacancyModel>?>()

    val vacancies: MutableLiveData<List<VacancyModel>?>
        get() = _vacancies

    private fun setResponse(responseData: List<VacancyModel>?) {
        if (responseData != null) {
            _vacancies.value = responseData
        }
    }
    fun getVacancies(){
        disposables.clear()
        //TODO
        //disposables.add(disposable)
    }
    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
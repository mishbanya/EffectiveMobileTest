package com.mishbanya.effectivemobiletest.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mishbanya.effectivemobiletest.domain.common.repository.IMultipleLangRepository
import com.mishbanya.effectivemobiletest.domain.common.repository.IOffersAndVacanciesRepository
import com.mishbanya.effectivemobiletest.domain.vacancies.entity.VacancyModel
import com.mishbanya.effectivemobiletest.domain.vacancies.repository.IChangeVacancyFavoritenessRepository
import com.mishbanya.effectivemobiletest.domain.vacancies.repository.IVacanciesGetterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MoreVacanciesViewModel @Inject constructor(
    private val vacanciesGetterRepositoryImpl: IVacanciesGetterRepository,
    private val changeVacancyFavoritenessRepository: IChangeVacancyFavoritenessRepository,
    private val multipleLangRepository: IMultipleLangRepository
):ViewModel(){
    private val _vacancies = MutableLiveData<List<VacancyModel>?>()

    val vacancies: MutableLiveData<List<VacancyModel>?>
        get() = _vacancies

    private fun setResponse(responseData: List<VacancyModel>?) {
        if (responseData != null) {
            _vacancies.value = responseData
        }
    }
    fun changeFavoriteness(position: Int){
        val vacancyList = _vacancies.value?.toList()
        vacancyList?.get(position)?.let { changeVacancyFavoritenessRepository.changeFavoriteness(it.id) }
        setResponse(getVacanciesFromSP())
    }
    fun countMultipleVacancies(count: Int): String{
        return multipleLangRepository.multipleVacanciesLang(count)
    }
    fun getVacancies(){
        try {
            setResponse(getVacanciesFromSP())
        }catch (e: Exception){
            Log.e("MoreVacanciesViewModel", "Vacancies get failed.", e)
        }
    }
    private fun getVacanciesFromSP(): List<VacancyModel>?{
        return vacanciesGetterRepositoryImpl.getVacancies()
    }
}
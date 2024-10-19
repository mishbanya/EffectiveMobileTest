package com.mishbanya.effectivemobiletest.data.vacancies.repositoryimpl

import com.mishbanya.effectivemobiletest.domain.vacancies.repository.IChangeVacancyFavoritenessRepository
import com.mishbanya.effectivemobiletest.domain.vacancies.repository.IVacanciesGetterRepository
import com.mishbanya.effectivemobiletest.domain.vacancies.repository.IVacanciesSaverRepository
import javax.inject.Inject

class ChangeVacancyFavoritenessRepositoryImpl@Inject constructor(
    private val vacanciesSaverRepository: IVacanciesSaverRepository,
    private val vacanciesGetterRepository: IVacanciesGetterRepository
): IChangeVacancyFavoritenessRepository {
    override fun changeFavoriteness(id: String) {
        var vacancies = vacanciesGetterRepository.getVacancies()
        if (vacancies != null) {
            for(vacancy in vacancies){
                if(vacancy.id == id) {
                    vacancy.isFavorite = !vacancy.isFavorite
                    break
                }
            }
        }
        vacanciesSaverRepository.saveVacancies(vacancies)
    }
}
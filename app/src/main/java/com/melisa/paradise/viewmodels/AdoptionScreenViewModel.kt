package com.melisa.paradise.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melisa.paradise.auth.AuthenticationService
import com.melisa.paradise.data.Repository
import com.melisa.paradise.data.domain.PetInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface AdoptionScreenViewModel {
    val featuredList: State<List<PetInfo>>
    val recommendedList: State<List<PetInfo>>
    val currentlyAppliedFilter: State<FilterOptions>

    fun addPetToFavourites(petInfo: PetInfo)

    fun removePetFromFavourites(petInfo: PetInfo)

    fun sendRequestForAdoption(petInfo: PetInfo)

    fun applyFilter(filterOption: FilterOptions)

    enum class FilterOptions { ALL, DOGS, CATS }
}

class EdenAdoptionScreenViewModel(
    private val repository: Repository,
    private val authenticationService: AuthenticationService,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel(), AdoptionScreenViewModel {
    private val petsAvailableForAdoption = repository.petsAvailableForAdoption

    private val _currentlyAppliedFilter = mutableStateOf(AdoptionScreenViewModel.FilterOptions.ALL)
    override val currentlyAppliedFilter =
        _currentlyAppliedFilter as State<AdoptionScreenViewModel.FilterOptions>

    private val _featuredList = mutableStateOf(emptyList<PetInfo>())
    override val featuredList = _featuredList as State<List<PetInfo>>

    private val _recommendedList = mutableStateOf(emptyList<PetInfo>())
    override val recommendedList = _recommendedList as State<List<PetInfo>>

    private val petsAvailableForAdoptionObserver = Observer<List<PetInfo>> { newPetInfoList ->
        val filteredList = applyFilterToList(currentlyAppliedFilter.value, newPetInfoList)
        _featuredList.value = filteredList
        _recommendedList.value = filteredList
    }

    init {
        petsAvailableForAdoption.observeForever(petsAvailableForAdoptionObserver)
    }

    private fun applyFilterToList(
        selectedFilter: AdoptionScreenViewModel.FilterOptions,
        list: List<PetInfo>
    ): List<PetInfo> = when (selectedFilter) {
        AdoptionScreenViewModel.FilterOptions.ALL -> list
        AdoptionScreenViewModel.FilterOptions.DOGS -> list.filter { it.type == "Dog" }
        AdoptionScreenViewModel.FilterOptions.CATS -> list.filter { it.type == "Cat" }
    }

    override fun sendRequestForAdoption(petInfo: PetInfo) {
        authenticationService.currentUser?.let { currentUser ->
            viewModelScope.launch(defaultDispatcher) {
                repository.sendRequestForAdoption(currentUser, petInfo)
            }
        }
    }

    override fun addPetToFavourites(petInfo: PetInfo) {
    }

    override fun removePetFromFavourites(petInfo: PetInfo) {
    }

    override fun applyFilter(filterOption: AdoptionScreenViewModel.FilterOptions) {
        _currentlyAppliedFilter.value = filterOption
        // filter list
        petsAvailableForAdoption.value?.let { petInfoList ->
            val filteredList = applyFilterToList(filterOption, petInfoList)
            _featuredList.value = filteredList
            _recommendedList.value = filteredList
        }
    }

    override fun onCleared() {
        super.onCleared()
        petsAvailableForAdoption.removeObserver(petsAvailableForAdoptionObserver)

    }
}


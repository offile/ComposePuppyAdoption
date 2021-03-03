package com.example.androiddevchallenge.ui.home

import androidx.lifecycle.*
import com.example.androiddevchallenge.data.AdoptionRepository
import com.example.androiddevchallenge.data.State
import com.example.androiddevchallenge.model.Adoption
import kotlinx.coroutines.launch

class HomeViewModel constructor(
    private val repository: AdoptionRepository,
) : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    private val _adoptionList = MutableLiveData<List<Adoption>>()
    val adoptionList: LiveData<List<Adoption>>  = _adoptionList

    init {
        loadData()
    }

    fun loadData(){
        viewModelScope.launch {
            _state.value = State.Loading
            _adoptionList.value = repository.getAdoptions()
            _state.value = State.Success
        }
    }
}
package com.example.androiddevchallenge.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.androiddevchallenge.data.AdoptionRepository
import com.example.androiddevchallenge.data.State
import com.example.androiddevchallenge.model.Adoption

class DetailViewModel(
    private val repository: AdoptionRepository,
    private val id: String
) : ViewModel() {
    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    val adoption: LiveData<Adoption> = liveData {
        _state.value = State.Loading
        val data = repository.getAdoption(id)
        if(data != null){
            emit(data)
            _state.value = State.Success
        }else{
            _state.value = State.Fail
        }
    }
}
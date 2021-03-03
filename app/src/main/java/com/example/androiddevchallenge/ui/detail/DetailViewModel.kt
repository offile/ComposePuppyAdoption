/*
 * Copyright $YEAR The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
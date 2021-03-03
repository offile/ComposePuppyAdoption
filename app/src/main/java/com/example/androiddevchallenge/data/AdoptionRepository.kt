package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.model.Adoption

interface AdoptionRepository {

    suspend fun getAdoption(id: String): Adoption?

    suspend fun getAdoptions(): List<Adoption>
}
package com.example.androiddevchallenge.data.impl

import com.example.androiddevchallenge.data.AdoptionRepository
import com.example.androiddevchallenge.model.Adoption

class AdoptionRepositoryImpl : AdoptionRepository {
    override suspend fun getAdoption(id: String): Adoption? {
        return adoptionData.find { it.id == id }
    }

    override suspend fun getAdoptions(): List<Adoption> {
        return adoptionData
    }
}

val adoptionRepository: AdoptionRepository = AdoptionRepositoryImpl()
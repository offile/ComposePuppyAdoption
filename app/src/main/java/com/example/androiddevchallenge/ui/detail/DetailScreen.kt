/*
 * Copyright 2021 The Android Open Source Project
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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.Icon
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.State
import com.example.androiddevchallenge.data.impl.adoptionRepository
import com.example.androiddevchallenge.model.Adoption

@Composable
fun DetailScreen(
    id: String,
    navController: NavHostController
){
    val detailViewModel = DetailViewModel(adoptionRepository, id)
    val state: State? by detailViewModel.state.observeAsState(State.Nothing)
    val data: Adoption? by detailViewModel.adoption.observeAsState()
    Scaffold(
        topBar = {
            val title = data?.name ?: ""
            TopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24), contentDescription = null)
                    }
                }
            )
        },
    ){
        if(state == State.Success){
            SuccessContent(data!!)
        }
    }
}

@Composable
fun SuccessContent(adoption: Adoption){
    val modifier = Modifier.padding(top = 10.dp)
    Column(modifier = Modifier.padding(10.dp)) {
        Card(elevation = 5.dp) {
            Image(
                painter = painterResource(id = adoption.image),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
        }
        val name = stringResource(id = R.string.adoption_name, adoption.name)
        val age = stringResource(id = R.string.adoption_age, adoption.age)
        val from = stringResource(id = R.string.adoption_from, adoption.from)
        val info = stringResource(id = R.string.adoption_detail, adoption.name, adoption.age, adoption.name)

        Text(text = name, style = MaterialTheme.typography.subtitle1, modifier = modifier)
        Text(text = age, style = MaterialTheme.typography.subtitle1, modifier = modifier)
        Text(text = from, style = MaterialTheme.typography.subtitle1, modifier = modifier)
        Text(text = info, style = MaterialTheme.typography.body1, modifier = modifier)
    }
}
package com.example.androiddevchallenge.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.State
import com.example.androiddevchallenge.data.impl.adoptionRepository
import com.example.androiddevchallenge.model.Adoption
import androidx.compose.foundation.layout.Row
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController: NavHostController) {
    val homeViewModel = HomeViewModel(adoptionRepository)
    val state: State? by homeViewModel.state.observeAsState()
    val data: List<Adoption> by homeViewModel.adoptionList.observeAsState(emptyList())
    Scaffold(
        topBar = {
            val title = stringResource(id = R.string.app_name)
            TopAppBar(
                title = { Text(text = title) },
            )
        },
    ){
        if(state == State.Success){
            SuccessContent(navController, data = data)
        }
    }
}

@Composable
fun SuccessContent(navController: NavHostController, data: List<Adoption>){
    LazyColumn{
        items(data){ item ->
            Column {
                AdoptionItem(item){ navController.navigate("detail/${item.id}") }
                ItemDivider()
            }
        }
    }
}

@Composable
fun ItemDivider(){
    Divider(
        modifier = Modifier.padding(horizontal = 16.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}

@Composable
fun AdoptionItem(adoption: Adoption,onClick: () -> Unit){
    Row(modifier = Modifier
        .clickable(onClick = onClick)
        .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = adoption.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(end = 16.dp)
                .size(100.dp, 80.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
            val name = stringResource(id = R.string.adoption_name, adoption.name)
            val age = stringResource(id = R.string.adoption_age, adoption.age)
            val from = stringResource(id = R.string.adoption_from, adoption.from)

            Text(text = name, style = MaterialTheme.typography.subtitle1)
            Text(text = age, style = MaterialTheme.typography.body1)
            Text(text = from, style = MaterialTheme.typography.body1)
        }
    }
}
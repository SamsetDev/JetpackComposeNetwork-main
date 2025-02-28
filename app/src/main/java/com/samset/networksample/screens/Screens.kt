package com.samset.networksample.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samset.networksample.component.CardView
import com.samset.networksample.modal.User
import com.samset.networksample.screens.viewmodel.HomeViewModel
import com.samset.networksample.utils.ApiState

/**
 * Created by Samset on 2/28/25.
Copyright (c) 2025  All rights reserved.
 */

@Composable
fun HomeScreen(innerPadding: PaddingValues) {
    val viewModel: HomeViewModel = viewModel()
    val usersState by viewModel.users

    LaunchedEffect(Unit) {
        viewModel.fetchUserData()
    }
    when (usersState) {

        is ApiState.Loading -> {
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                CircularProgressIndicator(
                    modifier = Modifier
                        .width(50.dp).height(50.dp)
                        .aspectRatio(1f)
                )
            }
        }

        is ApiState.Success -> {
            val users = (usersState as ApiState.Success<List<User>>).data
            Column(
                modifier = Modifier
                  .fillMaxSize()
                  .padding(top = innerPadding.calculateTopPadding())
            ) {
                LazyColumn {
                    itemsIndexed(users) { index, data ->
                        CardView(data.login)
                    }
                }

            }
        }

        is ApiState.Error -> {
          val error=usersState as ApiState.Error
              Text(text = "Error Occured ${error}")
        }

    }

}
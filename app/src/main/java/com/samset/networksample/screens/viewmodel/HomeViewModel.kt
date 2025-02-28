package com.samset.networksample.screens.viewmodel

import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samset.networksample.modal.User
import com.samset.networksample.network.ApiService
import com.samset.networksample.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Samset on 2/28/25.
Copyright (c) 2025  All rights reserved.
 */

@HiltViewModel
public class HomeViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    private val data = mutableStateOf<ApiState<List<User>>>(ApiState.Loading)
    val users: State<ApiState<List<User>>> = data

    fun fetchUserData(){

        viewModelScope.launch {
            try {
                val response=apiService.getUser()
                data.value=ApiState.Success(response)
            }catch (e:Exception){
                data.value=ApiState.Error(e.message.toString())
            }
        }
    }


}
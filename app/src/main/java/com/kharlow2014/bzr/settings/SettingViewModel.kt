package com.kharlow2014.bzr.settings

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: SettingRepository
) : ViewModel() {

    fun generateData() {
        viewModelScope.launch {
            repository.insertTestData()
        }
    }
}

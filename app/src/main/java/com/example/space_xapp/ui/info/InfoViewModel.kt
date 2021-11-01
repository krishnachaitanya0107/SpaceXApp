package com.example.space_xapp.ui.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.space_xapp.data.SpacexRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    repository: SpacexRepository
) : ViewModel() {

    val info = repository.getCompanyInfo().asLiveData()

}
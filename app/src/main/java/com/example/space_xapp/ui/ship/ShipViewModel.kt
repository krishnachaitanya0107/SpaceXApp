package com.example.space_xapp.ui.ship

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.space_xapp.data.SpacexRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ShipViewModel @Inject constructor(
    repository: SpacexRepository
) : ViewModel() {

    val ships = repository.getShips().asLiveData()

}
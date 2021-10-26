package com.example.space_xapp.ui.crew

import androidx.lifecycle.*
import com.example.space_xapp.api.SpacexApi
import com.example.space_xapp.data.Crew
import com.example.space_xapp.data.SpacexRepository
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class CrewViewModel @Inject constructor(
    repository: SpacexRepository
) : ViewModel() {

    val crew=repository.getCrew().asLiveData()

    /*private val crewLiveData= MutableLiveData<List<Crew>>()
    val crew : LiveData<List<Crew>> = crewLiveData

    init {
        viewModelScope.launch {
            val crewDetails = api.getCrewDetails()

            crewLiveData.value = crewDetails

        }
    }*/

}
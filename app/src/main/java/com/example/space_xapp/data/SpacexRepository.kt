package com.example.space_xapp.data

import androidx.room.withTransaction
import com.example.space_xapp.api.SpacexApi
import com.example.space_xapp.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject


class SpacexRepository @Inject constructor(
    private val spacexApi: SpacexApi,
    private val db:SpacexDatabase
){
    private val crewDao=db.crewDao()
    private val shipDao=db.shipDao()

    fun getCrew()= networkBoundResource(
        query = {
            crewDao.getAllCrew()
        },
        fetch = {
            delay(300)
            spacexApi.getCrewData()
        },
        safeFetchResult = { crew->
            db.withTransaction {
                crewDao.deleteAllCrew()
                crewDao.insertCrewData(crew)
            }
        }
    )

    fun getShips()=networkBoundResource(
        query = {
            shipDao.getAllShips()
        },
        fetch = {
            delay(300)
            spacexApi.getShipsDetails()
        },
        safeFetchResult = { ship->
            db.withTransaction {
                shipDao.deleteAllShipsData()
                shipDao.insertShipData(ship)
            }
        }
    )

}
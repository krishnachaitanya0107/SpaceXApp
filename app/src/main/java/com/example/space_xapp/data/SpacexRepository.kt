package com.example.space_xapp.data

import androidx.room.withTransaction
import com.example.space_xapp.api.SpacexApi
import com.example.space_xapp.di.SharedPref
import com.example.space_xapp.util.Constants
import com.example.space_xapp.util.networkBoundResource
import kotlinx.coroutines.delay
import java.util.*
import javax.inject.Inject
import kotlin.math.abs


class SpacexRepository @Inject constructor(
    private val spacexApi: SpacexApi,
    private val db: SpacexDatabase
) {
    private val crewDao = db.crewDao()
    private val shipDao = db.shipDao()
    private val infoDao = db.infoDao()

    fun getCrew() = networkBoundResource(
        query = {
            crewDao.getAllCrew()
        },
        fetch = {
            delay(300)
            spacexApi.getCrewData()
        },
        safeFetchResult = { crew ->
            db.withTransaction {
                crewDao.deleteAllCrew()
                crewDao.insertCrewData(crew)
            }
        }
    )

    /*fun getShips(lastUpdated: Long , updateLastFetched:(currentMillis:Long)->Unit) = networkBoundResource(
        query = {
            shipDao.getAllShips()
        },
        fetch = {
            delay(300)
            spacexApi.getShipsDetails()
        },
        safeFetchResult = { ship ->
            db.withTransaction {
                shipDao.deleteAllShipsData()
                shipDao.insertShipData(ship)
                updateLastFetched(System.currentTimeMillis())
            }
        },
        shouldFetch = {
            abs(System.currentTimeMillis() - lastUpdated) >= Constants.oneHr
        }
    )*/

    fun getShips() = networkBoundResource(
        query = {
            shipDao.getAllShips()
        },
        fetch = {
            delay(300)
            spacexApi.getShipsDetails()
        },
        safeFetchResult = { ship ->
            db.withTransaction {
                shipDao.deleteAllShipsData()
                shipDao.insertShipData(ship)
            }
        }
    )

    fun getCompanyInfo() = networkBoundResource(
        query = {
            infoDao.getAllInfo()
        },
        fetch = {
            delay(300)
            spacexApi.getCompanyInfo()
        },
        safeFetchResult = { info ->
            db.withTransaction {
                infoDao.deleteAllInfo()
                infoDao.insertInfo(info)
            }
        }
    )

}
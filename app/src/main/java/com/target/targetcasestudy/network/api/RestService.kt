package com.target.targetcasestudy.network.api

import com.target.targetcasestudy.data.DealListResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface RestService {
    //FETCH DEALS
    @GET(ApiConstants.DEALS)
    fun getDealsList(): Observable<DealListResponse>

}
package com.target.targetcasestudy.ui.deallist

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DealListItemModel( var id:Int=0,
                              var title: String="",
                              var aisle: String="",
                              var description: String="",
                              var imageUrl: String="",
                              var regularPriceDisplayString: String="",
                              var salePriceDisplayString: String="") : Parcelable
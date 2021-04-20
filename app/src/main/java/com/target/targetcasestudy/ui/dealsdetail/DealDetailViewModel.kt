package com.target.targetcasestudy.ui.dealsdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.target.targetcasestudy.ui.deallist.DealListItemModel

class DealDetailViewModel: ViewModel() {
    var dealListItemModel = MutableLiveData<DealListItemModel>()
}
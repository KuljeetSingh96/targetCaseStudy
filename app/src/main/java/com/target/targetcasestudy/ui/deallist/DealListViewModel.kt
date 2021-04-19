package com.target.targetcasestudy.ui.deallist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DealListViewModel: ViewModel() {
    var errorRetryVisibility: MutableLiveData<Int> = MutableLiveData()
    var dealListVisibility: MutableLiveData<Int> = MutableLiveData()
    var dealListData: MutableLiveData<List<DealListItemModel>> = MutableLiveData()
}
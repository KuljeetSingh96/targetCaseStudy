package com.target.targetcasestudy.ui.payment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PaymentDialogViewModel: ViewModel() {
    var creditCardText = MutableLiveData<String>()
    var buttonState=MutableLiveData<Boolean>()
}
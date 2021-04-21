package com.target.targetcasestudy.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.validateCreditCard
import com.target.targetcasestudy.databinding.DealListActivityBinding
import com.target.targetcasestudy.databinding.DialogPaymentBinding
import com.target.targetcasestudy.ui.deallist.DealListViewModel

/**
 * Dialog that displays a minimal credit card entry form.
 *
 * Your task here is to enable the "submit" button when the credit card number is valid and
 * disable the button when the credit card number is not valid.
 *
 * You do not need to input any of your actual credit card information. See `Validators.kt` for
 * info to help you get fake credit card numbers.
 *
 * You do not need to make any changes to the layout of this screen (though you are welcome to do
 * so if you wish).
 */
class PaymentDialogFragment : DialogFragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val viewModel = createPaymentViewModel()
    val binding = initializeBinding(container, viewModel)

    binding.cancel.setOnClickListener { dismiss() }
    binding.submit.setOnClickListener { dismiss() }
    viewModel.creditCardText.observe(this, Observer {
        viewModel.buttonState.value=validateCreditCard(it)
    })
    return binding.root
  }
  private fun createPaymentViewModel(): PaymentDialogViewModel {
    val viewModel= ViewModelProviders.of(this).get(PaymentDialogViewModel::class.java)
     viewModel.buttonState.value=false
    return viewModel
  }

  private fun initializeBinding(
    container: ViewGroup?,
    viewModel: PaymentDialogViewModel
  ): DialogPaymentBinding {
    val inflater = LayoutInflater.from(context)
    val binding: DialogPaymentBinding =
      DialogPaymentBinding.inflate(inflater, container, false)
    binding.lifecycleOwner = this
    binding.viewModel = viewModel
    return binding
  }
}
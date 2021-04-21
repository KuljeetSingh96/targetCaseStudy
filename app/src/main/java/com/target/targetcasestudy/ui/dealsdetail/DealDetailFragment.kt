package com.target.targetcasestudy.ui.dealsdetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

import com.target.targetcasestudy.databinding.DealDetailItemBinding
import com.target.targetcasestudy.ui.deallist.DealListItemModel


class DealDetailFragment : Fragment() {
    private lateinit var  listener: DealDetailFragmentInteractionListener
    companion object{
      private const val EXTRA_DEAL_LIST_ITEM_DETAIL="com.target.targetcasestudy.ui.dealsdetail.EXTRA_DEAL_LIST_ITEM_DETAIL"
      fun newInstance(dealListItemModel: DealListItemModel):DealDetailFragment{
        val dealDetailFragment=DealDetailFragment()
        val args=Bundle()
        args.putParcelable(EXTRA_DEAL_LIST_ITEM_DETAIL,dealListItemModel)
        dealDetailFragment.arguments=args
        return dealDetailFragment
      }
    }
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    val dealListItemModel=arguments?.getParcelable<DealListItemModel>(EXTRA_DEAL_LIST_ITEM_DETAIL)
    val viewModel = createDealListViewModel(dealListItemModel)
    val binding = initializeBinding(container, viewModel)
    return binding.root
  }

  private fun createDealListViewModel(dealListItemModel: DealListItemModel?): DealDetailViewModel {
    val dealDetailViewModel= ViewModelProviders.of(this).get(DealDetailViewModel::class.java)
    dealDetailViewModel.dealListItemModel.value=dealListItemModel
    return dealDetailViewModel
  }
  private fun initializeBinding(container: ViewGroup?, viewModel: DealDetailViewModel): DealDetailItemBinding
  {
    val inflater = LayoutInflater.from(context)
    val binding: DealDetailItemBinding =
      DealDetailItemBinding.inflate(inflater, container, false)
    binding.lifecycleOwner = this
    binding.viewModel = viewModel
    binding.eventHandler=this
    return binding
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    listener =
      context as DealDetailFragmentInteractionListener
  }
  fun onBackClicked() {
    listener.finishFragment()
  }
  interface DealDetailFragmentInteractionListener {
    fun finishFragment()
  }
}

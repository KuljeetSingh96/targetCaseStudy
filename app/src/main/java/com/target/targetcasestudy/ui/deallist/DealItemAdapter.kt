package com.target.targetcasestudy.ui.deallist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.StaticData
import com.target.targetcasestudy.databinding.DealListItemBinding

class DealItemAdapter : RecyclerView.Adapter<DealItemAdapter.ViewHolder>() {
  private val dealListViewModels = ArrayList<DealListItemModel>()

  fun updateDealListViewModels(dealListViewModels: List<DealListItemModel>) {
    this.dealListViewModels.clear()
    this.dealListViewModels.addAll(dealListViewModels)
    notifyDataSetChanged()
  }
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder.createViewHolder(parent)
  }

  override fun getItemCount(): Int {
    return dealListViewModels.size
  }

  override fun getItemId(position: Int): Long {
    return dealListViewModels[position].id.toLong()
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val repoListViewModel = dealListViewModels[position]
    holder.bind(repoListViewModel)

  }


  class ViewHolder(
    private val binding: DealListItemBinding
  )// Set in constructor since these do not change
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(dealListItemModel: DealListItemModel) {
      binding.viewModel = dealListItemModel
      binding.rootLayout.setOnClickListener {
      }
    }

    companion object {

      fun createViewHolder(
        parent: ViewGroup
      ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DealListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
      }
    }
  }
}
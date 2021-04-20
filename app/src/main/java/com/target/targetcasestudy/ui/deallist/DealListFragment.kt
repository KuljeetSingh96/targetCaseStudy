package com.target.targetcasestudy.ui.deallist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.target.targetcasestudy.R
import com.target.targetcasestudy.databinding.DealListActivityBinding
import com.target.targetcasestudy.network.repository.Repository
import com.target.targetcasestudy.network.schedulers.SchedulerProvider
import com.target.targetcasestudy.ui.dealsdetail.DealDetailFragment


class DealListFragment : Fragment() {
    lateinit var presenter: DealListPresenter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel = createDealListViewModel()
        val binding = initializeBinding(container, viewModel)
        setupPresenter(viewModel)
        setupRecyclerView(binding)
        presenter.getDealList()
        return binding.root
    }

    private fun createDealListViewModel(): DealListViewModel {
        return ViewModelProviders.of(this).get(DealListViewModel::class.java)
    }

    private fun initializeBinding(
        container: ViewGroup?,
        viewModel: DealListViewModel
    ): DealListActivityBinding {
        val inflater = LayoutInflater.from(context)
        var binding: DealListActivityBinding =
            DealListActivityBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.eventHandler = this
        return binding
    }

    private fun setupPresenter(viewModel: DealListViewModel) {
        presenter = DealListPresenter(viewModel, Repository.getRepository()!!, SchedulerProvider())
    }

    private fun setupRecyclerView(dealListActivityBinding: DealListActivityBinding) {
        val adapter = DealItemAdapter(this)
        dealListActivityBinding.recyclerView.layoutManager = LinearLayoutManager(context)
        dealListActivityBinding.recyclerView.adapter = adapter
        dealListActivityBinding.swipeToRefresh.setOnRefreshListener {
            presenter::getDealList
            dealListActivityBinding.swipeToRefresh.isRefreshing = false
        }
        presenter.dealListViewModel.dealListData.observe(this, Observer {
            adapter.updateDealListViewModels(it)
        })
    }

    fun onRetryClicked() {
        presenter.getDealList()
    }

    override fun onDestroy() {
        presenter.onDispose()
        super.onDestroy()
    }

     fun onDealsClicked(dealListItemModel: DealListItemModel) {
        fragmentManager!!.beginTransaction()
            .add(
                R.id.container,
                DealDetailFragment.newInstance(dealListItemModel)
            ).addToBackStack("detail")
            .commit()

    }
}

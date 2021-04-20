package com.target.targetcasestudy.ui.deallist

import android.view.View
import androidx.annotation.VisibleForTesting
import com.target.targetcasestudy.data.DealListResponse
import com.target.targetcasestudy.network.repository.Repository
import com.target.targetcasestudy.network.schedulers.SchedulerProvider
import io.reactivex.disposables.Disposable
import java.util.*

class DealListPresenter(
    val dealListViewModel: DealListViewModel,
    private val repository: Repository,
    private val schedulerProvider: SchedulerProvider
) {
    private var disposable: Disposable? = null
    fun getDealList() {
        loadListView()
        disposable = repository.getDealsList(
        )?.subscribeOn(getSchedulerProvider().io())
            ?.map { dealListResponse -> mapDealResponseToItems(dealListResponse) }
            ?.observeOn(getSchedulerProvider().ui())
            ?.subscribe(
                { dealListItemResponse -> onDealListSuccess(dealListItemResponse) },
                { _ -> onNetworkFailure() })
    }

    private fun mapDealResponseToItems(dealListResponse: DealListResponse): List<DealListItemModel> {
        val dealListItemModel: MutableList<DealListItemModel> =
            ArrayList<DealListItemModel>()

        for (products in dealListResponse.products) {
            var dealItemModel = DealListItemModel()
            dealItemModel.id = products.id
            dealItemModel.title = products.title
            dealItemModel.description = products.description
            dealItemModel.aisle = products.aisle
            dealItemModel.imageUrl = products.imageUrl
            dealItemModel.salePriceDisplayString =
                when (products.salePrice == null || products.salePrice?.displayString.isNullOrEmpty()) {
                    true -> "$ 0.00"
                    false -> products.salePrice?.displayString.toString()
                }
            dealItemModel.regularPriceDisplayString =
                when (products.regularPrice == null || products.regularPrice?.displayString.isNullOrEmpty()) {
                    true -> "$ 0.00"
                    false -> products.regularPrice?.displayString.toString()
                }
            dealListItemModel.add(dealItemModel)
        }
        return dealListItemModel
    }

    @VisibleForTesting
    internal fun onDealListSuccess(dealListItemViewModel: List<DealListItemModel>) {
        when {
            (dealListItemViewModel.isNotEmpty()) -> {
                dealListViewModel.dealListData.postValue(dealListItemViewModel)
                loadListView()
            }
            else -> loadRetryView()
        }
    }

    @VisibleForTesting
    internal fun onNetworkFailure() {
        loadRetryView()
    }

    @VisibleForTesting
    internal fun loadRetryView() {
        dealListViewModel.dealListVisibility.value = View.GONE
        dealListViewModel.errorRetryVisibility.value = View.VISIBLE
    }

    @VisibleForTesting
    internal fun loadListView() {
        dealListViewModel.dealListVisibility.value = View.VISIBLE
        dealListViewModel.errorRetryVisibility.value = View.GONE
    }

    @VisibleForTesting
    internal fun getSchedulerProvider(): SchedulerProvider {
        return schedulerProvider
    }

    fun onDispose() {
        if(disposable!=null) {
            disposable?.dispose()
        }
    }
}
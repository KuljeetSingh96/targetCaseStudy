package com.target.targetcasestudy.ui.deallist

import com.target.targetcasestudy.network.repository.Repository
import com.target.targetcasestudy.network.schedulers.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
@Module
class DealListModule(val viewModel: DealListViewModel) {
    private val repository: Repository = Repository.getRepository()!!
    private val schedulerProvider: SchedulerProvider = SchedulerProvider()

    @Provides
    @Singleton
    fun getPresenter(): DealListPresenter {
        return DealListPresenter(viewModel, repository,schedulerProvider)
    }
}
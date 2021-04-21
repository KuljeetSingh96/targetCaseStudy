package com.target.targetcasestudy.ui.deallist

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DealListModule::class])
interface DealListComponent {
fun inject(dealListFragment: DealListFragment)
}
package com.hftor.plyr

import Player.PlayerViewModel
import Player.Repo
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Created by hafthorg on 22/02/2019.
 */
object DependencyModule{
    val appModule = module{
        single { Repo() }
        viewModel{ PlayerViewModel(get()) }

    }
}
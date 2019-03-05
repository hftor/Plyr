package com.hftor.plyr

import Player.PlayerViewModel
import Player.Repo
import com.hftor.plyr.dataBase.AppDataBase
import com.hftor.plyr.repo.SongInfoRepository
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Created by hafthorg on 22/02/2019.
 */
object DependencyModule{
    val appModule = module{
        single { SongInfoRepository(get()) }
        single { Repo(get(), get()) }
        viewModel{ PlayerViewModel(get()) }

    }
}
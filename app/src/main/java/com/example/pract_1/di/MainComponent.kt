package com.example.pract_1.di

import com.example.pract_1.ui.BaseFragment
import dagger.Component
import dagger.Component.Builder
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class, NetworkModule::class, DbModule::class])
interface MainComponent {

    fun inject(fragment: BaseFragment)

    @Component.Builder
    interface Builder{
        fun build(): MainComponent
        fun dbModule(module: DbModule): Builder
    }
}
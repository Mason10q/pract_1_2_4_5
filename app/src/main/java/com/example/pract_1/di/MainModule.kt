package com.example.pract_1.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pract_1.Mapper
import com.example.pract_1.model.MainViewModel
import com.example.pract_1.ViewModelFactory
import com.example.pract_1.entities.Drug
import com.example.pract_1.entities.Pharmacy
import com.example.pract_1.entities.Profile
import com.example.pract_1.mappers.DrugMapper
import com.example.pract_1.mappers.PharmacyMapper
import com.example.pract_1.mappers.ProfileMapper
import com.example.pract_1.network.dtos.DrugDto
import com.example.pract_1.network.dtos.PharmacyDto
import com.example.pract_1.network.dtos.ProfileDto
import com.example.pract_1.tables.DrugTable
import com.example.pract_1.tables.PharmacyTable
import com.example.pract_1.tables.ProfileTable
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {

    @Binds
    fun bindViewModelFactory(impl: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindChooseNameViewModel(viewModel: MainViewModel): ViewModel


    @Binds
    fun bindDrugMapper(mapper: DrugMapper): Mapper<DrugDto, DrugTable, Drug>

    @Binds
    fun bindPharmacyMapper(mapper: PharmacyMapper): Mapper<PharmacyDto, PharmacyTable, Pharmacy>

    @Binds
    fun bindProfileMapper(mapper: ProfileMapper): Mapper<ProfileDto, ProfileTable, Profile>
}
package com.example.pract_1.model

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pract_1.entities.Drug
import com.example.pract_1.entities.Pharmacy
import com.example.pract_1.entities.Profile
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private val _profile = MutableLiveData<Profile>()
    val profile: LiveData<Profile> = _profile


    private val _pharmacyList = MutableLiveData<List<Pharmacy>>()
    val pharmacyList: LiveData<List<Pharmacy>> = _pharmacyList

    private val _drugList = MutableLiveData<List<Drug>>()
    val drugList: LiveData<List<Drug>> = _drugList


    fun getProfileView() =
        repository.getProfile()
            .subscribe({ profile ->
                _profile.postValue(profile)
            }, { err ->
                Log.d("viewModel", err.message.toString())
            })


    fun getPharmacyList() =
        repository.getPharmacies()
            .subscribe({ pharmacies ->
                Log.d("repo","insert ${pharmacies.size}")
                _pharmacyList.postValue(pharmacies)
            }, { err ->
                Log.d("viewModel", err.message.toString())
            })

    fun getDrugList() =
        repository.getDrugs()
            .subscribe({ drugs ->
                _drugList.postValue(drugs)
            }, { err ->
                Log.d("viewModel", err.message.toString())
            })

}
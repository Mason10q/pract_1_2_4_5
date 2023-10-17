package com.example.pract_1.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pract_1.Navigator
import com.example.pract_1.R
import com.example.pract_1.ViewModelFactory
import com.example.pract_1.databinding.FragmentNBinding
import com.example.pract_1.databinding.ProfileBinding
import com.example.pract_1.di.DaggerMainComponent
import com.example.pract_1.di.DbModule
import com.example.pract_1.entities.Profile
import com.example.pract_1.model.MainViewModel
import javax.inject.Inject

sealed class BaseFragment(
    private val number: Int,
    protected val id: Int?,
    private val nextFragment: BaseFragment?
) : Fragment() {

    private lateinit var navigator: Navigator

    @Inject lateinit var viewModelFactory: ViewModelFactory

    protected val viewModel by lazy { ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java] }

    private fun bindContainer(view: View){
        binding.container.removeAllViews()
        binding.container.addView(view)
    }

    private fun inject() = DaggerMainComponent
        .builder()
        .dbModule(DbModule(requireContext()))
        .build()
        .inject(this@BaseFragment)


    private val navigatorNew = object : Navigator {
        override fun navigateBack() { findNavController().navigateUp() }

        override fun navigateNext() {
            if (nextFragment != null) {
                findNavController().navigate(nextFragment.id!!)
            } else {
                Toast.makeText(requireContext(), "Дальше некуда(", Toast.LENGTH_LONG).show()
            }
        }
    }


    private val navigatorOld = object : Navigator {
        override fun navigateBack() { requireActivity().supportFragmentManager.popBackStack() }

        override fun navigateNext() {
            if (nextFragment != null) {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.host_fragment, nextFragment)
                    .addToBackStack(getString(R.string.fragment_n, number))
                    .commit()
            } else {
                Toast.makeText(requireContext(), "Дальше некуда(", Toast.LENGTH_LONG).show()
            }
        }

    }


    private val binding by lazy { FragmentNBinding.inflate(layoutInflater) }

    private fun bindViews() {
        with(binding) {
            fragmentIdText.text = getString(R.string.fragment_n, number)
            navDownBtn.setOnClickListener { navigator.navigateBack() }
            navUpBtn.setOnClickListener { navigator.navigateNext() }
        }
    }

    private fun createListView(list: List<String>): ListView = ListView(context).apply {
        val adapter = ArrayAdapter(
            context,
            android.R.layout.simple_list_item_1,
            list
        )

        this.adapter = adapter
    }

    private fun createProfile(profile: Profile) = ProfileBinding.inflate(layoutInflater).apply {
        name.text = getString(R.string.name, profile.name)
        status.text = getString(R.string.status, profile.status)
    }.root

    private fun prepareObservers(){
        viewModel.profile.observe(viewLifecycleOwner){ profile ->
            bindContainer(createProfile(profile))
        }

        viewModel.drugList.observe(viewLifecycleOwner){ drugs ->
            bindContainer(createListView(drugs.map { it.name }))
        }

        viewModel.pharmacyList.observe(viewLifecycleOwner){ pharmacies ->
            bindContainer(createListView(pharmacies.map { it.name }))
        }
    }

    open fun getData(){}

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        prepareObservers()
        getData()
        navigator = navigatorOld
        bindViews()

        return binding.root
    }


    class FirstFragment : BaseFragment(1, R.id.firstFragment, SecondFragment()){
        override fun getData() {
            viewModel.getProfileView()
        }
    }
    class SecondFragment : BaseFragment(2, R.id.secondFragment, ThirdFragment()){
        override fun getData() {
            viewModel.getPharmacyList()
        }
    }
    class ThirdFragment : BaseFragment(3, R.id.thirdFragment, null){
        override fun getData() {
            viewModel.getDrugList()
        }
    }
}
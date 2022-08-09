package com.example.adtajstumag.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adtajstumag.adapters.ItemAdapter
import com.example.adtajstumag.databinding.FragmentInfoBinding
import kotlinx.coroutines.launch

class InfoFragment : Fragment() {

    private var binding: FragmentInfoBinding? = null

    private val viewModel: InfoViewModel by viewModels()

    private var itemAdapter: ItemAdapter = ItemAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getInfo2()
        listeners()
        observes()
        init()

    }

    private fun observes() {

        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.infoState.collect {
                    binding!!.swipeRefresh.isRefreshing = it.isLoading
                    viewModel.collect(itemAdapter, binding!!.swipeRefresh)
                }
            }
        }
    }

    private fun listeners() {
        binding!!.swipeRefresh.setOnRefreshListener {

            viewModel.run {
                getInfo2()
                collect(itemAdapter, binding!!.swipeRefresh)
            }
        }
        itemAdapter.itemClick = {
            findNavController().navigate(InfoFragmentDirections.actionInfoFragmentToMoreInfoFragment(
                it.titleKA,
                it.publishDate,
                it.descriptionKA,
                it.cover))
        }
    }

    private fun init() {
        binding?.recyclerview?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = itemAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
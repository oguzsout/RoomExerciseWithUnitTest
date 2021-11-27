package com.oguzdogdu.roomexercise.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.oguzdogdu.roomexercise.R
import com.oguzdogdu.roomexercise.adapter.ListAdapter
import com.oguzdogdu.roomexercise.databinding.FragmentListBinding
import com.oguzdogdu.roomexercise.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


class ListFragment : Fragment(R.layout.fragment_list) {

    private lateinit var mUserViewModel: UserViewModel

    private var _binding: FragmentListBinding? = null

    private val binding get() = _binding!!

    private val adapter = ListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        observeData()

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
    }

    private fun setupRv() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeData() {
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        mUserViewModel.readAllData.observe(viewLifecycleOwner, {
            adapter.users = it
        })
    }
}
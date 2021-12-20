package com.oguzdogdu.roomexercise.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.oguzdogdu.roomexercise.R
import com.oguzdogdu.roomexercise.adapter.UserAdapter
import com.oguzdogdu.roomexercise.databinding.FragmentListBinding
import com.oguzdogdu.roomexercise.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private val userViewModel: UserViewModel by viewModels()

    private val adapter = UserAdapter()

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
        userViewModel.userList.observe(viewLifecycleOwner, {
            adapter.users = it
        })
    }
}
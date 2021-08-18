package com.oguzdogdu.roomexercise.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.oguzdogdu.roomexercise.R
import com.oguzdogdu.roomexercise.model.User
import com.oguzdogdu.roomexercise.viewmodel.UserViewModel
import com.oguzdogdu.roomexercise.databinding.FragmentAddBinding


class AddFragment : Fragment(R.layout.fragment_add) {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.button.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase(){
        val firstName = binding.addFirstName.text.toString()
        val lastName = binding.addLastName.text.toString()
        val age = binding.editTextNumber.text

        if (inputCheck(firstName, lastName, age)){
            val user = User(0,firstName,lastName,Integer.parseInt(age.toString()))
            mUserViewModel.addUser(user)
            Toast.makeText(context,"Successfully added!!!",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(context,"Please fill out all fields",Toast.LENGTH_SHORT).show()
        }
    }
    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean{
        return  !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty() )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
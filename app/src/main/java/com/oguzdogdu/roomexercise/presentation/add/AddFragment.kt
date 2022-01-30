package com.oguzdogdu.roomexercise.presentation.add

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.oguzdogdu.roomexercise.R
import com.oguzdogdu.roomexercise.databinding.FragmentAddBinding
import com.oguzdogdu.roomexercise.presentation.base.BaseFragment
import com.oguzdogdu.roomexercise.util.Status
import com.oguzdogdu.roomexercise.presentation.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFragment : BaseFragment<FragmentAddBinding>(FragmentAddBinding::inflate) {

    private val userViewModel: UserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        insertDataToDatabase()
        binding.btnAdd.setOnClickListener {
            userViewModel.makeUser(
                binding.addFirstName.text.toString(),
                binding.addLastName.text.toString(),
                binding.editTextAge.text.toString()
            )
        }
    }
    private fun insertDataToDatabase() {
        userViewModel.insertUsersMessage.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    Toast.makeText(requireActivity(), "Success", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_addFragment_to_listFragment)
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message ?: "Error", Toast.LENGTH_LONG)
                        .show()
                }

                Status.LOADING -> {
                }
            }
        })
        /*
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

         */
    }
}
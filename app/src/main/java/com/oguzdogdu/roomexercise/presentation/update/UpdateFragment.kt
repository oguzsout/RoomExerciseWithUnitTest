package com.oguzdogdu.roomexercise.presentation.update

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.oguzdogdu.roomexercise.R
import com.oguzdogdu.roomexercise.databinding.FragmentUpdateBinding
import com.oguzdogdu.roomexercise.domain.model.Users
import com.oguzdogdu.roomexercise.presentation.base.BaseFragment
import com.oguzdogdu.roomexercise.presentation.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateFragment : BaseFragment<FragmentUpdateBinding>(FragmentUpdateBinding::inflate) {

    private val args by navArgs<UpdateFragmentArgs>()

    private val userViewModel: UserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.updateFirstName.setText(args.currentUser.firstName)
        binding.updateLastName.setText(args.currentUser.lastName)
        binding.updateAge.setText(args.currentUser.age.toString())

        binding.btnUpdate.setOnClickListener {
            update()
        }

        binding.btnDelete.setOnClickListener {
            deleteUser()
        }
    }
    private fun update() {
        val firstName = binding.updateFirstName.text.toString()
        val lastName = binding.updateLastName.text.toString()
        val age = binding.updateAge.text.toString().toInt()
        val updatedUser = Users(firstName, lastName, age,args.currentUser.id)
        userViewModel.updateUser(updatedUser)
        Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }
    /*  val firstName = binding.updateFirstName.text.toString()
      val lastName = binding.updateLastName.text.toString()
      val age = Integer.parseInt(binding.updateAge.text.toString())

      if (inputCheck(firstName,lastName,binding.updateAge.text)){
          val updatedUser = User(args.currentUser.id,firstName,lastName, age)
          mUserViewModel.updateUser(updatedUser)
          Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
          findNavController().navigate(R.id.action_updateFragment_to_listFragment)
      } else {
          Toast.makeText(context, "Please fill out all fields", Toast.LENGTH_SHORT).show()
      }
     */
    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            userViewModel.deleteUser(args.currentUser)
            Toast.makeText(
                context,
                "Successfully removed: ${args.currentUser.firstName}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")
        builder.create().show()
    }
}


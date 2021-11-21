package com.oguzdogdu.roomexercise.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.oguzdogdu.roomexercise.R
import com.oguzdogdu.roomexercise.databinding.FragmentUpdateBinding
import com.oguzdogdu.roomexercise.util.Status
import com.oguzdogdu.roomexercise.viewmodel.UserViewModel

class UpdateFragment : Fragment(R.layout.fragment_update) {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel : UserViewModel

    private var _binding: FragmentUpdateBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        /*
        binding.updateFirstName.setText(args.currentUser.firstName)
        binding.updateLastName.setText(args.currentUser.lastName)
        binding.updateAge.setText(args.currentUser.age.toString())
        */
        binding.btnUpdate.setOnClickListener {
            mUserViewModel.makeUser(
                binding.updateFirstName.text.toString(),
                binding.updateLastName.text.toString(),
                binding.updateAge.text.toString()
            )
            updateItem()
        }
        binding.btnDelete.setOnClickListener {
            deleteUser()
        }
    }

    private fun updateItem() {
        mUserViewModel.insertUserMessage.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    Toast.makeText(requireActivity(), "Success", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_updateFragment_to_listFragment)
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message ?: "Error", Toast.LENGTH_LONG)
                        .show()
                }

                Status.LOADING -> {
                }
            }
        })
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
    }
    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


package com.oguzdogdu.roomexercise.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.oguzdogdu.roomexercise.R
import com.oguzdogdu.roomexercise.databinding.FragmentUpdateBinding
import com.oguzdogdu.roomexercise.model.User
import com.oguzdogdu.roomexercise.viewmodel.UserViewModel

class UpdateFragment : Fragment(R.layout.fragment_update) {
    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel : UserViewModel

    private var _binding: FragmentUpdateBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.updateFirstName.setText(args.currentUser.firstName)
        binding.updateLastName.setText(args.currentUser.lastName)
        binding.updateAge.setText(args.currentUser.age.toString())


        binding.button.setOnClickListener {
            updateItem()
        }
        }
    private fun updateItem(){
        val firstName = binding.updateFirstName.text.toString()
        val lastName = binding.updateLastName.text.toString()
        val age = Integer.parseInt(binding.updateAge.text.toString())

        if (inputCheck(firstName,lastName,binding.updateAge.text)){
            val updatedUser = User(args.currentUser.id,firstName,lastName, age)
             mUserViewModel.updateUser(updatedUser)
                Toast.makeText(requireContext(),"Updated Successfully!",Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
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
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun deleteUser(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(context,"Successfully removed: ${args.currentUser.firstName}",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){_, _ ->}
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")
        builder.create().show()
    }
}


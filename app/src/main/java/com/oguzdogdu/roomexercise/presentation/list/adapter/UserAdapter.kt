package com.oguzdogdu.roomexercise.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.oguzdogdu.roomexercise.databinding.CustomRowBinding
import com.oguzdogdu.roomexercise.presentation.list.ListFragmentDirections
import com.oguzdogdu.roomexercise.domain.model.Users

class UserAdapter : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: CustomRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(users: Users) {
            binding.apply {
                firstNameTxt.text = users.firstName
                lastNameTxt.text = users.lastName
                ageTxt.text = users.age.toString()
            }
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<Users>() {
        override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean {
            return oldItem.id == newItem.id
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var users: List<Users>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CustomRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val userList = users[position]
        holder.bind(userList)
        holder.binding.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(userList)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = users.size
}
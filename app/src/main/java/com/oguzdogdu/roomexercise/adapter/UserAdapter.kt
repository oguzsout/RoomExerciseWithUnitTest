package com.oguzdogdu.roomexercise.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.oguzdogdu.roomexercise.databinding.CustomRowBinding
import com.oguzdogdu.roomexercise.fragments.ListFragmentDirections
import com.oguzdogdu.roomexercise.model.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: CustomRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.apply {
                firstNameTxt.text = user.firstName
                lastNameTxt.text = user.lastName
                ageTxt.text = user.age.toString()
            }
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var users: List<User>
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
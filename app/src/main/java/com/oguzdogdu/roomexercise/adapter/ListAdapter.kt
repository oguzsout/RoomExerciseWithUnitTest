package com.oguzdogdu.roomexercise.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.oguzdogdu.roomexercise.model.User
import com.oguzdogdu.roomexercise.databinding.CustomRowBinding
import com.oguzdogdu.roomexercise.fragments.ListFragmentDirections

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: CustomRowBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }
    }
    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var users: List<User>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CustomRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = users[position]
        holder.binding.idTxt.text = currentItem.id.toString()
        holder.binding.firstNameTxt.text = currentItem.firstName
        holder.binding.lastNameTxt.text = currentItem.lastName
        holder.binding.ageTxt.text = currentItem.age.toString()

        holder.binding.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }
    override fun getItemCount() = users.size
}
package com.oguzdogdu.roomexercise.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.oguzdogdu.roomexercise.model.User
import com.oguzdogdu.roomexercise.databinding.CustomRowBinding
import com.oguzdogdu.roomexercise.fragments.ListFragmentDirections

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()
    class MyViewHolder(val binding: CustomRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CustomRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.binding.idTxt.text = currentItem.id.toString()
        holder.binding.firstNameTxt.text = currentItem.firstName
        holder.binding.lastNameTxt.text = currentItem.lastName
        holder.binding.ageTxt.text = currentItem.age.toString()

        holder.binding.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }
    fun setData(user:List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}
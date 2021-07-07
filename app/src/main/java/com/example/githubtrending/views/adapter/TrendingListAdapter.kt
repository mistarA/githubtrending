package com.example.practice.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubtrending.databinding.AuthorRowBinding
import com.example.githubtrending.model.Author

class TrendingListAdapter(private val authorList: ArrayList<Author>) :
    RecyclerView.Adapter<TrendingListAdapter.AuthorViewHolder>() {

    class AuthorViewHolder(val binding: AuthorRowBinding) : RecyclerView.ViewHolder(binding.root)

    fun updateAuthorList(newAuthorList: List<Author>) {
        authorList.clear()
        authorList.addAll(newAuthorList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {

        return AuthorViewHolder(
            AuthorRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        holder.binding.authorName.text = authorList[position].name
        holder.binding.authorDescription.text = authorList[position].description
        Glide.with(holder.binding.root).load(authorList[position].avatar).circleCrop()
            .into(holder.binding.avatar)
    }

    override fun getItemCount(): Int {
        return authorList.size
    }
}
package com.jay.storymirror.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.jay.storymirror.R
import com.jay.storymirror.model.StoryInfo
import com.jay.storymirror.ui.storyread.StoryReadActivity
import kotlin.math.abs

class StoryAdapter(private val context: Context, private val problemSolverList: List<StoryInfo>) :
    RecyclerView.Adapter<StoryAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.text_view_title)
        var author: TextView = view.findViewById(R.id.text_view_author)
        var reads: TextView = view.findViewById(R.id.text_view_reads)
        var votes: TextView = view.findViewById(R.id.text_view_votes)
        var description: TextView = view.findViewById(R.id.text_view_description)
        var chipGroup: ChipGroup = view.findViewById(R.id.chip_group)
        var thumbnail: ImageView = view.findViewById(R.id.image_view_thumbnail) as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_story, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return problemSolverList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val problemSolver = problemSolverList[position]
        holder.title.text = problemSolver.title
        holder.author.text = "by ${problemSolver.author}"
        holder.reads.text = numToString(problemSolver.reads)
        holder.votes.text = numToString(problemSolver.votes)
        holder.description.text = problemSolver.description
        for (tag in problemSolver.tags) {
            val chip = Chip(holder.chipGroup.context)
            chip.text = tag
            holder.chipGroup.addView(chip)
        }
        Glide.with(context)
            .load(problemSolver.thumbnail)
            .thumbnail(0.5F)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop().into(holder.thumbnail)

        holder.itemView.setOnClickListener {
            Toast.makeText(context, problemSolverList[position].title, Toast.LENGTH_SHORT).show()
            val intent = Intent(context, StoryReadActivity::class.java)
            intent.putExtra("storyId", problemSolverList[position].storyId)
            context.startActivity(intent)
        }
    }

    private fun numToString(number: Long): String {
        return when {
            abs(number / 1000000) > 1 -> (number / 1000000).toString() + "m"
            abs(number / 1000) > 1 -> (number / 1000).toString() + "k"
            else -> number.toString()
        }
    }
}
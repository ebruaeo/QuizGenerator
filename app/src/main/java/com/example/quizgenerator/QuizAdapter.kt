package com.example.quizgenerator

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizgenerator.databinding.RecyclerViewRowBinding

class QuizAdapter(val quizList: List<Quiz>) :
    RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {
    class QuizViewHolder(val binding: RecyclerViewRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val binding =
            RecyclerViewRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuizViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return quizList.size
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.binding.textViewRecyclerView.text = quizList[position].name
        holder.itemView.setOnClickListener {
            holder.itemView.setBackgroundResource(R.drawable.bg_selected)
            holder.itemView.postDelayed(
                { holder.itemView.setBackgroundResource(R.drawable.bg_unselected) },
                200
            )

            val intent = Intent(holder.itemView.context, QuizActivity::class.java)
            intent.putExtra("position", position)
            //  intent.putExtra("quiz", quizList[position])
            holder.itemView.context.startActivity(intent)
        }
    }
}
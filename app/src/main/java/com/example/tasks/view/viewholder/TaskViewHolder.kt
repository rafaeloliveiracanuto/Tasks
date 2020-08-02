package com.example.tasks.view.viewholder

import android.app.AlertDialog
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks.R
import com.example.tasks.service.listener.TaskListener
import com.example.tasks.service.model.TaskModel

class TaskViewHolder(itemView: View, val listener: TaskListener) :
    RecyclerView.ViewHolder(itemView) {

    private var mTextDescription: TextView = itemView.findViewById(R.id.text_description)
    private var mTextPriority: TextView = itemView.findViewById(R.id.text_priority)
    private var mTextDueDate: TextView = itemView.findViewById(R.id.text_due_date)
    private var mImageTask: ImageView = itemView.findViewById(R.id.image_task)

    fun bindData(task: TaskModel) {

        this.mTextDescription.text = ""
        this.mTextPriority.text = ""
        this.mTextDueDate.text = ""

        if (task.complete) {
            mTextDescription.setTextColor(Color.GRAY)
            mImageTask.setImageResource(R.drawable.ic_done)
        } else {
            mTextDescription.setTextColor(Color.BLACK)
            mImageTask.setImageResource(R.drawable.ic_todo)
        }

        // mTextDescription.setOnClickListener { listener.onListClick(task.id) }
        // mImageTask.setOnClickListener { }

        mTextDescription.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle(R.string.remocao_de_tarefa)
                .setMessage(R.string.remover_tarefa)
                .setPositiveButton(R.string.sim) { dialog, which ->
                    // listener.onDeleteClick(task.id)
                }
                .setNeutralButton(R.string.cancelar, null)
                .show()
            true
        }

    }

}
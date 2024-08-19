package com.example.todo_app.ui.views.fragments.main_fragment

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_app.constants.Constant
import com.example.todo_app.DataBase.model.Task
import com.example.todo_app.R
import com.example.todo_app.databinding.ItemBuilderBinding

class TasksAdapter(private var tasks: MutableList<Task>? = null) :
    RecyclerView.Adapter<TasksAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBuilderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int =
        tasks?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task: Task? = tasks?.get(position)
        holder.binding.TvTaskName.text = task?.tittle
        holder.binding.TvTimer.text = "${task?.time}"
        if (task?.isDone == true) {
            holder.binding.TvTaskName.setTextColor(Color.parseColor(Constant.doneColor))
            holder.binding.checkButton.icon = null
            holder.binding.checkButton.text = "Done"
            holder.binding.verticalDivider.setBackgroundColor(Color.parseColor(Constant.doneColor))
            holder.binding.TvTimer.setTextColor(Color.parseColor(Constant.doneColor))

        } else {
            holder.binding.checkButton.setIconResource(R.drawable.ic_check)
            holder.binding.checkButton.text = null
            holder.binding.TvTaskName.setTextColor(Color.parseColor(Constant.notDoneColor))
            holder.binding.checkButton.setBackgroundColor(Color.parseColor(Constant.notDoneColor))
            holder.binding.verticalDivider.setBackgroundColor(Color.parseColor(Constant.notDoneColor))
            holder.binding.TvTimer.setTextColor(Color.BLACK)

        }
        if (onCheckButtonClickListener != null) {
            holder.binding.checkButton.setOnClickListener {
                onCheckButtonClickListener?.onItemClick(position, task!!)
            }
        }
        if (onItemViewClickListener != null) {
            holder.itemView.setOnClickListener {
                onItemViewClickListener?.onItemClick(position, task!!)
            }
        }


    }

    fun submitNewList(tasks: MutableList<Task>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }

    var onCheckButtonClickListener: OnClick? = null
     var onItemViewClickListener: OnClick? = null

    fun submitNewItem(task: Task) {
        tasks?.add(task)
        notifyItemInserted(tasks!!.size)
    }

    fun interface OnClick {
        fun onItemClick(position: Int, task: Task)
    }


    class ViewHolder(val binding: ItemBuilderBinding) : RecyclerView.ViewHolder(binding.root)
}

package com.example.todo_app.ui.views.fragments.main_fragment

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_app.constants.Constant
import com.example.todo_app.DataBase.model.Task
import com.example.todo_app.R
import com.example.todo_app.databinding.ItemBuilderBinding
import com.zerobranch.layout.SwipeLayout

class TasksAdapter(private var tasks: MutableList<Task>? = null) :
    RecyclerView.Adapter<TasksAdapter.ViewHolder>() {
    var lastOpenSwipeLayout: SwipeLayout? = null

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
            doneTask(holder)

        } else {

            undoneTask(holder)
        }
        if (onCheckButtonClickListener != null) {
            holder.binding.checkButton.setOnClickListener {
                onCheckButtonClickListener?.onItemClick(position, task!!)
            }
        }
        if (onItemViewClickListener != null) {
            holder.binding.dragItem.setOnClickListener {
                onItemViewClickListener?.onItemClick(position, task!!)
            }
        }

        holder.binding.swiper.setOnActionsListener(object : SwipeLayout.SwipeActionsListener {
            override fun onOpen(direction: Int, isContinuous: Boolean) {
                if (lastOpenSwipeLayout != null && lastOpenSwipeLayout != holder.binding.swiper) {
                    lastOpenSwipeLayout?.close(true);
                }
                lastOpenSwipeLayout = holder.binding.swiper
            }

            override fun onClose() {
            }
        })
        if (onDeleteItemClickListener != null) {
            holder.binding.rightView.setOnClickListener {
                onDeleteItemClickListener?.onItemClick(position, task!!)
            }
        }
    }

    fun submitNewList(tasks: MutableList<Task>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }

    var onCheckButtonClickListener: OnClick? = null
    var onItemViewClickListener: OnClick? = null
    var onDeleteItemClickListener: OnClick? = null

    fun submitNewItem(task: Task) {
        tasks?.add(task)
        notifyItemInserted(tasks!!.size)
    }

    fun interface OnClick {
        fun onItemClick(position: Int, task: Task)
    }


    class ViewHolder(val binding: ItemBuilderBinding) : RecyclerView.ViewHolder(binding.root)

    private fun doneTask(holder: ViewHolder ) {
        holder.binding.TvTaskName.setTextColor(Color.parseColor(Constant.doneColor))
        holder.binding.checkButton.icon = null
        holder.binding.checkButton.text = holder.itemView.context.resources.getString(R.string.done)
        holder.binding.checkButton.setBackgroundColor(holder.itemView.context.getColor(R.color.pureWhite))
        holder.binding.checkButton.elevation = 0F

        holder.binding.checkButton.setTextColor(Color.parseColor(Constant.doneColor))



        holder.binding.verticalDivider.setBackgroundColor(Color.parseColor(Constant.doneColor))
        holder.binding.TvTimer.setTextColor(Color.parseColor(Constant.doneColor))
    }

    private fun undoneTask(holder: ViewHolder) {
        holder.binding.checkButton.elevation = 0F
        holder.binding.checkButton.setIconResource(R.drawable.ic_check)
        holder.binding.checkButton.text = null
        holder.binding.TvTaskName.setTextColor(Color.parseColor(Constant.notDoneColor))
        holder.binding.checkButton.setBackgroundColor(Color.parseColor(Constant.notDoneColor))
        holder.binding.verticalDivider.setBackgroundColor(Color.parseColor(Constant.notDoneColor))
        holder.binding.TvTimer.setTextColor(Color.BLACK)
    }

}

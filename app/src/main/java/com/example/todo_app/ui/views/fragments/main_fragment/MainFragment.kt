package com.example.todo_app.ui.views.fragments.main_fragment

import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Ignore
import com.example.todo_app.DataBase.AppDataBase
import com.example.todo_app.DataBase.model.Task
import com.example.todo_app.Excitation.ignoreTime
import com.example.todo_app.Excitation.setDate
import com.example.todo_app.R
import com.example.todo_app.base.BaseFragment
import com.example.todo_app.constants.Strings
import com.example.todo_app.databinding.FragmentMainBinding
import com.example.todo_app.databinding.ItemBuilderBinding
import com.example.todo_app.ui.views.EditTask.EditTask
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import java.util.Calendar

class MainFragment : BaseFragment<FragmentMainBinding>() {
    val adapter = TasksAdapter()

    var selectedDate :Calendar? = Calendar.getInstance().apply {
        ignoreTime()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding.RVTasks.adapter = adapter
        setButtonClickListener()
       setItemViewClickListener()
        binding.RVTasks.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                adapter.lastOpenSwipeLayout?.close(true)
                adapter.lastOpenSwipeLayout = null
            }
        })

        binding.calenderView.setDateSelected(
            CalendarDay.today(),
            true
        )
        adapter.onDeleteItemClickListener = TasksAdapter.OnClick{position, task ->
context?.let {
  val dialog =  MaterialAlertDialogBuilder(it,R.style.MaterialAlertDialog_Rounded).setTitle("delete")
        .setMessage("Are You sure for delete This Task ?")

        .setNegativeButton("Cansel",DialogInterface.OnClickListener{dialog, which ->
          dialog.dismiss()
            adapter.lastOpenSwipeLayout!!.close(true)
            adapter.lastOpenSwipeLayout = null
      })
      .setPositiveButton("Accept",DialogInterface.OnClickListener { dialog, i ->
          AppDataBase.getInstance().tasksDao().deleteTask(task)
          adapter.notifyItemRemoved(position)
          dialog.dismiss()
      })

    dialog.show()
}


        }
        binding.calenderView.setOnDateChangedListener(OnDateSelectedListener { widget, date, selected ->

            selectedDate?.setDate(date.year, date.month, date.day)
            getTasksFromDataBase()
        })
    }

    override fun onResume() {
        super.onResume()
        getTasksFromDataBase()

    }

    override fun onDestroy() {
        super.onDestroy()
        selectedDate = null
    }

    fun getTasksFromDataBase() {
        val tasks = AppDataBase.getInstance().tasksDao().gitTaskByDate(selectedDate!!.timeInMillis)
        adapter.submitNewList(tasks.toMutableList())
    }

    override fun getLayoutId(): Int = R.layout.fragment_main
private fun setButtonClickListener(){
    adapter.onCheckButtonClickListener =
        TasksAdapter.OnClick { position, task ->
            adapter.notifyItemChanged(position)
            task.isDone = task.isDone?.not()
            AppDataBase.getInstance().tasksDao().updateTask(task)

        }
}
    private fun setItemViewClickListener(){
        adapter.onItemViewClickListener =
            TasksAdapter.OnClick { position, task ->
navigationToEditScreen(task)
            }

    }
    private fun navigationToEditScreen(task: Task){
        val intent = Intent(context,EditTask()::class.java)
        intent.putExtra(Strings.task,task)
startActivity(intent)
    }
}


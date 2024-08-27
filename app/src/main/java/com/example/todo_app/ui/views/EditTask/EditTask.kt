package com.example.todo_app.ui.views.EditTask

import android.app.TimePickerDialog
import android.os.Bundle
import com.example.todo_app.DataBase.AppDataBase
import com.example.todo_app.DataBase.model.Task
import com.example.todo_app.Excitation.formatTimeText
import com.example.todo_app.Excitation.ignoreDate
import com.example.todo_app.Excitation.setTime
import com.example.todo_app.R
import com.example.todo_app.base.BaseActivity
import com.example.todo_app.constants.Strings
import com.example.todo_app.databinding.ActivityEditTaskBinding
import java.util.Calendar

class EditTask : BaseActivity<ActivityEditTaskBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val time = Calendar.getInstance().apply {
            ignoreDate()
        }
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        val task = intent.getParcelableExtra<Task>(Strings.task)

        binding.EtTime.setOnClickListener {

            val dialog = TimePickerDialog(
                this,
                { timePicker, i, i2 ->
                    time.setTime(i, i2)
                    binding.EtTime.setText(time.formatTimeText())

                },
                time.get(Calendar.HOUR_OF_DAY),
                time.get(Calendar.MINUTE),
                false

            )
            dialog.show()

        }
        setOldDataToFields(task)
        binding.BtSaveChange.setOnClickListener {
            setChanges(task!!, time)
            finish()
        }
        binding.backButton.setOnClickListener{
            finish()
        }
    }

    private fun setOldDataToFields(task: Task?) {
        binding.EtTime.hint = task!!.time
        binding.EtTittle.setText(task.tittle)
        binding.EtDetails.setText(task.description)
    }
    private fun setChanges(task: Task, time: Calendar) {
        task.tittle =if ( binding.EtTittle.text.isNullOrBlank())task.tittle else(binding.EtTittle.text.toString())
        task.description = if (binding.EtDetails.text
                .isNullOrBlank()
        ) task.description else (binding.EtDetails.text.toString())
        task.time = if(binding.EtTime.text.isNullOrBlank()) task.time
        else binding.EtTime.text.toString()
    AppDataBase.getInstance().tasksDao().updateTask(task)



    }
    override fun getLayoutId(): Int = R.layout.activity_edit_task


}

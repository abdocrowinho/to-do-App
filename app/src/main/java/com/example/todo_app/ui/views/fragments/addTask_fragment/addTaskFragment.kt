package com.example.todo_app.ui.views.fragments.addTask_fragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.example.todo_app.DataBase.AppDataBase
import com.example.todo_app.DataBase.Dao.TaskDao
import com.example.todo_app.DataBase.model.Task
import com.example.todo_app.Excitation.formatDateText
import com.example.todo_app.Excitation.formatTimeText
import com.example.todo_app.Excitation.ignoreDate
import com.example.todo_app.Excitation.ignoreTime
import com.example.todo_app.Excitation.setDate
import com.example.todo_app.Excitation.setTime
import com.example.todo_app.R
import com.example.todo_app.databinding.FragmentAddTaskBinding
import com.example.todo_app.databinding.FragmentAddTaskBindingImpl
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.time.Year
import java.util.Calendar

class addTaskFragment : BottomSheetDialogFragment() {
    lateinit var binding: FragmentAddTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.TvSelectDate.setOnClickListener {

            showDatePicker()
        }
        binding.TvHintSelectTime.setOnClickListener {
            showTimePicker()
        }
        binding.BtDone.setOnClickListener {
            createTask()
        }
    }


    private fun createTask() {
        if (formIsValid()) {
            AppDataBase.getInstance().tasksDao().addNewTask(
                Task(

                    tittle = binding.EtTittle.text.toString(),
                    description = binding.EtDescription.text.toString(),

                    time = time.formatTimeText(),
                    dateTime = date.timeInMillis



                )
            )
            setTaskAddedListener?.taskAddedListener()

            val mydialog =  MaterialAlertDialogBuilder(requireContext(),R.style.MaterialAlertDialog_Rounded).setTitle("delete")
                .setMessage("Task Added Successful")
                .setNegativeButton("Accept", DialogInterface.OnClickListener { dialog, i ->
                    dialog.dismiss()
                    dismiss()


                })

            mydialog.show()

        }
    }

    private fun formIsValid(): Boolean {
        var isValid = true
        if (binding.EtTittle.text.isNullOrBlank()
        ) {
            binding.tilTittle.error = getString(R.string.pleas_enter_tittle)
            isValid = false
        } else {
            binding.tilTittle.error = null
        }
        if (binding.EtDescription.text.isNullOrBlank()
        ) {
            binding.tilDescription.error = getString(R.string.pleas_enter_description)
            isValid = false
        } else {
            binding.tilDescription.error = null
        }
        if (binding.selectedDate.text == null
        ) {
            binding.tilSelectedDate.error = getString(R.string.pleas_enter_date)
            isValid = false
        } else {
            binding.tilDescription.error = null
        }
        if (binding.selectedTime.text == null
        ) {
            binding.tilSelectItem.error = getString(R.string.pleas_enter_time)
            isValid = false
        } else {
            binding.tilSelectItem.error = null
        }
        return isValid
    }

    val date = Calendar.getInstance().apply {
        ignoreTime()
    }
    val time = Calendar.getInstance().apply {
        ignoreDate()
    }

    private fun showDatePicker() {
        val dialog = DatePickerDialog(
            requireContext(),
            { datePicker, year, month, day ->
                date.setDate(year, month, day)
                binding.selectedDate.text = date.formatDateText()
            },
            date.get(Calendar.YEAR),
            date.get(Calendar.MONTH),
            date.get(Calendar.DAY_OF_WEEK)

        )
        dialog.show()


    }

    private fun showTimePicker() {
        val dialog = TimePickerDialog(
            requireContext(), { timePicker, hour, minute ->
                time.setTime(hour, minute)
                binding.selectedTime.text = time.formatTimeText()
            },

            time.get(Calendar.HOUR_OF_DAY),
            time.get(Calendar.MINUTE),
            false
        )
        dialog.show()
    }
    var setTaskAddedListener : TaskAddedListener?=null
    fun interface TaskAddedListener{
        fun  taskAddedListener()
    }
}
package com.example.taskmanager.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentTaskBinding
import com.example.taskmanager.model.Task
import com.example.taskmanager.ui.home.HomeFragment

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        val inputText = binding.outlinedTextField.editText?.text.toString()
        binding.outlinedTextField.editText?.doOnTextChanged { inputText, _, _, _ ->
        }
        val inputTextDesc = binding.title.editText?.text.toString()
        binding.outlinedTextField.editText?.doOnTextChanged { inputText, _, _, _ ->
        }

        val task = arguments?.getSerializable(HomeFragment.TASK_DATA) as Task?
        task?.let {
            etTitle.setText(it.title)
            etDesc.setText(it.desc)
            btnSave.text = (getString(R.string.update))
        }

        btnSave.setOnClickListener {
            if (task == null) {
                save()
            } else {
                App.db.taskDao().update(
                    task.copy(
                        title = etTitle.text.toString(),
                        desc = etDesc.text.toString()))
            }
            findNavController().navigateUp()
        }
    }

    private fun save() {
        val data = Task(
            title = binding.etTitle.text.toString(),
            desc = binding.etDesc.text.toString()
        )
        App.db.taskDao().insert(data)
    }
}
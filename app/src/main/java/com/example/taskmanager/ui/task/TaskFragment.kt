package com.example.taskmanager.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.model.Task
import com.example.taskmanager.databinding.FragmentTaskBinding

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        task = arguments?.getSerializable(TASK_KEY) as Task?
        if (task == null) {
            onSave()
        } else {
            onUpdate()
        }
    }

    private fun onUpdate() = with(binding) {
        etTitle.setText(task?.title)
        etDesc.setText(task?.desc)
        btnSave.text = (getString(R.string.update))
        btnSave.setOnClickListener {
            val data = task?.copy(
                title = binding.etTitle.text.toString(),
                desc = binding.etDesc.text.toString()
            )
            App.db.taskDao().update(data!!)
            findNavController().navigateUp()
        }
    }

    private fun onSave() {
        binding.btnSave.setOnClickListener {
            if (binding.etTitle.text.toString().isNotEmpty()) {
                val data = Task(
                    title = binding.etTitle.text.toString(),
                    desc = binding.etDesc.text.toString()
                )
                App.db.taskDao().insert(data)
                findNavController().navigateUp()
            } else {
                Toast.makeText(context, "title can't be empty", Toast.LENGTH_LONG).show()
                binding.etTitle.error = "null"
                return@setOnClickListener
            }
        }
    }

    companion object {
        val TASK_KEY = "task.key"
    }
}
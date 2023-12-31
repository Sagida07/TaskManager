package com.example.taskmanager.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.taskmanager.databinding.FragmentNotificationsBinding
import com.example.taskmanager.model.Cinema
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            val cinema = Cinema(
                name = binding.etName.text.toString(),
                author = binding.etAuthor.text.toString()
            )

            Firebase.firestore.collection(FirebaseAuth.getInstance().currentUser?.uid.toString())
                .add(cinema)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "Данные сохранены", Toast.LENGTH_SHORT)
                        .show()
                    binding.etAuthor.text?.clear()
                    binding.etName.text?.clear()
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.taskmanager1.ui.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.taskmanager1.R
import com.example.taskmanager1.data.local.Pref
import com.example.taskmanager1.databinding.FragmentProfileBinding
import com.example.taskmanager1.utils.loadImage

private lateinit var binding: FragmentProfileBinding

class ProfileFragment : Fragment() {

    private val pref by lazy {
        Pref(requireContext())
    }

    private val pic =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK && it.data != null) {
                val uri: Uri? = it.data?.data
                pref.setImage(uri.toString())
                binding.ivProfile.loadImage(uri.toString())
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etName.setText(pref.getName())
        binding.btnNameSave.setOnClickListener {
            pref.saveName(binding.etName.text.toString())
        }
        binding.ivProfile.loadImage(pref.getImage())
        binding.ivProfile.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            pic.launch(intent)
        }
    }
}
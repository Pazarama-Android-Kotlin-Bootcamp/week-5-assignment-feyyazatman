package com.merttoptas.retrofittutorial.ui.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.merttoptas.retrofittutorial.data.model.DataState
import com.merttoptas.retrofittutorial.data.model.Users
import com.merttoptas.retrofittutorial.databinding.FragmentUserBinding
import com.merttoptas.retrofittutorial.ui.loadingprogress.LoadingProgressBar
import com.merttoptas.retrofittutorial.ui.users.adapter.UsersAdapter
import com.merttoptas.retrofittutorial.ui.users.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment() {

    private lateinit var loadingProgressBar: LoadingProgressBar
    private lateinit var binding : FragmentUserBinding
    private val viewModel by viewModels<UsersViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingProgressBar = LoadingProgressBar(requireContext())

        viewModel.userLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    loadingProgressBar.hide()
                    it.data.let {
                        binding.rvUserList.adapter = UsersAdapter().apply {
                            submitList(it)
                        }
                    }
                }
                is DataState.Error -> {
                    loadingProgressBar.hide()
                    Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG).show()
                }
                is DataState.Loading -> {
                    loadingProgressBar.show()
                }
            }
        }
    }


}
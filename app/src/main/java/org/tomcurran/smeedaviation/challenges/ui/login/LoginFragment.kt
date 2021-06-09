package org.tomcurran.smeedaviation.challenges.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import org.tomcurran.smeedaviation.challenges.R
import org.tomcurran.smeedaviation.challenges.databinding.LoginFragmentBinding
import org.tomcurran.smeedaviation.challenges.util.EventObserver

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()

    private val startActivityForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { activityResult ->
            viewModel.onActivityResult(activityResult)
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = LoginFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.startActivityForResult.observe(viewLifecycleOwner, EventObserver { intent ->
            try {
                startActivityForResult.launch(intent)
            } catch (e: Exception) {
                // intentionally left blank - onActivityResult will handle the failure
            }
        })

        viewModel.navigateToMain.observe(viewLifecycleOwner, EventObserver {
            binding.root.findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        })

        return binding.root
    }
}
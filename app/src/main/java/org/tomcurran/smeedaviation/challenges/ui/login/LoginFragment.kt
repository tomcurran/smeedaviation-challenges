package org.tomcurran.smeedaviation.challenges.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import org.tomcurran.smeedaviation.challenges.R
import org.tomcurran.smeedaviation.challenges.databinding.LoginFragmentBinding
import org.tomcurran.smeedaviation.challenges.util.EventObserver

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = LoginFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.startActivityForResult.observe(viewLifecycleOwner, EventObserver {
            startActivityForResult(it.intent, it.requestCode)
        })

        viewModel.navigateToMain.observe(viewLifecycleOwner, EventObserver {
            binding.root.findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        })

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.onActivityResult(requestCode, resultCode, data)
    }
}
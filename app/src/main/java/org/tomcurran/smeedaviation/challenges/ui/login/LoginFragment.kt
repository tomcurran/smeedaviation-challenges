package org.tomcurran.smeedaviation.challenges.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import org.tomcurran.smeedaviation.challenges.R
import org.tomcurran.smeedaviation.challenges.databinding.LoginFragmentBinding
import org.tomcurran.smeedaviation.challenges.util.EventObserver

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()

        private const val LOGIN_REQUEST_CODE = 0
    }

    private val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = LoginFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.launchLoginIntent.observe(viewLifecycleOwner, EventObserver { intent ->
            startActivityForResult(intent, LOGIN_REQUEST_CODE)
        })

        viewModel.navigateToMain.observe(viewLifecycleOwner, EventObserver {
            binding.root.findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        })

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == LOGIN_REQUEST_CODE) {
            viewModel.processLoginResponse(resultCode, data)
        }
    }
}
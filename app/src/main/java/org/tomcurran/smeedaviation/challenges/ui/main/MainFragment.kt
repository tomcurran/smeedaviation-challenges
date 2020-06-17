package org.tomcurran.smeedaviation.challenges.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import net.openid.appauth.*
import net.openid.appauth.connectivity.DefaultConnectionBuilder
import org.tomcurran.smeedaviation.challenges.R
import org.tomcurran.smeedaviation.challenges.databinding.MainFragmentBinding
import org.tomcurran.smeedaviation.challenges.util.EventObserver

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()

        private const val AUTH_REQUEST_CODE = 0
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.launchIntent.observe(viewLifecycleOwner, EventObserver { intent ->
            startActivityForResult(intent, AUTH_REQUEST_CODE)
        })

        binding.button.setOnClickListener {
            viewModel.auth()
        }

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == AUTH_REQUEST_CODE) {
            viewModel.processAuth(resultCode, data)
        }
    }
}
package com.ruslan.huseynov.foodninja.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.ruslan.huseynov.foodninja.R
import com.ruslan.huseynov.foodninja.databinding.FragmentLoginBinding
import com.ruslan.huseynov.foodninja.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        binding = FragmentLoginBinding.inflate(inflater,container,false)

        auth = Firebase.auth
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth.currentUser?.let { // İstifadəçi əvvəlcə giriş edibsə

            findNavController(binding.root).navigate(R.id.actionLoginToHome)
        }

        binding.btnLogin.setOnClickListener {

            // İstifadəçinin email və şifrəsini almaq
            val email = binding.etEmailLogin.text.toString()
            val password = binding.etPassLogin.text.toString()
            viewModel.login(email,password,it)
        }

        // İstifadəçinin hesabı yoxdursa
        binding.tvCreateAccount.setOnClickListener {

            findNavController(it).navigate(R.id.actionLoginToCreate)
        }

        // İstifadəçi şifrəni unudubsa
        binding.tvForgotPass.setOnClickListener {

            val email = binding.etEmailLogin.text.toString()
            viewModel.resetPassword(email,it)
        }
    }
}
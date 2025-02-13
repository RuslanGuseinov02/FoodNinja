package com.ruslan.huseynov.foodninja.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.ruslan.huseynov.foodninja.databinding.FragmentSignUpBinding
import com.ruslan.huseynov.foodninja.ui.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View{
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        auth = Firebase.auth
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCreate.setOnClickListener {

            // İstifadəçinin email və şifrəsini almaq
            val email = binding.etEmailCreate.text.toString().trim()
            val password = binding.etPassCreate.text.toString().trim()
            val confirmPassword = binding.etConfirmPass.text.toString().trim()

            if(password == confirmPassword){
                viewModel.signUp(email,password,it)
            }else{
                Snackbar.make(binding.root,"Passwords are not matching",2000).show()
            }
        }
    }
}
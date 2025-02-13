package com.ruslan.huseynov.foodninja.ui.view

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.ruslan.huseynov.foodninja.R
import com.ruslan.huseynov.foodninja.databinding.FragmentProfileBinding
import com.ruslan.huseynov.foodninja.ui.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db : SharedPreferences
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        auth = Firebase.auth
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = requireContext().getSharedPreferences("mode",MODE_PRIVATE)// Dark temanı yaddaşda saxlamaq üçün
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth.currentUser?.let {
            binding.tvEmail.text = it.email  // Giriş etmiş istifadəçinin email-in göstərmək üçün
        }

        binding.btnDelete.setOnClickListener {

            val alert = AlertDialog.Builder(requireContext())
            alert.setTitle("DELETE ACCOUNT!")
            alert.setMessage("ARE YOU SURE YOU WANT TO DELETE YOUR ACCOUNT?")

            alert.setPositiveButton("Yes"){ _, _ ->

                viewModel.deleteAccount(it)
            }
            alert.setNegativeButton("No"){ which, _ ->

                which.dismiss()
            }
            alert.show()
        }

        binding.btnSignOut.setOnClickListener{

            val alert = AlertDialog.Builder(requireContext())

            alert.setTitle("Sign Out")
            alert.setMessage("Are you sure you want to sign out?")
            alert.setPositiveButton("Yes"){ _, _ ->

                auth.signOut() // Hesabdan çıxış etmək üçün
                Navigation.findNavController(it).navigate(R.id.actionProfileToLogin)
            }

            alert.setNegativeButton("No"){ which, _ ->

                which.dismiss()
            }
            alert.show()
        }

        binding.btnReset.setOnClickListener {

            val alert = AlertDialog.Builder(requireContext())
            alert.setTitle("Reset Password")
            alert.setMessage("Are you sure you want to reset your password?")
            alert.setPositiveButton("Yes"){ _, _ ->

                viewModel.changePassword(auth.currentUser!!.email!!,it)
            }

            alert.setNegativeButton("No"){ which, _ ->

                which.dismiss()
            }
            alert.show()
        }

        val isNightMode = db.getBoolean("night",false)

        binding.switchNight.isChecked = isNightMode
        binding.switchNight.setOnCheckedChangeListener { _, isChecked ->

            val editor = db.edit()
            editor.putBoolean("night",isChecked)
            editor.apply()

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}
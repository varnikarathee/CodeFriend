package com.example.CodeFriend.ui.sign.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.CodeFriend.R
import com.example.CodeFriend.helpers.MyValidation
import com.example.CodeFriend.ui.main.MainActivity
import com.example.CodeFriend.ui.sign.ViewModelSignUser
import com.example.CodeFriend.utils.Status

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
//    private var _binding: LoginFragment? = null
//    private val binding get() = _binding!!

    private  val viewModel by  viewModels<ViewModelSignUser>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        _binding = LoginFragment.inflate(inflater, container, false)
//        val view = binding.ro

        login_btn_LogIn.setOnClickListener {
            val email: String = inputTextLayoutEmail.editText!!.text.toString()
            val password: String = inputTextLayoutPassword.editText!!.text.toString()



            if (MyValidation.isValidEmail(requireContext(),inputTextLayoutEmail)
                && MyValidation.validatePass(requireContext(),inputTextLayoutPassword)) {

                viewModel.signInWithEmailAndPassword(email, password)
                viewModel.successToLoginLiveData.observe(viewLifecycleOwner){
                    when(it.status){
                        Status.LOADING -> {
                            progress.visibility=View.VISIBLE
                        }
                        Status.SUCCESS -> {
                            progress.visibility=View.GONE
                            activity?.startActivity(Intent(activity, MainActivity::class.java))
                            activity?.finish()
                        }
                        Status.ERROR -> {
                            Toast.makeText(activity, ""+it.message, Toast.LENGTH_SHORT).show()
                        }
                    }

                }

            }



        }




        login_btn_Register.setOnClickListener {
            findNavController().navigate(
                R.id.action_loginFragment_to_registerFragment
            )
        }

        login_forget.setOnClickListener {
            findNavController().navigate(
                R.id.action_loginFragment_to_resetPasswordFragment
            )
        }


    }


}
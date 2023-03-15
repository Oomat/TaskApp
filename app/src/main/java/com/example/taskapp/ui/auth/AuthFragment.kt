package com.example.taskapp.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.taskapp.Extenssions.showToast
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentAuthBinding
import com.example.taskapp.databinding.FragmentOnBoardPageBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import java.util.concurrent.TimeUnit


class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    private var auth = FirebaseAuth.getInstance()
    private var correctCode:String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        initViews()
        initListener()
        return binding.root

    }

    private fun initListener() {
        binding.btnSend.setOnClickListener{
            sendPhone()
        }
        binding.btnConfirm.setOnClickListener{
            sendCode()
        }
    }

    private fun sendCode() {
        val credential = correctCode?.let { PhoneAuthProvider.getCredential(correctCode!!, binding.etCode.text.toString())}
        signInWithPhoneAuthCredential(credential)

    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential?) {
        if (credential != null) {
            auth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        findNavController().navigate(R.id.navigation_home)

                        val user = task.result?.user
                    } else {
                        // Sign in failed, display a message and update the UI
                        Log.w("ololo", "signInWithCredential:failure", task.exception)
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            Log.w("ololo", "signInWithPhoneAuthCredential: ", )
                            showToast(task.exception.toString())

                        }
                    }
                }
        }
    }

    private fun sendPhone() {
        auth.setLanguageCode("RU")
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(binding.etPhone.toString())       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(object : OnVerificationStateChangedCallbacks(){
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {

                }

                override fun onVerificationFailed(exeption: FirebaseException) {
                    Log.e("ololo", "onVerificationFailed: "+exeption.message.toString() )
                showToast(exeption.message.toString())
                }

                override fun onCodeSent(code: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(code, p1)
                    correctCode = code

                    binding.btnSend.isVisible = false
                    binding.etPhone.isVisible = false

                    binding.etCode.isVisible = true
                    binding.btnConfirm.isVisible = true
                }

            })          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun initViews() {

    }
}
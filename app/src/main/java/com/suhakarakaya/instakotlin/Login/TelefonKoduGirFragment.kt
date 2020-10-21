package com.suhakarakaya.instakotlin.Login

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.suhakarakaya.instakotlin.R
import com.suhakarakaya.instakotlin.utils.EventbusDataEvents
import kotlinx.android.synthetic.main.fragment_telefon_codu_gir.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.concurrent.TimeUnit

class TelefonKoduGirFragment : Fragment() {

    var gelenTelNo = ""
    lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    var verificationID = ""
    var gelenKod = ""
    lateinit var progressBar: ProgressBar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_telefon_codu_gir, container, false)

        view.txt_kullaniciTelNo.setText(gelenTelNo)
        progressBar = view.progressBarRegister

        setupCallBack()

        view.btn_telKodIleri.setOnClickListener {


            if (gelenKod.equals(view.edt_onayKodu.text.toString())) {

                EventBus.getDefault().postSticky(
                    EventbusDataEvents.KayitBilgileriniGonder(
                        gelenTelNo,
                        null,
                        verificationID,
                        gelenKod,
                        false
                    )
                )

                activity?.let {
                    var transaction = it!!.supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fl_register, KayitFragment())
                    transaction.addToBackStack("kayitFragmenti eklendi")
                    transaction.commit()
                }


            } else {
                Toast.makeText(activity, "hatalı", Toast.LENGTH_SHORT).show()
            }

        }
        activity?.let {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                gelenTelNo, // Phone number to verify
                60, // Timeout duration
                TimeUnit.SECONDS, // Unit of timeout
                it!!, // Activity (for callback binding)
                callbacks
            ) // OnVerificationStateChangedCallbacks

        }

        return view
    }

    private fun setupCallBack() {

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                if (!credential.smsCode.isNullOrEmpty()) {
                    gelenKod = credential.smsCode!!
                    progressBar.visibility = View.GONE
                }


            }

            override fun onVerificationFailed(e: FirebaseException) {
                progressBar.visibility = View.GONE
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                progressBar.visibility = View.VISIBLE
                verificationID = verificationId!!

            }
        }

    }

    @Subscribe(sticky = true)
    internal fun onTelefonNoEvent(kayitBilgileri: EventbusDataEvents.KayitBilgileriniGonder) {
        gelenTelNo = kayitBilgileri.telNo!!

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        EventBus.getDefault().register(this)
    }

    override fun onDetach() {
        EventBus.getDefault().unregister(this)
        super.onDetach()
    }


}
package com.suhakarakaya.instakotlin.Login

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.suhakarakaya.instakotlin.R
import com.suhakarakaya.instakotlin.utils.EventbusDataEvents
import kotlinx.android.synthetic.main.fragment_kayit.*
import kotlinx.android.synthetic.main.fragment_kayit.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


class KayitFragment : Fragment() {

    var telNo = ""
    var verificaitonID = ""
    var gelenMail = ""
    var gelenKod = ""
    var emailIleKayitIslemi = true

    lateinit var mAuth: FirebaseAuth
    lateinit var mRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_kayit, container, false)

        mAuth = FirebaseAuth.getInstance()
        mRef = FirebaseDatabase.getInstance().reference

        view.edt_adSoyad.addTextChangedListener(watcher)
        view.edt_kullaniciAdi.addTextChangedListener(watcher)
        view.edt_password.addTextChangedListener(watcher)


        view.btn_giris_kayit.setOnClickListener {
            if (emailIleKayitIslemi) {


                var sifre = view.edt_password.text.toString()

                mAuth.createUserWithEmailAndPassword(gelenMail,sifre)
                    .addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                        override fun onComplete(p0: Task<AuthResult>) {
                            if (p0!!.isSuccessful) {
                                Toast.makeText(activity, "Oturum Açıldı", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(
                                    activity,
                                    "Oturum Açılamadı" + p0!!.exception,
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }

                    })

            } else {


            }

        }


        return view
    }


    var watcher: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            if (s!!.length > 5) {

                if (edt_adSoyad.text.toString().length > 5 && edt_kullaniciAdi.text.toString().length > 5 && edt_password.text.toString().length > 5) {
                    btn_giris_kayit.isEnabled = true
                    btn_giris_kayit.setTextColor(ContextCompat.getColor(activity!!, R.color.blue))
                    btn_giris_kayit.setBackgroundResource(R.drawable.regitser_button_blue)
                }

            } else {
                btn_giris_kayit.isEnabled = false
                btn_giris_kayit.setTextColor(ContextCompat.getColor(activity!!, R.color.white))
                btn_giris_kayit.setBackgroundResource(R.drawable.regitser_button_white)
            }
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

    }


    @Subscribe(sticky = true)
    internal fun onKayitEvent(kayitBilgileri: EventbusDataEvents.KayitBilgileriniGonder) {
        if (kayitBilgileri.emailKayidi) {
            emailIleKayitIslemi = true
            gelenMail = kayitBilgileri.email!!
            Toast.makeText(activity, "Gelen Mail: " + gelenMail, Toast.LENGTH_SHORT).show()

        } else {
            emailIleKayitIslemi = false
            telNo = kayitBilgileri.telNo!!
            verificaitonID = kayitBilgileri.verficationId!!
            gelenKod = kayitBilgileri.code!!

            Toast.makeText(
                activity,
                "Gelen Kod: " + gelenKod + "VerificationID: " + verificaitonID,
                Toast.LENGTH_SHORT
            ).show()

        }


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
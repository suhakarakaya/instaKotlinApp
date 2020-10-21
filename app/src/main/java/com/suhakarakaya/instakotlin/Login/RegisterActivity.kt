package com.suhakarakaya.instakotlin.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.suhakarakaya.instakotlin.R
import com.suhakarakaya.instakotlin.utils.EventbusDataEvents
import kotlinx.android.synthetic.main.activity_register.*
import org.greenrobot.eventbus.EventBus

class RegisterActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener {

    lateinit var manager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        manager = supportFragmentManager
        manager.addOnBackStackChangedListener(this)
        init()
    }

    private fun init() {

        txt_eposta.setOnClickListener {
            view_telefon.visibility = View.GONE
            view_eposta.visibility = View.VISIBLE
            txt_telefon.setTextColor(resources.getColor(R.color.gray))
            txt_eposta.setTextColor(resources.getColor(R.color.black))
            edt_girisYontemi.setText("")
            edt_girisYontemi.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            edt_girisYontemi.setHint("E-Posta")
            btn_Ileri.isEnabled = false
            btn_Ileri.setTextColor(
                ContextCompat.getColor(
                    this@RegisterActivity,
                    R.color.blue
                )
            )
            btn_Ileri.setBackgroundResource(
                R.drawable.regitser_button_blue
            )
        }


        txt_telefon.setOnClickListener {
            view_telefon.visibility = View.VISIBLE
            view_eposta.visibility = View.GONE
            txt_eposta.setTextColor(resources.getColor(R.color.gray))
            txt_telefon.setTextColor(resources.getColor(R.color.black))
            edt_girisYontemi.setText("")
            edt_girisYontemi.inputType = InputType.TYPE_CLASS_NUMBER
            edt_girisYontemi.setHint("Telefon")
            btn_Ileri.isEnabled = false
            btn_Ileri.setTextColor(
                ContextCompat.getColor(
                    this@RegisterActivity,
                    R.color.blue
                )
            )
            btn_Ileri.setBackgroundResource(
                R.drawable.regitser_button_blue
            )
        }


        edt_girisYontemi.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (s!!.length >= 10) {
                    btn_Ileri.isEnabled = true
                    btn_Ileri.setTextColor(
                        ContextCompat.getColor(
                            this@RegisterActivity,
                            R.color.white
                        )
                    )
                    btn_Ileri.setBackgroundResource(R.drawable.regitser_button_white)
                } else {

                    btn_Ileri.isEnabled = false
                    btn_Ileri.setTextColor(
                        ContextCompat.getColor(
                            this@RegisterActivity,
                            R.color.blue
                        )
                    )
                    btn_Ileri.setBackgroundResource(
                        R.drawable.regitser_button_blue
                    )
                }

            }

            override fun afterTextChanged(s: Editable?) {
            }
        })


        btn_Ileri.setOnClickListener {

            if (edt_girisYontemi.hint.toString().equals("Telefon")) {
                if (isValidTelefon(edt_girisYontemi.text.toString())) {
                    cl_register.visibility = View.GONE
                    fl_register.visibility = View.VISIBLE
                    var transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fl_register, TelefonKoduGirFragment())
                    transaction.addToBackStack("telefonKoduGirFragmentEklendi")
                    transaction.commit()
                    EventBus.getDefault()
                        .postSticky(
                            EventbusDataEvents.KayitBilgileriniGonder(
                                edt_girisYontemi.text.toString(),
                                null,
                                null,
                                null, false
                            )
                        )
                } else {
                    Toast.makeText(this, "Lütfen telefon numarası giriniz", Toast.LENGTH_SHORT)
                        .show()
                }


            } else {

                if (isValidEmail(edt_girisYontemi.text.toString())) {
                    cl_register.visibility = View.GONE
                    fl_register.visibility = View.VISIBLE
                    var transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fl_register, KayitFragment())
                    transaction.addToBackStack("emailKoduGirFragmentEklendi")
                    transaction.commit()
                    EventBus.getDefault()
                        .postSticky(
                            EventbusDataEvents.KayitBilgileriniGonder(
                                null,
                                edt_girisYontemi.text.toString(),
                                null,
                                null, true
                            )
                        )
                } else {
                    Toast.makeText(this, "Lütfen telefon numarası giriniz", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }

    }

    override fun onBackStackChanged() {

        val fragmentSayisi = manager.backStackEntryCount

        if (fragmentSayisi == 0) {
            cl_register.visibility = View.VISIBLE
        }
    }


    fun isValidEmail(kontrolEdilecekMail: String): Boolean {

        if (kontrolEdilecekMail == null) {
            return false
        }
        return android.util.Patterns.EMAIL_ADDRESS.matcher(kontrolEdilecekMail).matches()

    }


    fun isValidTelefon(kontrolEdilecekTelefon: String): Boolean {

        if (kontrolEdilecekTelefon == null || kontrolEdilecekTelefon.length > 14) {
            return false
        }
        return android.util.Patterns.PHONE.matcher(kontrolEdilecekTelefon).matches()

    }


}
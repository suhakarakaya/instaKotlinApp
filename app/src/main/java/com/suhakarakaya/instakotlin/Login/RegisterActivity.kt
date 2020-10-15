package com.suhakarakaya.instakotlin.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import com.suhakarakaya.instakotlin.R
import com.suhakarakaya.instakotlin.utils.EventbusDataEvents
import kotlinx.android.synthetic.main.activity_register.*
import org.greenrobot.eventbus.EventBus

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

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
            btn_ileri.isEnabled = false
            btn_ileri.setTextColor(
                ContextCompat.getColor(
                    this@RegisterActivity,
                    R.color.blue
                )
            )
            btn_ileri.setBackgroundResource(
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
            btn_ileri.isEnabled = false
            btn_ileri.setTextColor(
                ContextCompat.getColor(
                    this@RegisterActivity,
                    R.color.blue
                )
            )
            btn_ileri.setBackgroundResource(
                R.drawable.regitser_button_blue
            )
        }


        edt_girisYontemi.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (start + before + count >= 10) {
                    btn_ileri.isEnabled = true
                    btn_ileri.setTextColor(
                        ContextCompat.getColor(
                            this@RegisterActivity,
                            R.color.white
                        )
                    )
                    btn_ileri.setBackgroundResource(R.drawable.regitser_button_white)
                } else {

                    btn_ileri.isEnabled = false
                    btn_ileri.setTextColor(
                        ContextCompat.getColor(
                            this@RegisterActivity,
                            R.color.blue
                        )
                    )
                    btn_ileri.setBackgroundResource(
                        R.drawable.regitser_button_blue
                    )
                }

            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        btn_ileri.setOnClickListener {

            if (edt_girisYontemi.hint.toString().equals("Telefon")) {
                cl_register.visibility = View.GONE
                fl_register.visibility = View.VISIBLE
                var transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fl_register, TelefonKoduGirFragment())
                transaction.addToBackStack("telefonKoduGirFragmentEklendi")
                transaction.commit()
                EventBus.getDefault()
                    .postSticky(EventbusDataEvents.TelefonNoGonder(edt_girisYontemi.text.toString()))

            } else {
                cl_register.visibility = View.GONE
                fl_register.visibility = View.VISIBLE
                var transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fl_register, EmailKoduGirFragment())
                transaction.addToBackStack("emailKoduGirFragmentEklendi")
                transaction.commit()
                EventBus.getDefault()
                    .postSticky(EventbusDataEvents.EmailGonder(edt_girisYontemi.text.toString()))
            }

        }

    }

    override fun onBackPressed() {
        cl_register.visibility = View.VISIBLE
        super.onBackPressed()
    }


}
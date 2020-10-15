package com.suhakarakaya.instakotlin.Login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suhakarakaya.instakotlin.R
import com.suhakarakaya.instakotlin.utils.EventbusDataEvents
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class TelefonKoduGirFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_telefon_codu_gir, container, false)
        return view
    }

    @Subscribe(sticky = true)
    internal fun onTelefonNoEvent(telefonNumarası: EventbusDataEvents.TelefonNoGonder) {
        var gelenTelNo = telefonNumarası.telNo

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
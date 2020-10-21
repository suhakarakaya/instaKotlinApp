package com.suhakarakaya.instakotlin.utils

class EventbusDataEvents {

    internal class KayitBilgileriniGonder(
        var telNo: String?,
        var email: String?,
        var verficationId: String?,
        var code: String?,
        var emailKayidi:Boolean
    )

}
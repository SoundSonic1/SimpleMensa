package com.soundsonic.simplemensa.util

import android.nfc.Tag
import android.nfc.tech.IsoDep
import com.soundsonic.simplemensa.data.cardcheck.card.desfire.DesfireProtocol
import com.soundsonic.simplemensa.data.cardcheck.cardreader.Readers
import com.soundsonic.simplemensa.data.cardcheck.cardreader.ValueData

object NfcReader {
    fun getValueData(tag: Tag): ValueData? {
        var data: ValueData? = null
        val tech = IsoDep.get(tag)
        try {
            tech.connect()
        } catch (e: Exception) {
            e.printStackTrace()
            return data
        }
        try {
            val desfireProtocol = DesfireProtocol(tech)
            data = Readers.getInstance().readCard(desfireProtocol)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            tech.close()
        }
        return data
    }
}

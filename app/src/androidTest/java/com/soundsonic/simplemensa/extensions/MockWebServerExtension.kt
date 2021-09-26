package com.soundsonic.simplemensa.extensions

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets

fun MockWebServer.enqueueResponse(fileName: String, responseCode: Int = 200) {
    val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
    val source = inputStream.source().buffer()
    enqueue(
        MockResponse()
            .setBody(source.readString(StandardCharsets.UTF_8))
            .setResponseCode(responseCode)
    )
}

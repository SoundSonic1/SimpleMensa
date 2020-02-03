package com.soundsonic.simplemensa.data.cardcheck.card.desfire

/**
 * Created by Jakob Wenzel on 16.11.13.
 */
class DesfireException : Exception {
    constructor(message: String?) : super(message)
    constructor(cause: Throwable?) : super(cause)
}

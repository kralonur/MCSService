package com.example.mcsservice.util

import timber.log.Timber
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

private const val MAX_PASS_LENGTH = 32

object AES {

    private fun hexStringToByteArray(s: String): ByteArray {
        val len = s.length
        val data = ByteArray(len / 2)
        var i = 0
        while (i < len) {
            data[i / 2] = ((Character.digit(s[i], 16) shl 4)
                    + Character.digit(s[i + 1], 16)).toByte()
            i += 2
        }
        return data
    }

    private fun getIvParameterSpec(): IvParameterSpec {
        val iv = byteArrayOf(
            '0'.toByte(),
            '0'.toByte(),
            '0'.toByte(),
            '0'.toByte(),
            '0'.toByte(),
            '0'.toByte(),
            '0'.toByte(),
            '0'.toByte(),
            '0'.toByte(),
            '0'.toByte(),
            '0'.toByte(),
            '0'.toByte(),
            '0'.toByte(),
            '0'.toByte(),
            '0'.toByte(),
            '0'.toByte()
        )
        return IvParameterSpec(iv)
    }

    private fun getSecretKeySpec(key: ByteArray) = SecretKeySpec(key, "AES")

    private fun getCipher(
        secretKey: SecretKeySpec,
        ivParameterSpec: IvParameterSpec
    ): Cipher {
        val cipher: Cipher = Cipher.getInstance("AES/CBC/NOPADDING")
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec)
        return cipher
    }

    private fun getPaddedKey(secret: String): String {
        val length = secret.length

        return when {
            length > MAX_PASS_LENGTH -> {
                Timber.e("keys length more than maximum key length")
                ""
            }
            length < MAX_PASS_LENGTH -> {
                var returnKey = secret
                while (returnKey.length != MAX_PASS_LENGTH) {
                    returnKey += "0"
                }

                returnKey
            }
            else -> secret
        }
    }

    fun decrypt(strToDecrypt: String, secret: String): String {
        val paddedKey = getPaddedKey(secret)
        if (paddedKey.isEmpty()) return paddedKey

        try {
            val ivParameterSpec = getIvParameterSpec()
            val key = paddedKey.toByteArray(charset("UTF-8"))
            val secretKey = getSecretKeySpec(key)

            // CBC mode decryption enabled
            val cipher: Cipher = getCipher(secretKey, ivParameterSpec)
            return String(cipher.doFinal(hexStringToByteArray(strToDecrypt)))
        } catch (e: Throwable) {
            Timber.e(e)
        }
        return ""
    }

}
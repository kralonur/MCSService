package com.example.mcsservice.ui.section_detail.validator

import com.example.mcsservice.util.AES
import org.junit.Test
import java.util.regex.Pattern

class HtmlValidatorTest {
    @Test
    fun addition_isCorrect() {
        val encryptedDesc = AES.decrypt(
            "9f6b82f7d612dcb8f27543fbb570772ef19aa4d3c3c3d4a5a8e39290284ba6b0fd5ee0a25388e610c43044380289406fee3f1406c8cd6f41b0544669b937f2a47140bc2909602f54f8b1eda8092c6677de6556d7a44d47ba698ab0a78907f178e6ce2aef542bfffb19d97309a9e02ad22d1553c60e2b2af57eaf9c14e4bbc066f0240e75bd8c542c807f740f3624cdd63b3dca27825d37b75a3e424ebb6df4080c6841f1f031f4b2ce1edc71ac84d3d5f7698be20b3aeac0a0622aa9ef9771f62677bdbac41c2706e2a0e38f30f5b14efe1fd56244c681095ef90f18b38afbad1ffaec9f9802fa7f902a944c66c9cf7b8902877fb31e480d35a6988f29c32f1d047dadc5dad28c85f4b64542562f4072b997d9b3007b4e168adccc2059c36b8336a72e32d6b45e87bdaf1e95c423e085f05382cee2fb6b8b8d37cce62534e4782745b6822e48740b661f66d8559ff55379afaeac55d4c716ee2f1200e287f9bcd3d6e81da1d5781cd58457585b9647aba8fdabda6f34a5e69883864c20aff5b5f0cdedeb15ac0cfa254e266e92f9dec90510140b67bde00ccec2765006eeff2ca6f29476214165dd6ca4124f3217de468b62c97786c700d384757c0e467ab6961186f29aec02784956ed742130ef6c2695f956b7c280315d525f43f8f4caa29ef24c52297ee42bdacf44af21574bb07fd2f574f670c112c20d3d94f942d2922f1fdf94ed8e00edacdb1dbb967fae5b766eb8adbca292e3e17436f4451b510dea8784d5a5d0662f8bfe3b15ff4ef3a833c7bd2a1660441c58bebebcf51541a7642a839e4bfec6eaad1782041adba161ff417288fc146b7fd450c2781b204365c8dacb54a92f38c1a1fd81189317f78d9cdea88fb8f49d06b0dbe3ea161db48fa656c90463da9eeb6c7301f6311223f32f56d91fe21b87354608571121ad7b84e5",
            "123"
        )
        println(encryptedDesc)
        println(isValid(encryptedDesc))
        println(HtmlValidator().isValid("<p>а) Докажите следующие свойства, зная только определение функции Эйлера:</p><p><br></p><ol><li>φ(p<sup>n</sup>) = p<sup>n</sup> − p<sup>n−1</sup> , где p — простое</li><li>φ(n) четно, если n &gt; 2</li><li>φ(m)φ(n) = φ(НОД(m, n)) φ(НОК(m, n))</li></ol><p>б) Используя функцию Эйлера, покажите, что множество простых чисел бесконечно.</p>\u000B\u000B\u000B\u000B\u000B\u000B\u000B\u000B\u000B\u000B\u000B"))
        println(HtmlValidator().isValid(encryptedDesc))
        println(isValid("<ol><li>Назовите известные вам признаки деления числа на 2, 3, 5 и 9.</li><li>Используя малую теорему Ферма, найдите остаток от деления числа 5<sup>16 </sup>на 17.</li><li>Не используя калькулятор, найдите остаток от деления числа, состоящего из 137 единиц, на 139.</li></ol>"))
    }


    private fun isValid(desc: String): Boolean {
        val pattern = Pattern.compile("<[a-z][\\s\\S]*>.*")
        val matcher = pattern.matcher(desc)

        return matcher.matches()
    }
}
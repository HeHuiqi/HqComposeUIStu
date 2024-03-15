package com.example.hqcomposeuistu.language.base

import android.util.Log

class HqUser constructor (var name:String, var age:Int, var sex:String) {
    // 下面的常量这样使用
    // HqUser.MAN
    companion object {
        //常量
        val MEN = "男"
        val WUMENM = " 女"
        fun allSexInfo():String{
            return "[$MEN,$WUMENM]"
        }
    }
    //变量
    var info = "我叫$name,我今年${age}岁了，我的性别是$age 。"


    //无返回值函数
    fun practice():Unit {
        Log.i("hhq", "$name 正在练习1")
        return
    }
    //无返回值函数,同上
    fun practice2() {
        Log.i("hhq", "$name 正在练习2")
    }
    //有返回值函数
    fun study(): String {
        return "$name 要学习"
    }

    fun test(){
        val mapFun = fun(it: String): String { return it.uppercase() };
        val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
        fruits.filter { it.startsWith("a") }.sortedBy { it }
            .map(mapFun).forEach {
                Log.i("hhq", "fruit:$it")
            }
    }

    override fun toString(): String {
        return "{name:$name,age:$age,sex:$sex}"
    }






}
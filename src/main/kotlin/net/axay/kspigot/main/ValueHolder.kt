package net.axay.kspigot.main

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object ValueHolder {

    private val gsonBuilder by lazy {
        GsonBuilder()
    }

    val gson: Gson by lazy { gsonBuilder.create() }
    val gsonPretty: Gson by lazy { gsonBuilder.setPrettyPrinting().create() }

}
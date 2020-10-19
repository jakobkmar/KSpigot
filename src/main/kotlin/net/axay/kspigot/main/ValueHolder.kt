package net.axay.kspigot.main

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object ValueHolder {

    private val gsonBuilder by lazy {
        GsonBuilder()
    }

    private val gson: Gson by lazy { gsonBuilder.create() }
    private val gsonPretty: Gson by lazy { gsonBuilder.setPrettyPrinting().create() }

    fun getGson(pretty: Boolean = false) = if (pretty) gsonPretty else gson

}
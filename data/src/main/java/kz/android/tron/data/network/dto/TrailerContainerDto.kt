package kz.android.tron.data.network.dto


import com.google.gson.annotations.SerializedName


data class TrailerContainerDto(@SerializedName("results") val results: List<TrailerDto>?)
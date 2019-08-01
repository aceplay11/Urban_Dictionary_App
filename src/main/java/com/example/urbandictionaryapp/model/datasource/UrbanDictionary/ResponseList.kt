package com.example.urbandictionaryapp.model.datasource.UrbanDictionary


import com.google.gson.annotations.SerializedName

data class ResponseList(
    @SerializedName("author")
    val author: String,
    @SerializedName("current_vote")
    val currentVote: String,
    @SerializedName("defid")
    val defid: Int,
    @SerializedName("definition")
    val definition: String,
    @SerializedName("example")
    val example: String,
    @SerializedName("permalink")
    val permalink: String,
    @SerializedName("sound_urls")
    val soundUrls: List<Any>,
    @SerializedName("thumbs_down")
    val thumbsDown: Int,
    @SerializedName("thumbs_up")
    val thumbsUp: Int,
    @SerializedName("word")
    val word: String,
    @SerializedName("written_on")
    val writtenOn: String
) : (ResponseList, ResponseList) -> Int {
    override fun invoke(p1: ResponseList, p2: ResponseList): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
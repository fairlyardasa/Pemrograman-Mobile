package com.example.p11_retrovidapp.model

import com.google.gson.annotations.SerializedName

//"title": "Genshin Impact Update 4.2 Dikonfirmasi Rilis pada 8 November!",
//"thumb": "https://thelazy.media/wp-content/uploads/2023/11/Key-Art_EN-218x150.png",
//"author": "Teo Ariesda",
//"tag": "Games",
//"time": "November 5, 2023",
//"desc": "Minggu (5/11) — Tidak mau kalah dengan Honkai Star Rail, Genshin Impact juga hadirkan update terbaru 4.2 yang bertajuk \"Masquerade of the Guilty\"!\n\nSimak trailer selengkapnya di bawah:\n\nhttps://youtu.be/1Ip43DfbkUw\nGenshin Impact Tawarkan Tantangan Baru melalui...",
//"key": "2023/11/05/genshin-impact-05112023"
data class News (
    @SerializedName("title") val title:String,
    @SerializedName("author") val author:String,
    @SerializedName("time") val time:String,
)
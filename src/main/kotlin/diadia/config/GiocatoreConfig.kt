package diadia.config


import com.google.gson.annotations.SerializedName

data class GiocatoreConfig(
    @SerializedName("CFUDef")
    val cFUDef: Int
)
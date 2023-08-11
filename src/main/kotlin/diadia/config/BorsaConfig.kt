package diadia.config


import com.google.gson.annotations.SerializedName

data class BorsaConfig(
    @SerializedName("PesoDef")
    val pesoDef: Int
)
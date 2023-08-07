package diadia.config


import com.google.gson.annotations.SerializedName

data class BorsaConfig(
    @SerializedName("SogliaMagicaStandar")
    val pesoDef: Int
)
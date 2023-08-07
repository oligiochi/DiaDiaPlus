package diadia.config


import com.google.gson.annotations.SerializedName

data class DiaDiaConfig(
    @SerializedName("MessaggioDiBenvenuto")
    val messaggioDiBenvenuto: String
)
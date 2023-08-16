package diadia.config.tipiPersonaggiConfig

import com.google.gson.annotations.SerializedName

data class MagoConfig(
    @SerializedName("Nome")
    val nome: String,
    @SerializedName("Presentazione")
    val presentazione: String
)
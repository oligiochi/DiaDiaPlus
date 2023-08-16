package diadia.config.tipiPersonaggiConfig

import com.google.gson.annotations.SerializedName

data class StregaConfig(
    @SerializedName("Nome")
    val nome: String,
    @SerializedName("Presentazione")
    val presentazione: String
)
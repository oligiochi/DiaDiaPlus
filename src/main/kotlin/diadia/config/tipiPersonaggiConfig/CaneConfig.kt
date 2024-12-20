package diadia.config.tipiPersonaggiConfig

import com.google.gson.annotations.SerializedName

data class CaneConfig(
    @SerializedName("Nome")
    val nome: String,
    @SerializedName("Presentazione")
    val presentazione: String,
    @SerializedName("OggettiPreferitiDef")
    val oggettiPreferitiDef: List<String>
)
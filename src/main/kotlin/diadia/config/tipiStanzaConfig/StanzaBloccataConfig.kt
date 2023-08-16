package diadia.config.tipiStanzaConfig

import com.google.gson.annotations.SerializedName

data class StanzaBloccataConfig(
    @SerializedName("OggettiSbloccantiDef")
    val oggettiSbloccantiDef: List<String>
)

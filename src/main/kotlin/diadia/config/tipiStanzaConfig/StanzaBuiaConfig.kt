package diadia.config.tipiStanzaConfig

import com.google.gson.annotations.SerializedName

data class StanzaBuiaConfig(
    @SerializedName("OggettiSbloccantiDef")
    val oggettiSbloccantiDef: List<String>
)

package diadia.config
import com.google.gson.annotations.SerializedName
import diadia.config.tipiStanzaConfig.StanzaBloccataConfig
import diadia.config.tipiStanzaConfig.StanzaBuiaConfig
import diadia.config.tipiStanzaConfig.StanzaMagicaConfig

data class StanzaConfig(
    @SerializedName("StanzaBuia")
    val stanzaBuiaConfig: StanzaBuiaConfig,
    @SerializedName("StanzaBloccata")
    val stanzaBloccataConfig: StanzaBloccataConfig,
    @SerializedName("StanzaMagica")
    val stanzaMagicaConfig: StanzaMagicaConfig
)
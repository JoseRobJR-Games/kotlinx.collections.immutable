package kotlinx.collections.immutable.serializers

import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.toImmutableMap
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public class ImmutableMapSerializer<K, V>(kSerializer: KSerializer<K>, vSerializer: KSerializer<V>): KSerializer<ImmutableMap<K, V>> {
    private val delegation = MapSerializer(kSerializer, vSerializer)
    
    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor = SerialDescriptor("ImmutableMap", delegation.descriptor)
    override fun deserialize(decoder: Decoder): ImmutableMap<K, V> {
        return delegation.deserialize(decoder).toImmutableMap()
    }

    override fun serialize(encoder: Encoder, value: ImmutableMap<K, V>) {
        delegation.serialize(encoder, value)
    }
}

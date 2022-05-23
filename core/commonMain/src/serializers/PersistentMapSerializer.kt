package kotlinx.collections.immutable.serializers

import kotlinx.collections.immutable.PersistentMap
import kotlinx.collections.immutable.toPersistentMap
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public class PersistentMapSerializer<K, V>(kSerializer: KSerializer<K>, vSerializer: KSerializer<V>): KSerializer<PersistentMap<K, V>> {
    private val delegation = MapSerializer(kSerializer, vSerializer)
    
    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor = SerialDescriptor("PersistentMap", delegation.descriptor)
    override fun deserialize(decoder: Decoder): PersistentMap<K, V> {
        return delegation.deserialize(decoder).toPersistentMap()
    }

    override fun serialize(encoder: Encoder, value: PersistentMap<K, V>) {
        delegation.serialize(encoder, value)
    }
}

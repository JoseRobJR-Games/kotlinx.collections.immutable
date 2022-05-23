package kotlinx.collections.immutable.serializers

import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.toPersistentSet
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.SetSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public class PersistentSetSerializer<E>(eSerializer: KSerializer<E>): KSerializer<PersistentSet<E>> {
    private val delegation = SetSerializer(eSerializer)
    
    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor = SerialDescriptor("PersistentSet", delegation.descriptor)
    override fun deserialize(decoder: Decoder): PersistentSet<E> {
        return delegation.deserialize(decoder).toPersistentSet()
    }

    override fun serialize(encoder: Encoder, value: PersistentSet<E>) {
        delegation.serialize(encoder, value)
    }
}

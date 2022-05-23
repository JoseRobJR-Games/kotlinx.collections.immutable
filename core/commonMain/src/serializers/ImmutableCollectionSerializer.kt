package kotlinx.collections.immutable.serializers

import kotlinx.collections.immutable.ImmutableCollection
import kotlinx.collections.immutable.toImmutableList
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public class ImmutableCollectionSerializer<E>(eSerializer: KSerializer<E>): KSerializer<ImmutableCollection<E>> {
    private val delegation = ListSerializer(eSerializer)
    
    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor = SerialDescriptor("ImmutableCollection", delegation.descriptor)
    override fun deserialize(decoder: Decoder): ImmutableCollection<E> {
        return delegation.deserialize(decoder).toImmutableList()
    }

    override fun serialize(encoder: Encoder, value: ImmutableCollection<E>) {
        @Suppress("UNCHECKED_CAST")
        delegation.serialize(encoder, value as? List<E> ?: value.toList())
    }
}

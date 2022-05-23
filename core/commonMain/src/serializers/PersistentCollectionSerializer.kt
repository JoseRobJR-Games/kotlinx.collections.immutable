package kotlinx.collections.immutable.serializers

import kotlinx.collections.immutable.PersistentCollection
import kotlinx.collections.immutable.toPersistentList
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public class PersistentCollectionSerializer<E>(eSerializer: KSerializer<E>): KSerializer<PersistentCollection<E>> {
    private val delegation = ListSerializer(eSerializer)
    
    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor = SerialDescriptor("PersistentCollection", delegation.descriptor)
    override fun deserialize(decoder: Decoder): PersistentCollection<E> {
        return delegation.deserialize(decoder).toPersistentList()
    }

    override fun serialize(encoder: Encoder, value: PersistentCollection<E>) {
        @Suppress("UNCHECKED_CAST")
        delegation.serialize(encoder, value as? List<E> ?: value.toList())
    }
}

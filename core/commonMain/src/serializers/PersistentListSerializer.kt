package kotlinx.collections.immutable.serializers

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public class PersistentListSerializer<E>(eSerializer: KSerializer<E>): KSerializer<PersistentList<E>> {
    private val delegation = ListSerializer(eSerializer)
    
    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor = SerialDescriptor("PersistentList", delegation.descriptor)
    override fun deserialize(decoder: Decoder): PersistentList<E> {
        return delegation.deserialize(decoder).toPersistentList()
    }

    override fun serialize(encoder: Encoder, value: PersistentList<E>) {
        delegation.serialize(encoder, value)
    }
}

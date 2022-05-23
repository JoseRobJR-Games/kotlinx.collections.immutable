package kotlinx.collections.immutable.serializers

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public class ImmutableListSerializer<E>(eSerializer: KSerializer<E>): KSerializer<ImmutableList<E>> {
    private val delegation = ListSerializer(eSerializer)
    
    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor = SerialDescriptor("ImmutableList", delegation.descriptor)
    override fun deserialize(decoder: Decoder): ImmutableList<E> {
        return delegation.deserialize(decoder).toImmutableList()
    }

    override fun serialize(encoder: Encoder, value: ImmutableList<E>) {
        delegation.serialize(encoder, value)
    }
}

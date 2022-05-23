package kotlinx.collections.immutable.serializers

import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.toImmutableSet
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.SetSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public class ImmutableSetSerializer<E>(eSerializer: KSerializer<E>): KSerializer<ImmutableSet<E>> {
    private val delegation = SetSerializer(eSerializer)
    
    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor = SerialDescriptor("ImmutableSet", delegation.descriptor)
    override fun deserialize(decoder: Decoder): ImmutableSet<E> {
        return delegation.deserialize(decoder).toImmutableSet()
    }

    override fun serialize(encoder: Encoder, value: ImmutableSet<E>) {
        delegation.serialize(encoder, value)
    }
}

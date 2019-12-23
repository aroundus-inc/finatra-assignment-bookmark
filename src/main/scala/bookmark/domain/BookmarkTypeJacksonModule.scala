package bookmark.domain

import com.fasterxml.jackson.core.{JsonGenerator, JsonParser}
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.{
  DeserializationContext,
  JsonDeserializer,
  JsonSerializer,
  SerializerProvider
}

object BookmarkTypeJacksonModule extends SimpleModule {

  class BookmarkTypeDeserializer extends JsonDeserializer[BookmarkType] {

    override def deserialize(
      p: JsonParser,
      ctx: DeserializationContext
    ): BookmarkType =
      Option(p.getText).map(BookmarkType.valueOf).orNull
  }

  class BookmarkTypeSerializer extends JsonSerializer[BookmarkType] {

    override def handledType: Class[BookmarkType] = classOf[BookmarkType]

    override def serialize(
      value: BookmarkType,
      gen: JsonGenerator,
      serializers: SerializerProvider
    ): Unit =
      gen.writeString(value.name)
  }

  addDeserializer(classOf[BookmarkType], new BookmarkTypeDeserializer)
  addSerializer(new BookmarkTypeSerializer)
}

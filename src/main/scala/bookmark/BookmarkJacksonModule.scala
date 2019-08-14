package bookmark

import bookmark.domain.BookmarkTypeJacksonModule
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.{
  DeserializationFeature,
  Module,
  ObjectMapper,
  PropertyNamingStrategy,
  SerializationFeature
}
import com.twitter.finatra.json.modules.FinatraJacksonModule

object BookmarkJacksonModule extends FinatraJacksonModule {

  override val additionalJacksonModules: Seq[Module] =
    super.additionalJacksonModules ++ Seq(BookmarkTypeJacksonModule)

  override val serializationInclusion = JsonInclude.Include.ALWAYS

  override val propertyNamingStrategy: PropertyNamingStrategy =
    PropertyNamingStrategy.LOWER_CAMEL_CASE

  override def additionalMapperConfiguration(mapper: ObjectMapper): Unit =
    mapper
      .setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE)
      .setSerializationInclusion(JsonInclude.Include.ALWAYS)
      .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
      .configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true)
      .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true)
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
      .configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true)
      .configure(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY, true)
      .configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, false)
      .configure(
        DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,
        true
      )
}

package bookmark.controllers

import bookmark.BookmarkServer
import bookmark.repository.{BookmarkRepository, BookmarkRepositoryImpl}
import com.aroundus.example.bookmark.domain.{Bookmark, BookmarkType}
import com.google.inject.Stage
import com.twitter.finagle.http.Status
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.finatra.json.FinatraObjectMapper
import com.twitter.inject.server.FeatureTestMixin
import org.scalatest._

class BookmarkControllerTest
    extends WordSpec
    with FeatureTestMixin
    with Matchers
    with OptionValues
    with Inside
    with Inspectors
    with BeforeAndAfterAll {
  override val server =
    new EmbeddedHttpServer(new BookmarkServer, stage = Stage.DEVELOPMENT)

  val bookmarkRepository: BookmarkRepository =
    injector.instance[BookmarkRepositoryImpl]
  val mapper: FinatraObjectMapper = injector.instance[FinatraObjectMapper]

  "BookmarkControllerTest" should {
    "handle bookmark apis" in {
      val bookmark = Bookmark(
        id = "foo",
        ownerID = "owner-foo",
        `type` = BookmarkType.Foo,
        targetID = "foo-foo"
      )

      val createdBookmark = server.httpPostJson[Bookmark](
        "/Bookmarks",
        postBody = mapper.writeValueAsString(bookmark),
        andExpect = Status.Ok
      )

      createdBookmark shouldBe bookmark

      server.httpDelete(s"/Bookmarks/${bookmark.id}", andExpect = Status.Ok)

      bookmarkRepository.findByID(bookmark.id) shouldBe empty
    }
  }
}

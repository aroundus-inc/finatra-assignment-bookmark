package bookmark.controllers

import bookmark.BookmarkServer
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

  val mapper: FinatraObjectMapper = injector.instance[FinatraObjectMapper]

  "BookmarkControllerTest" should {
    "create new bookmark" in {
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
    }

    "delete new bookmark" in {
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
    }
  }
}

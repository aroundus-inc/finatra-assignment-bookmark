package bookmark.controllers

import bookmark.BookmarkServer
import bookmark.domain.{Bookmark, BookmarkType}
import bookmark.repository.BookmarkRepository
import com.google.inject.Stage
import com.twitter.finagle.http.Status
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.finatra.json.FinatraObjectMapper
import com.twitter.inject.server.FeatureTestMixin
import org.scalatest._

import scala.util.Random

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
    injector.instance[BookmarkRepository]
  val mapper: FinatraObjectMapper = injector.instance[FinatraObjectMapper]

  "BookmarkController" should {

    /**
      * API 테스트 코드 작성
      */
    "handle bookmark apis" in {
      val bookmark = Bookmark(
        id = Random.alphanumeric.take(15).mkString,
        ownerID = "owner-foo",
        `type` = BookmarkType.Foo,
        targetID = "foo-foo"
      )

      bookmarkRepository.findByID(bookmark.id) shouldBe empty

      val createdBookmark = server.httpPostJson[Bookmark](
        "/Bookmarks",
        postBody = mapper.writeValueAsString(bookmark),
        andExpect = Status.Ok
      )

      createdBookmark shouldBe bookmark
      bookmarkRepository.findByID(bookmark.id) should not be empty

      server.httpDelete(s"/Bookmarks/${bookmark.id}", andExpect = Status.Ok)

      bookmarkRepository.findByID(bookmark.id) shouldBe empty
    }
  }
}

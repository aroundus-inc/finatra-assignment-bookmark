package bookmark.controllers

import bookmark.BookmarkServer
import bookmark.domain.{Bookmark, BookmarkType}
import bookmark.repository.BookmarkRepository
import com.google.inject.Stage
import com.twitter.finagle.http.{Request, Status}
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

      // 생성 API
      val createdBookmark = server.httpPostJson[Bookmark](
        "/Bookmark",
        postBody = mapper.writeValueAsString(Map("bookmark" -> bookmark)),
        andExpect = Status.Ok
      )

      createdBookmark shouldBe bookmark
      bookmarkRepository.findByID(bookmark.id) should not be empty

      // 북마크 타입 수정 API
      val updatedBookmark = server.httpPutJson[Bookmark](
        path = s"/Bookmark/${bookmark.id}",
        putBody = mapper.writeValueAsString(
          Map("type" -> BookmarkType.Bar, "description" -> "Hello!")
        )
      )

      updatedBookmark shouldBe bookmark
        .copy(`type` = BookmarkType.Bar, description = Option("Hello!"))

      // 타입별 북마크 리스트 불러오기 API
      val barBookmarks = server.httpGetJson[Seq[Bookmark]](
        Request.queryString("/Bookmarks", Map("type" -> BookmarkType.Bar.name)),
        andExpect = Status.Ok
      )

      barBookmarks shouldBe Seq(updatedBookmark)

      // 삭제 API
      server.httpDelete(s"/Bookmark/${bookmark.id}", andExpect = Status.Ok)

      bookmarkRepository.findByID(bookmark.id) shouldBe empty
    }
  }
}

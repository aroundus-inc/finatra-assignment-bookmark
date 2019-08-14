package bookmark.services

import bookmark.{BookmarkJacksonModule, BookmarkModule}
import bookmark.domain.{Bookmark, BookmarkType}
import bookmark.repository.BookmarkRepository
import com.twitter.finatra.json.FinatraObjectMapper
import com.twitter.inject.app.TestInjector
import com.twitter.inject.{Injector, IntegrationTestMixin}
import org.scalatest._

import scala.util.Random

class BookmarkServiceTest
    extends WordSpec
    with IntegrationTestMixin
    with Matchers
    with OptionValues
    with Inside
    with Inspectors
    with BeforeAndAfterAll {
  override def injector: Injector =
    TestInjector(Seq(BookmarkModule, BookmarkJacksonModule)).create

  val bookmarkRepository: BookmarkRepository =
    injector.instance[BookmarkRepository]
  val bookmarkService: BookmarkService =
    injector.instance[BookmarkService]
  val mapper: FinatraObjectMapper = injector.instance[FinatraObjectMapper]

  /**
    * 서비스 테스트 작성
    */
  "BookmarkService" should {
    "create new bookmark" in {
      val bookmark = Bookmark(
        id = Random.alphanumeric.take(15).toString(),
        ownerID = "owner-foo",
        `type` = BookmarkType.Foo,
        targetID = "foo-foo"
      )

      bookmarkRepository.findByID(bookmark.id) shouldBe empty
      bookmarkService.createBookmark(bookmark)
      bookmarkRepository.findByID(bookmark.id) should not be empty
    }

    "not create bookmark with same id" in {
      val bookmark = Bookmark(
        id = Random.alphanumeric.take(15).toString(),
        ownerID = "owner-foo",
        `type` = BookmarkType.Foo,
        targetID = "foo-foo"
      )

      bookmarkRepository.findByID(bookmark.id) shouldBe empty
      bookmarkService.createBookmark(bookmark)
      bookmarkRepository.findByID(bookmark.id) should not be empty

      intercept[Exception] {
        bookmarkService.createBookmark(bookmark)
      }
    }
  }
}

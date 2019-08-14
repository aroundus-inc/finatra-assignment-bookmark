package bookmark.services

import bookmark.domain.Bookmark
import bookmark.repository.BookmarkRepository
import com.google.inject.{Inject, Singleton}

// Write service logic
@Singleton
class BookmarkServiceImpl @Inject()(bookmarkRepository: BookmarkRepository)
    extends BookmarkService {
  override def createBookmark(bookmark: Bookmark): Bookmark = ???

  override def updateBookmark(id: String, bookmark: Bookmark): Bookmark = ???

  override def deleteBookmark(id: String): Unit = bookmarkRepository.delete(id)

  override def getBookmarks(
    id: Seq[String],
    ownerID: Seq[String]
  ): Seq[Bookmark] = ???
}

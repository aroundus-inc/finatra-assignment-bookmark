package bookmark.services

import com.aroundus.example.bookmark.domain.Bookmark
import com.google.inject.ImplementedBy

@ImplementedBy(classOf[BookmarkServiceImpl])
trait BookmarkService {
  def createBookmark(bookmark: Bookmark): Bookmark

  def updateBookmark(id: String, bookmark: Bookmark): Bookmark

  def deleteBookmark(id: String): Unit

  def getBookmarks(id: Seq[String], ownerID: Seq[String]): Seq[Bookmark]
}

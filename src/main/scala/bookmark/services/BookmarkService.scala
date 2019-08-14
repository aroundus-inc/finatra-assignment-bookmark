package bookmark.services

import bookmark.domain.Bookmark
import com.google.inject.ImplementedBy

@ImplementedBy(classOf[BookmarkServiceImpl])
trait BookmarkService {
  def createBookmark(bookmark: Bookmark): Bookmark

  def updateBookmark(id: String, bookmark: Bookmark): Bookmark

  def deleteBookmark(id: String): Unit

  // 조건에 따라 Parameter 추가, 혹은 method 추가
  def getBookmarks(id: Seq[String], ownerID: Seq[String]): Seq[Bookmark]
}

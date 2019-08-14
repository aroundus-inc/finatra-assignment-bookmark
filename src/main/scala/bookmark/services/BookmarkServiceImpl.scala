package bookmark.services

import com.aroundus.example.bookmark.domain.Bookmark

// Write service logic
class BookmarkServiceImpl extends BookmarkService {
  override def createBookmark(bookmark: Bookmark): Bookmark = ???

  override def updateBookmark(id: String, bookmark: Bookmark): Bookmark = ???

  override def getBookmarks(
    id: Seq[String],
    ownerID: Seq[String]
  ): Seq[Bookmark] = ???
}

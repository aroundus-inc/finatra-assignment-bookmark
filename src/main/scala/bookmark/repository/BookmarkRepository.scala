package bookmark.repository

import bookmark.domain.Bookmark
import com.google.inject.ImplementedBy

@ImplementedBy(classOf[BookmarkRepositoryImpl])
trait BookmarkRepository {
  def insert(bookmark: Bookmark): Int
  def update(id: String, bookmark: Bookmark): Int
  def delete(id: String): Int
  def findByID(id: String): Option[Bookmark]
}

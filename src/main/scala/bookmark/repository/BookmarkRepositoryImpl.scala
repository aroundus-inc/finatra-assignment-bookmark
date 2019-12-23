package bookmark.repository

import bookmark.Database
import bookmark.domain.Bookmark
import com.google.inject.{Inject, Singleton}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

@Singleton
class BookmarkRepositoryImpl @Inject() (val database: Database)
    extends BookmarkRepository {
  import database.api._

  override def insert(bookmark: Bookmark): Int =
    Await.result(database.db.run(bookmarks += bookmark), Duration.Inf)
  override def update(id: String, bookmark: Bookmark): Int = ???
  override def delete(id: String): Int                     = ???

  override def findByID(id: String): Option[Bookmark] =
    Await.result(
      database.db.run(bookmarks.filter(_.id === id).result.headOption),
      Duration.Inf
    )
}

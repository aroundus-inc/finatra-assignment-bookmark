package bookmark.repository

import bookmark.Database
import com.aroundus.example.bookmark.domain.{Bookmark, BookmarkType}
import com.google.inject.{Inject, Singleton}
import slick.ast.BaseTypedType
import slick.jdbc.JdbcType
import slick.lifted.ProvenShape

import scala.concurrent.Await
import scala.concurrent.duration.Duration

@Singleton
class BookmarkRepositoryImpl @Inject()(val database: Database)
    extends BookmarkRepository {
  import database.api._

  implicit val bookmarkTypeMapper
    : JdbcType[BookmarkType] with BaseTypedType[BookmarkType] =
    MappedColumnType.base[BookmarkType, String](_.name, BookmarkType.valueOf)

  class BookmarkTable(tag: Tag) extends Table[Bookmark](tag, "bookmarks") {
    def id: Rep[String] = column[String]("id", O.PrimaryKey)
    def ownerID: Rep[String] = column[String]("owner_id")
    def bookmarkType: Rep[BookmarkType] = column[BookmarkType]("type")
    def targetID: Rep[String] = column[String]("target_id")
    def description: Rep[Option[String]] = column[Option[String]]("description")

    override def * : ProvenShape[Bookmark] =
      (id, ownerID, bookmarkType, targetID, description).mapTo[Bookmark]
  }

  val bookmarks = TableQuery[BookmarkTable]

  override def insert(bookmark: Bookmark): Int =
    Await.result(database.db.run(bookmarks += bookmark), Duration.Inf)
  override def update(id: String, bookmark: Bookmark): Int = ???
  override def delete(id: String): Int = ???

  override def findByID(id: String): Option[Bookmark] =
    Await.result(
      database.db.run(bookmarks.filter(_.id === id).result.headOption),
      Duration.Inf
    )
}

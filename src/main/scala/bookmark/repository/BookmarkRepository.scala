package bookmark.repository

import bookmark.Database
import bookmark.domain.{Bookmark, BookmarkType}
import com.google.inject.ImplementedBy
import slick.ast.BaseTypedType
import slick.jdbc.JdbcType
import slick.lifted
import slick.lifted.ProvenShape

@ImplementedBy(classOf[BookmarkRepositoryImpl])
trait BookmarkRepository {
  val database: Database

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

  val bookmarks = lifted.TableQuery[BookmarkTable]

  def insert(bookmark: Bookmark): Int
  def update(id: String, bookmark: Bookmark): Int
  def delete(id: String): Int
  def findByID(id: String): Option[Bookmark]
}

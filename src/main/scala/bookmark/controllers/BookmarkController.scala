package bookmark.controllers

import com.aroundus.example.bookmark.domain.http.BookmarkRequests.{
  CreateBookmarkRequest,
  GetBookmarkRequest,
  UpdateBookmarkRequest
}
import bookmark.services.BookmarkService
import com.google.inject.{Inject, Singleton}
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.util.FuturePool

@Singleton
class BookmarkController @Inject()(
  futurePool: FuturePool,
  bookmarkService: BookmarkService
) extends Controller {

  post("/Bookmark") { request: CreateBookmarkRequest =>
    futurePool {
      bookmarkService.createBookmark(request.bookmark)
    }
  }

  put("/Bookmark/:id") { request: UpdateBookmarkRequest =>
    futurePool {
      bookmarkService.updateBookmark(request.id, request.bookmark)
    }
  }

  get("/Bookmark/:id") { request: GetBookmarkRequest =>
    futurePool {
      bookmarkService.getBookmarks(id = Seq(request.id), ownerID = Nil) match {
        case bookmark :: Nil =>
          response.ok(bookmark)
        case Nil =>
          response.notFound
        case _ =>
          response.internalServerError("Multiple bookmarks with same id")
      }
    }
  }

  delete("/Bookmark/:id") { request: Request =>
    val id = request.getParam("id")
    if (id.isEmpty) {
      response.badRequest("Empty id param")
    } else {
      futurePool {
        bookmarkService.deleteBookmark(id)
        response.ok
      }
    }
  }

  /**
  * TODO
  * 추가해야할 API
  * 1. 북마크 카테고리 수정 API
  * 2. 카테고리별 북마크 리스트 불러오기 API
  * 3. 북마크 조회수 증가 API
  */
}

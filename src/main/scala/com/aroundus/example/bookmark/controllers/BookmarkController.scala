package com.aroundus.example.bookmark.controllers

import com.aroundus.example.bookmark.domain.http.BookmarkRequests.{
  CreateBookmarkRequest,
  GetBookmarkRequest,
  UpdateBookmarkRequest
}
import com.aroundus.example.bookmark.services.BookmarkService
import com.google.inject.{Inject, Singleton}
import com.twitter.finatra.http.Controller
import com.twitter.util.FuturePool

@Singleton
class BookmarkController @Inject()(
  futurePool: FuturePool,
  bookmarkService: BookmarkService
) extends Controller {

  post("/bookmark") { request: CreateBookmarkRequest =>
    futurePool {
      bookmarkService.createBookmark(request.bookmark)
    }
  }

  put("/bookmark/:id") { request: UpdateBookmarkRequest =>
    futurePool {
      bookmarkService.updateBookmark(request.id, request.bookmark)
    }
  }

  get("/bookmark/:id") { request: GetBookmarkRequest =>
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

}

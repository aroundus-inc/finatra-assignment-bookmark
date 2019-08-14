package com.aroundus.example.bookmark.domain.http

import com.aroundus.example.bookmark.domain.Bookmark
import com.twitter.finatra.request.RouteParam

object BookmarkRequests {
  case class CreateBookmarkRequest(bookmark: Bookmark)

  case class UpdateBookmarkRequest(
    @RouteParam("id") id: String,
    bookmark: Bookmark
  )

  case class GetBookmarkRequest(id: String)
}

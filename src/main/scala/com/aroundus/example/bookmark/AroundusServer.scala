package com.aroundus.example.bookmark

import com.aroundus.example.bookmark.controllers.BookmarkController
import com.google.inject
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.inject.app.DtabResolution

object AroundusServerMain extends AroundusServer

class AroundusServer extends HttpServer with DtabResolution {
  override protected def modules: Seq[inject.Module] =
    super.modules ++ Seq(BookmarkModule)

  override def configureHttp(router: HttpRouter): Unit =
    router
      .filter[CommonFilters]
      .add[BookmarkController]
}

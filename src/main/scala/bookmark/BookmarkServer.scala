package bookmark

import bookmark.controllers.BookmarkController
import com.google.inject
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.inject.app.DtabResolution

object BookmarkServerMain extends BookmarkServer

class BookmarkServer extends HttpServer with DtabResolution {

  override def modules: Seq[inject.Module] =
    super.modules ++ Seq(BookmarkModule)

  override def jacksonModule: inject.Module = BookmarkJacksonModule

  override def configureHttp(router: HttpRouter): Unit =
    router
      .filter[CommonFilters]
      .add[BookmarkController]
}

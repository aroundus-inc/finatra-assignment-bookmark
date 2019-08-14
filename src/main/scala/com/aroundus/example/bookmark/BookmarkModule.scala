package com.aroundus.example.bookmark

import com.google.inject.{Provides, Singleton}
import com.twitter.finatra.utils.FuturePools
import com.twitter.inject.TwitterModule
import com.twitter.util.FuturePool

object BookmarkModule extends TwitterModule {

  @Provides
  @Singleton
  def provideFuturePool: FuturePool = FuturePools.unboundedPool("bookmark")
}

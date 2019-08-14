package com.aroundus.example.bookmark.domain

sealed abstract class BookmarkType(val name: String)

object BookmarkType {
  case object Foo extends BookmarkType("Foo")
  case object Bar extends BookmarkType("Bar")

  def valueOf(name: String): BookmarkType = name.toLowerCase() match {
    case "foo" => Foo
    case "bar" => Bar
    case _     => throw new IllegalArgumentException
  }
}

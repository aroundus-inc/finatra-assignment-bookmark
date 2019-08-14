package com.aroundus.example.bookmark.domain

/**
  * 1. 카테고리 구분하기 위한 필드
  *   - BookmarkType과는 별개로 유저가 원하는대로 카테고리를 지정하여 저장할 수 있어야 함
  * 2. 생성 시간을 기록하기 위한 필드
  * 3. 조회 여부를 저장하는 필드
  */
case class Bookmark(
  id: String,
  ownerID: String,
  `type`: BookmarkType,
  targetID: String, // bookmark 대상 데이터의 id값
  description: Option[String] = None
)

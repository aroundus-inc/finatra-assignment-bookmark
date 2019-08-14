drop table if exists "bookmarks";

create table "bookmarks" (
    "id" VARCHAR NOT NULL PRIMARY KEY,
    "owner_id" VARCHAR NOT NULL,
    "type" VARCHAR NOT NULL,
    "target_id" VARCHAR NOT NULL,
    "description" VARCHAR
);

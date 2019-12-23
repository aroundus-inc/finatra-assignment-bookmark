package bookmark

import com.google.inject.Singleton
import com.typesafe.config.ConfigFactory
import slick.jdbc.{H2Profile, JdbcProfile}

@Singleton
class Database extends JdbcProfile with H2Profile {

  val db: backend.DatabaseDef =
    api.Database.forConfig("h2", ConfigFactory.load())
}

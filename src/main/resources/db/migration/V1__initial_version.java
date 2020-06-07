package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import java.sql.PreparedStatement;

/**
 * Example of a Java-based migration using Spring JDBC.
 */
public class V1__initial_version extends BaseJavaMigration {
    public void migrate(Context context) {
        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
				.execute("CREATE TABLE `tempo_db`.`user` ("
				+ " `id` INT NOT NULL AUTO_INCREMENT , "
				+ "`name` VARCHAR(50) NOT NULL , "
				+ "`email` VARCHAR(50) NOT NULL , "
				+ "`password` VARCHAR(255) NOT NULL , "
				+ "PRIMARY KEY (`id`)) ENGINE = MyISAM;");
    }
}
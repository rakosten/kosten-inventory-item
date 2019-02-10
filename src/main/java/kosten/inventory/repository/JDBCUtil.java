package kosten.inventory.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtil {

    private static HikariConfig hikariConfig = new HikariConfig();
    public static HikariDataSource hikariDataSource;
    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCUtil.class);


    static {
        hikariConfig.setJdbcUrl(System.getenv("RDS_JDBC_URL"));
        hikariConfig.setUsername(System.getenv("RDS_USERNAME"));
        hikariConfig.setPassword(System.getenv("RDS_PASSWORD"));
        hikariConfig.setMaximumPoolSize(30);
        hikariConfig.setDriverClassName(System.getenv("RDS_JDBC_DRIVER"));
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        hikariDataSource = new HikariDataSource(hikariConfig);
    }

    private JDBCUtil() {
    }


    public static <T> T execute(ConnectionCallback<T> callback) {
        try (Connection connection = hikariDataSource.getConnection()) {
            return callback.doInConnection(connection);
        } catch (SQLException e) {
            LOGGER.error("Returned error when retrieving single inventory item {}", e);
            throw new IllegalStateException("Error during execution.", e);
        }
    }

    public interface ConnectionCallback<T> {
        T doInConnection(Connection conn) throws SQLException;
    }


}

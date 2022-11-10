package dac.atv.av.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.yaml.snakeyaml.Yaml;

import javax.sql.DataSource;
import java.util.Map;

@Named
@Singleton
public class DataSourceConfigImpl implements DataSourceConfig{

    private final HikariDataSource ds;

    public DataSourceConfigImpl() throws ClassNotFoundException {
        Yaml yaml = new Yaml();
        Map<String, Object> data = yaml.load(getClass().getClassLoader().getResourceAsStream("config.yaml"));
        Map<String, Object> dbConfig = (Map<String, Object>) data.get("db");
        System.out.println(dbConfig);

        this.ds = new HikariDataSource();
        Class.forName((String) dbConfig.get("driver"));
        this.ds.setJdbcUrl((String) dbConfig.get("url"));
        this.ds.setUsername((String) dbConfig.get("user"));
        this.ds.setPassword((String) dbConfig.get("password")); //TODO weird conversion
    }

    @Override
    @Produces
    @Singleton
    public DataSource getDataSource() {
        return this.ds;
    }
}

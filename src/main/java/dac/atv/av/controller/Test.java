package dac.atv.av.controller;

import dac.atv.av.config.DataSourceConfig;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.sql.DataSource;
import java.sql.SQLException;

@Getter
@Setter
@NoArgsConstructor
@Named
public class Test {

    @Inject
    private DataSource dataSource;
    private String phaseValue = "Hello World";


    public String testConnection() throws SQLException {
        return dataSource.getConnection().toString();
    }



}

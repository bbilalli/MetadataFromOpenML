package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by marcin on 26.05.16.
 */
public interface ISqlResultHandler {

    void handleResultRow(ResultSet row) throws SQLException;
}

package exception;

import java.sql.SQLException;

public class DAOException extends RuntimeException {

    public DAOException(String message, SQLException e) {
        super(message, e);
    }

}

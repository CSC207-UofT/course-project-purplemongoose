package database.SQLite.arguments;

import util.BytesSerializerDeserializer;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLiteSerializableArg<T extends Serializable> extends SQLiteArg<byte[]>{

    /**
     * Create an instance of the SQLite argument class with arg as the argument.
     *
     * @param arg the argument that the SQLiteArg instance is for.
     */
    public SQLiteSerializableArg(T arg) {
        super(new BytesSerializerDeserializer<T>(arg).getAsBytes());
    }

    @Override
    public void setArg(int index, PreparedStatement statement) throws SQLException {
        statement.setBytes(index, this.arg);
    }
}

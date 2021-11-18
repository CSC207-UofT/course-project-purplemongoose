package database.gateway;

import java.io.*;
import java.sql.*;


public abstract class DatabaseGateway {

    /**
     * Abstract method for connecting to a database
     * @return returns a connection object for the database
     */
    abstract Connection databaseConnect();

    /**
     * Serializes an object into a byte array which can the best stored inside a database
     * @param object the object to be serialized
     * @return the serialized byte array of the object
     */
    public static byte[] toBytes(Object object) {
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
            objOut.writeObject(object);
            return byteOut.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Deserializes byte arrays into the original object
     * @param data the byte array containing object info
     * @return the deserialized object
     */
    public static Object toObject(byte[] data) {
        try {
            ByteArrayInputStream byteIn = new ByteArrayInputStream(data);
            ObjectInputStream objIn = new ObjectInputStream(byteIn);
            return objIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}

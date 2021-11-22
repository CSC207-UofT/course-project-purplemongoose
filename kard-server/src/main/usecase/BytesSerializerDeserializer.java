package usecase;

import java.io.*;

/**
 * A class for serializing and deserializing objects to bytes.
 *
 * @param <T> The class being serialized/deserialized
 */
public class BytesSerializerDeserializer<T extends Serializable> {
    private final T asSerializable;
    private final byte[] asBytes;

    /**
     * Serializes an object into a byte array which can the best stored inside a database
     * @param object the object to be serialized
     * @return the serialized byte array of the object
     */
    private static byte[] toBytes(Object object) {
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
    private T toObject(byte[] data) {
        try {
            ByteArrayInputStream byteIn = new ByteArrayInputStream(data);
            ObjectInputStream objIn = new ObjectInputStream(byteIn);

            return (T) objIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Create a BytesSerializerDeserializer object given the bytes.
     *
     * @param asBytes the bytes of the object.
     */
    public BytesSerializerDeserializer (byte[] asBytes) {
        this.asBytes = asBytes;
        this.asSerializable = toObject(this.asBytes);

    }

    /**
     * Create a BytesSerializerDeserializer object given the object.
     *
     * @param asSerializable the object.
     */
    public BytesSerializerDeserializer (T asSerializable) {
        this.asSerializable = asSerializable;
        this.asBytes = toBytes(this.asSerializable);
    }

    /**
     * Fetch an object represented by the array of bytes.
     *
     * @return an instance of T fetched from teh byte representation.
     */
    public T getAsSerializable() {
        return asSerializable;
    }

    /**
     * Fetch the object as an array of Bytes.
     *
     * @return a representation of the object as an array of Bytes.
     */
    public byte[] getAsBytes() {
        return asBytes;
    }
}

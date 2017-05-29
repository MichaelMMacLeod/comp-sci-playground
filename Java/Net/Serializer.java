import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

public final class Serializer {
	/**
	 * No need to instantiate a Serializer object.
	 */
	private Serializer() {}

	/**
	 * Reads a serialized object from a file.
	 * 
	 * @param filePath 
	 * @return the object located at filePath, or null, if filePath does not 
	 *         exist.
	 */
	public static Object deSerialize(String filePath) {
        try {
            FileInputStream fin = new FileInputStream(filePath);
            ObjectInputStream oin = new ObjectInputStream(fin);

            Object s = oin.readObject();

            fin.close();
            oin.close();

            return s;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Serializes and object and writes the serialization to filePath.
     */
    public static void serialize(Serializable s, String filePath) {
        try {
            FileOutputStream fout = new FileOutputStream(filePath);
            ObjectOutputStream oout = new ObjectOutputStream(fout);
            
            oout.writeObject(s);

            fout.close();
            oout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
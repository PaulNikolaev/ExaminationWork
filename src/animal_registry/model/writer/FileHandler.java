package animal_registry.model.writer;

import java.io.*;

public class FileHandler implements Writer {

    private String filePath = "src/animal_registry/model/save_registry/animalRegistry.txt";

    @Override
    public void setPath(String filePath) {
        this.filePath = filePath;
    }


    @Override
    public void save(Serializable serializable) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            objectOutputStream.writeObject(serializable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object read() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            return objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

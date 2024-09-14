package animal_registry.model.animal_registry;

import java.util.Iterator;
import java.util.List;

public class AnimalIterator<T> implements Iterator<T> {
    private List<T> animal;
    private int currenId;

    public AnimalIterator(List<T> animal) {
        this.animal = animal;
    }

    @Override
    public boolean hasNext() {
        return animal.size() > currenId;
    }

    @Override
    public T next() {
        return animal.get(currenId++);
    }
}

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private final Resume[] storage = new Resume[10000];
    private int storageSize;

    void clear() {
        for (int i = 0; i < storageSize; i++) {
            storage[i] = null;
        }
        storageSize = 0;
    }

    void save(Resume r) {
        storage[storageSize] = r;
        storageSize++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (storage[i].uuid == uuid) {
                return storage[i];
            }
        }
        return new Resume();
    }

    void delete(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (storage[i].uuid == uuid) {
                storage[i] = storage[storageSize - 1];
                storage[storageSize - 1] = null;
                storageSize--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        if (storageSize > 0) {
            return Arrays.copyOf(storage, storageSize);
        } else {
            return new Resume[0];
        }
    }

    int size() {
        return storageSize;
    }
}

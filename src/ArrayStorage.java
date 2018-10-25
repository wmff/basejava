import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private static final int STORAGE_LIMIT = 10_000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int storageSize;

    void clear() {
        Arrays.fill(storage, 0, storageSize, null);
        storageSize = 0;
    }

    void save(Resume r) {
        if (getIndexResume(r.getUuid()) != -1) {
            System.out.println("Resume uuid=" + r.getUuid() + " exist in storage");
        } else if (storage.length == storageSize) {
            System.out.println("Storage overflow");
        } else {
            storage[storageSize] = r;
            storageSize++;
        }
    }

    Resume get(String uuid) {
        int indexResume = getIndexResume(uuid);

        if (indexResume == -1) {
            System.out.println("Resume with uuid=" + uuid + " not exist in storage");
            return null;
        }

        return storage[indexResume];
    }

    void delete(String uuid) {
        int indexResume = getIndexResume(uuid);

        if (indexResume == -1) {
            System.out.println("Resume with uuid=" + uuid + " not exist in storage");
        } else {
            storage[indexResume] = storage[storageSize - 1];
            storage[storageSize - 1] = null;
            storageSize--;
        }
    }

    void update(Resume r) {
        int indexResume = getIndexResume(r.getUuid());

        if (indexResume == -1) {
            System.out.println("Resume with uuid=" + r.getUuid() + " not exist in storage");
        } else {
            storage[indexResume] = r;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, storageSize);
    }

    int size() {
        return storageSize;
    }

    private int getIndexResume(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}

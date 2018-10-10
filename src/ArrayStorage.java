import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    Resume[] storage = new Resume[10000];
    private int resumeCount;

    public ArrayStorage() {
        resumeCount = 1;
    }

    public void clear() {
        for (int i = 0; i < resumeCount - 1; i++) {
            storage[i] = null;
        }
        resumeCount = 1;
    }

    public void save(Resume r) {
        storage[resumeCount - 1] = r;
        resumeCount++;
    }

    public Resume get(String uuid) {
        for (int i = 0; i < resumeCount - 1; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < resumeCount - 1; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
            }
        }
        Arrays.sort(storage, new ResumeComparator());
        resumeCount--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, resumeCount - 1);
    }

    public int size() {
        return resumeCount - 1;
    }
}

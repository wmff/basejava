import java.util.Comparator;

public class ResumeComparator implements Comparator<Resume> {

    public int compare(Resume r1, Resume r2) {
        if (r1 == null && r2 == null) {
            return 0;
        }

        if (r1 == null) {
            return 1;
        }

        if (r2 == null) {
            return -1;
        }

        return 1;
    }
}

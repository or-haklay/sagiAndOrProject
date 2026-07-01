package sagiAndOr;

import java.util.Comparator;

public class CommitteeByMembersComparator implements Comparator<Committee> {
    @Override
    public int compare(Committee o1, Committee o2) {
        return o1.getNumOfLecturers() - o2.getNumOfLecturers();
    }
}

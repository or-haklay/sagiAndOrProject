package sagiAndOr;

import java.util.Comparator;

public class CommitteeByArticlesComparator implements Comparator<Committee> {
    @Override
    public int compare(Committee o1, Committee o2) {
        return o1.getTotalArticles() - o2.getTotalArticles();
    }
}

package in.ezeon;

import java.util.Map;
import java.util.TreeMap;

public class ReportService {
    private Repository repo = Repository.getRepository();

    public Map<String, Float> calculateCategoryTotal() {
        Map<String, Float> m = new TreeMap<>();
        for (Expense exp : repo.expList) {
            Long categoryId = exp.getCategoryId();
            String catName = this.getCategoryNameById(categoryId);
            if (m.containsKey(catName)) {
//                When expense is already present for a category
                Float total = m.get(catName);
                total = total + exp.getAmount();
                m.put(catName, total);
            } else {
//                This category is not yet added, so add here
                m.put(catName, exp.getAmount());

            }
        }
        return m;
    }

    public String getCategoryNameById(Long categoryId) {
        for (Category c : repo.catList) {
            if (c.getCategoryId().equals(categoryId))
                return c.getName();
        }
        return null; // no such category id exist
    }
}

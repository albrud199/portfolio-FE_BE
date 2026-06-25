package llm.iteration2.mall.repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {
    private final Map<Long, Map<String, Object>> rows = new LinkedHashMap<>();
    private long sequence = 1;

    public Map<String, Object> save(Map<String, Object> product) {
        product.put("id", sequence++);
        rows.put((Long) product.get("id"), product);
        return product;
    }

    public List<Map<String, Object>> findAll() {
        return new ArrayList<>(rows.values());
    }
}

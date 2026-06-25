package llm.iteration2.mall.service;

import java.util.List;
import java.util.Map;
import llm.iteration2.mall.repository.ProductRepository;

public class ProductService {
    private final ProductRepository repository = new ProductRepository();

    public Map<String, Object> create(Map<String, Object> product) {
        if (!product.containsKey("name")) {
            throw new IllegalArgumentException("name is required");
        }
        if (!product.containsKey("price")) {
            throw new IllegalArgumentException("price is required");
        }
        return repository.save(product);
    }

    public List<Map<String, Object>> list() {
        return repository.findAll();
    }
}

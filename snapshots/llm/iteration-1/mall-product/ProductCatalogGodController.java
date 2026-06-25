package llm.iteration1.mall;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductCatalogGodController {
    private final Map<Long, Map<String, Object>> products = new LinkedHashMap<>();
    private final Map<Long, Map<String, Object>> brands = new LinkedHashMap<>();
    private final Map<Long, Map<String, Object>> categories = new LinkedHashMap<>();
    private long sequence = 1;

    @PostMapping("/create")
    public Map<String, Object> createProduct(@RequestBody Map<String, Object> body) {
        Map<String, Object> product = new LinkedHashMap<>();
        product.put("id", sequence++);
        product.put("name", body.get("name"));
        product.put("brandId", body.get("brandId"));
        product.put("categoryId", body.get("categoryId"));
        product.put("price", new BigDecimal(String.valueOf(body.getOrDefault("price", "0"))));
        product.put("publishStatus", 0);
        products.put((Long) product.get("id"), product);
        return product;
    }

    @PutMapping("/update/{id}")
    public Map<String, Object> updateProduct(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        Map<String, Object> product = products.get(id);
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }
        product.putAll(body);
        return product;
    }

    @PostMapping("/publishStatus")
    public Map<String, Object> publishProducts(@RequestBody List<Long> ids) {
        for (Long id : ids) {
            if (products.containsKey(id)) {
                products.get(id).put("publishStatus", 1);
            }
        }
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("updated", ids.size());
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> deleteProduct(@PathVariable Long id) {
        products.remove(id);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("deleted", id);
        return response;
    }

    @GetMapping("/list")
    public List<Map<String, Object>> listProducts() {
        return new ArrayList<>(products.values());
    }

    @PostMapping("/brand")
    public Map<String, Object> saveBrand(@RequestBody Map<String, Object> body) {
        body.put("id", sequence++);
        brands.put((Long) body.get("id"), body);
        return body;
    }

    @PostMapping("/category")
    public Map<String, Object> saveCategory(@RequestBody Map<String, Object> body) {
        body.put("id", sequence++);
        categories.put((Long) body.get("id"), body);
        return body;
    }
}

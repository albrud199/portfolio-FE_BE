package llm.iteration2.mall.controller;

import java.util.List;
import java.util.Map;
import llm.iteration2.mall.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService service = new ProductService();

    @PostMapping("/create")
    public Map<String, Object> create(@RequestBody Map<String, Object> product) {
        return service.create(product);
    }

    @GetMapping("/list")
    public List<Map<String, Object>> list() {
        return service.list();
    }
}

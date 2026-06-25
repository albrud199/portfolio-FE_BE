package llm.iteration3.mall.controller;

import java.util.List;
import llm.iteration3.mall.dto.ProductCreateRequest;
import llm.iteration3.mall.dto.ProductView;
import llm.iteration3.mall.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ProductView create(@RequestBody ProductCreateRequest request) {
        return productService.create(request);
    }

    @PostMapping("/publish/{id}")
    public ProductView publish(@PathVariable Long id) {
        return productService.publish(id);
    }

    @GetMapping("/list")
    public List<ProductView> list() {
        return productService.list();
    }
}

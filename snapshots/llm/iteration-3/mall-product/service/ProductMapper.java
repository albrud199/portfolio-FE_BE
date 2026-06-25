package llm.iteration3.mall.service;

import llm.iteration3.mall.dto.ProductCreateRequest;
import llm.iteration3.mall.dto.ProductView;
import llm.iteration3.mall.model.Product;

public class ProductMapper {
    public Product toProduct(Long id, ProductCreateRequest request) {
        return new Product(id, request.getName(), request.getBrandId(), request.getCategoryId(), request.getPrice(), false);
    }

    public ProductView toView(Product product) {
        return new ProductView(product.getId(), product.getName(), product.getPrice(), product.isPublished());
    }
}

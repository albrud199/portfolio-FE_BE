package llm.iteration3.mall.service;

import java.util.List;
import llm.iteration3.mall.dto.ProductCreateRequest;
import llm.iteration3.mall.dto.ProductView;

public interface ProductService {
    ProductView create(ProductCreateRequest request);

    ProductView publish(Long id);

    List<ProductView> list();
}

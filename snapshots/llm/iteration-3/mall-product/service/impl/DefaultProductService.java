package llm.iteration3.mall.service.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import llm.iteration3.mall.dto.ProductCreateRequest;
import llm.iteration3.mall.dto.ProductView;
import llm.iteration3.mall.model.Product;
import llm.iteration3.mall.repository.ProductRepository;
import llm.iteration3.mall.service.ProductMapper;
import llm.iteration3.mall.service.ProductService;

public class DefaultProductService implements ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final AtomicLong sequence = new AtomicLong(1);

    public DefaultProductService(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ProductView create(ProductCreateRequest request) {
        validate(request);
        Product product = mapper.toProduct(sequence.getAndIncrement(), request);
        return mapper.toView(repository.save(product));
    }

    @Override
    public ProductView publish(Long id) {
        Product product = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        return mapper.toView(repository.save(product.publish()));
    }

    @Override
    public List<ProductView> list() {
        return repository.findAll().stream().map(mapper::toView).collect(Collectors.toList());
    }

    private void validate(ProductCreateRequest request) {
        if (request == null || request.getName() == null || request.getPrice() == null) {
            throw new IllegalArgumentException("Product name and price are required");
        }
    }
}

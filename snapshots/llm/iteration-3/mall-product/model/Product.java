package llm.iteration3.mall.model;

import java.math.BigDecimal;

public class Product {
    private final Long id;
    private final String name;
    private final Long brandId;
    private final Long categoryId;
    private final BigDecimal price;
    private final boolean published;

    public Product(Long id, String name, Long brandId, Long categoryId, BigDecimal price, boolean published) {
        this.id = id;
        this.name = name;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.price = price;
        this.published = published;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isPublished() {
        return published;
    }

    public Product publish() {
        return new Product(id, name, brandId, categoryId, price, true);
    }
}

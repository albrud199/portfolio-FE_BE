package llm.iteration3.mall.dto;

import java.math.BigDecimal;

public class ProductView {
    private final Long id;
    private final String name;
    private final BigDecimal price;
    private final boolean published;

    public ProductView(Long id, String name, BigDecimal price, boolean published) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.published = published;
    }

    public Long getId() {
        return id;
    }

    public boolean isPublished() {
        return published;
    }
}

package llm.iteration3.mall.dto;

import java.math.BigDecimal;

public class ProductCreateRequest {
    private String name;
    private Long brandId;
    private Long categoryId;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public Long getBrandId() {
        return brandId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public BigDecimal getPrice() {
        return price;
    }
}

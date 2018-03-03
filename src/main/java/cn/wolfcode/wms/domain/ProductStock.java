package cn.wolfcode.wms.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductStock {
    private Long id;

    private BigDecimal price;

    private BigDecimal storeNumber;

    private BigDecimal amount;

    private Product product;

    private Depot depot;
}
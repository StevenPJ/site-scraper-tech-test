package co.uk.sainsburys.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Product {
    private String title;
    private Integer calories;
    private Money price;
}

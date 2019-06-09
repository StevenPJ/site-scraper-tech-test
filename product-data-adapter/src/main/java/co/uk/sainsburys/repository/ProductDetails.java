package co.uk.sainsburys.repository;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ProductDetails {
    private String title;
    private String description;
    private String calories;
    private String price;
}

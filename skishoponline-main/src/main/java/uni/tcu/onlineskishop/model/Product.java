package uni.tcu.onlineskishop.model;

import jakarta.persistence.*;
import uni.tcu.onlineskishop.enums.ProductType;

@Entity
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PRICE", nullable = false)
    private double price;

    @Column(name = "DESCRIPTION", length = 1000)
    private String description;

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;

    @Column(name = "IMAGE_URL", nullable = false, length = 2000)
    private String imageUrl;

    @Column(name = "TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }
}

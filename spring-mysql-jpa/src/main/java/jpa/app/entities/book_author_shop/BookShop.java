package jpa.app.entities.book_author_shop;

import jakarta.persistence.*;
import jpa.app.entities.shared.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name= "shop")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "shop_name", nullable = false)
    private String shopName;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "abbreviation", nullable = false)
    private String abbreviation;


    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToMany
    @JoinTable(name="shop_books", joinColumns={@JoinColumn(name = "shop_id")}
            , inverseJoinColumns = {@JoinColumn(name = "book_id")})
    private List<Book> booksList;


}

package jpa.app.entities.book_author_shop;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "book_name", nullable = false)
    private String bookName;


    @ManyToMany
    @JoinTable(name = "book_authors", joinColumns = {@JoinColumn(name = "book_id")}
            , inverseJoinColumns = {@JoinColumn(name = "author_id")})
    private List<Author> authors;

    @Column(name = "printed_price", nullable = false)
    private Double printedPrice;


    @ManyToMany(mappedBy = "booksList")
    private List<BookShop> shops;

}

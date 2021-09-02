package at.bergmann.quoteserver.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String quote;

    private String author;

    protected Quote() {}

    public Quote(final String quote, final String author){
        this.author=author;
        this.quote=quote;
    }

    public Long getId() {
        return id;
    }

    public String getQuote() {
        return quote;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return String.format(
                "Quote[id=%d, quote='%s', author='%s']",
                id, quote, author);
    }

}

package at.bergmann.quoteserver.repository;

import at.bergmann.quoteserver.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for Quote entities.
 * Extends JpaRepository to provide standard CRUD operations and JPA-specific functionality.
 * Custom query methods are defined for specific search requirements.
 */
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    /**
     * Finds all quotes by the exact author name.
     *
     * @param author The author name to search for
     * @return A list of quotes by the specified author
     */
    List<Quote> findByAuthor(String author);
    
    /**
     * Finds all quotes where the author name contains the given string (case-insensitive).
     *
     * @param author The string to search for in author names
     * @return A list of quotes with matching authors
     */
    List<Quote> findByAuthorContainingIgnoreCase(String author);
    
    /**
     * Finds all quotes where the quote text contains the given string (case-insensitive).
     *
     * @param quote The string to search for in quote texts
     * @return A list of quotes with matching text
     */
    List<Quote> findByQuoteContainingIgnoreCase(String quote);
}

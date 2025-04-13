package at.bergmann.quoteserver.repository;

import at.bergmann.quoteserver.config.TestConfig;
import at.bergmann.quoteserver.entity.Quote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for the QuoteRepository.
 * Tests the repository's ability to perform CRUD operations and custom queries.
 */
@DataJpaTest
@Import(TestConfig.class)
@ActiveProfiles("test")
class QuoteRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private QuoteRepository quoteRepository;

    private Quote quote1;
    private Quote quote2;

    /**
     * Sets up test data before each test.
     * Creates and persists two sample quotes.
     */
    @BeforeEach
    void setUp() {
        quote1 = new Quote("Test Quote 1", "Author 1");
        quote2 = new Quote("Test Quote 2", "Author 2");
        
        entityManager.persist(quote1);
        entityManager.persist(quote2);
        entityManager.flush();
    }

    /**
     * Tests finding a quote by its ID when the quote exists.
     * Verifies that the correct quote is returned with all its properties.
     */
    @Test
    void findById_WhenQuoteExists_ShouldReturnQuote() {
        // Act
        Optional<Quote> found = quoteRepository.findById(quote1.getId());

        // Assert
        assertTrue(found.isPresent());
        assertEquals(quote1.getQuote(), found.get().getQuote());
        assertEquals(quote1.getAuthor(), found.get().getAuthor());
    }

    /**
     * Tests finding a quote by its ID when the quote does not exist.
     * Verifies that an empty Optional is returned.
     */
    @Test
    void findById_WhenQuoteDoesNotExist_ShouldReturnEmpty() {
        // Act
        Optional<Quote> found = quoteRepository.findById(99L);

        // Assert
        assertFalse(found.isPresent());
    }

    /**
     * Tests finding quotes by exact author name.
     * Verifies that all quotes by the specified author are returned.
     */
    @Test
    void findByAuthor_ShouldReturnQuotesByAuthor() {
        // Arrange
        Quote quote3 = new Quote("Test Quote 3", "Author 1");
        entityManager.persist(quote3);
        entityManager.flush();

        // Act
        List<Quote> found = quoteRepository.findByAuthor("Author 1");

        // Assert
        assertEquals(2, found.size());
        assertTrue(found.stream().anyMatch(q -> q.getId().equals(quote1.getId())));
        assertTrue(found.stream().anyMatch(q -> q.getId().equals(quote3.getId())));
    }

    /**
     * Tests finding quotes by author when no matching quotes exist.
     * Verifies that an empty list is returned.
     */
    @Test
    void findByAuthor_WhenNoQuotesExist_ShouldReturnEmptyList() {
        // Act
        List<Quote> found = quoteRepository.findByAuthor("NonExistentAuthor");

        // Assert
        assertTrue(found.isEmpty());
    }

    /**
     * Tests case-insensitive search for quotes by author name.
     * Verifies that quotes are found regardless of case.
     */
    @Test
    void findByAuthorContainingIgnoreCase_ShouldReturnMatchingQuotes() {
        // Arrange
        Quote quote3 = new Quote("Test Quote 3", "aut 1");
        entityManager.persist(quote3);
        entityManager.flush();

        // Act
        List<Quote> found = quoteRepository.findByAuthorContainingIgnoreCase("AUTHOR");

        // Assert
        assertEquals(2, found.size());
        assertTrue(found.stream().anyMatch(q -> q.getId().equals(quote1.getId())));
        assertTrue(found.stream().anyMatch(q -> q.getId().equals(quote2.getId())));
    }

    /**
     * Tests pagination functionality.
     * Verifies that quotes are correctly paginated with the specified page size.
     */
    @Test
    void findAllWithPagination_ShouldReturnPageOfQuotes() {
        // Act
        Page<Quote> page = quoteRepository.findAll(PageRequest.of(0, 1));

        // Assert
        assertEquals(2, page.getTotalElements());
        assertEquals(1, page.getContent().size());
        assertEquals(2, page.getTotalPages());
    }

    /**
     * Tests sorting functionality.
     * Verifies that quotes are correctly sorted by author name.
     */
    @Test
    void findAllWithSorting_ShouldReturnSortedQuotes() {
        // Act
        List<Quote> sorted = quoteRepository.findAll(Sort.by("author").ascending());

        // Assert
        assertEquals(2, sorted.size());
        assertEquals("Author 1", sorted.get(0).getAuthor());
        assertEquals("Author 2", sorted.get(1).getAuthor());
    }

    /**
     * Tests case-insensitive search for quotes by quote text.
     * Verifies that quotes are found when the search term appears in the quote text.
     */
    @Test
    void findByQuoteContainingIgnoreCase_ShouldReturnMatchingQuotes() {
        // Act
        List<Quote> found = quoteRepository.findByQuoteContainingIgnoreCase("test");

        // Assert
        assertEquals(2, found.size());
        assertTrue(found.stream().anyMatch(q -> q.getId().equals(quote1.getId())));
        assertTrue(found.stream().anyMatch(q -> q.getId().equals(quote2.getId())));
    }
} 
package at.bergmann.quoteserver.service;

import at.bergmann.quoteserver.entity.Quote;
import at.bergmann.quoteserver.repository.QuoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuoteServiceTest {

    @Mock
    private QuoteRepository quoteRepository;

    @InjectMocks
    private QuoteServiceImpl quoteService;

    private Quote quote1;
    private Quote quote2;

    @BeforeEach
    void setUp() {
        quote1 = new Quote("Test Quote 1", "Author 1");
        quote2 = new Quote("Test Quote 2", "Author 2");
    }

    @Test
    void list_ShouldReturnAllQuotes() {
        // Arrange
        List<Quote> quotes = Arrays.asList(quote1, quote2);
        when(quoteRepository.findAll()).thenReturn(quotes);

        // Act
        Iterable<Quote> result = quoteService.list();

        // Assert
        assertNotNull(result);
        assertEquals(quotes, result);
        verify(quoteRepository, times(1)).findAll();
    }

    @Test
    void findById_WhenQuoteExists_ShouldReturnQuote() {
        // Arrange
        when(quoteRepository.findById(1L)).thenReturn(Optional.of(quote1));

        // Act
        Quote result = quoteService.findById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(quote1, result);
        verify(quoteRepository, times(1)).findById(1L);
    }

    @Test
    void findById_WhenQuoteDoesNotExist_ShouldReturnNull() {
        // Arrange
        when(quoteRepository.findById(99L)).thenReturn(Optional.empty());

        // Act
        Quote result = quoteService.findById(99L);

        // Assert
        assertNull(result);
        verify(quoteRepository, times(1)).findById(99L);
    }

    @Test
    void findByAuthor_ShouldReturnQuotesByAuthor() {
        // Arrange
        List<Quote> quotes = Collections.singletonList(quote1);
        when(quoteRepository.findByAuthor("Author 1")).thenReturn(quotes);

        // Act
        Iterable<Quote> result = quoteService.findByAuthor("Author 1");

        // Assert
        assertNotNull(result);
        assertEquals(quotes, result);
        verify(quoteRepository, times(1)).findByAuthor("Author 1");
    }
} 
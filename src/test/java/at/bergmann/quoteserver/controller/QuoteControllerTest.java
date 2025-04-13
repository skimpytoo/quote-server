package at.bergmann.quoteserver.controller;

import at.bergmann.quoteserver.entity.Quote;
import at.bergmann.quoteserver.service.QuoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuoteControllerTest {

    @Mock
    private QuoteService quoteService;

    @InjectMocks
    private QuoteController quoteController;

    private Quote quote1;
    private Quote quote2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        quote1 = new Quote("Test Quote 1", "Author 1");
        quote2 = new Quote("Test Quote 2", "Author 2");
    }

    @Test
    void list_ShouldReturnAllQuotes() {
        // Arrange
        List<Quote> quotes = Arrays.asList(quote1, quote2);
        when(quoteService.list()).thenReturn(quotes);

        // Act
        Iterable<Quote> result = quoteController.list();

        // Assert
        assertNotNull(result);
        assertEquals(quotes, result);
        verify(quoteService, times(1)).list();
    }

    @Test
    void get_WhenQuoteExists_ShouldReturnQuote() {
        // Arrange
        when(quoteService.findById(1L)).thenReturn(quote1);

        // Act
        Quote result = quoteController.get(1L);

        // Assert
        assertNotNull(result);
        assertEquals(quote1, result);
        verify(quoteService, times(1)).findById(1L);
    }

    @Test
    void get_WhenQuoteDoesNotExist_ShouldReturnNull() {
        // Arrange
        when(quoteService.findById(99L)).thenReturn(null);

        // Act
        Quote result = quoteController.get(99L);

        // Assert
        assertNull(result);
        verify(quoteService, times(1)).findById(99L);
    }
} 
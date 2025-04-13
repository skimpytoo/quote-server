package at.bergmann.quoteserver.service;

import at.bergmann.quoteserver.entity.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface QuoteService {
    Iterable<Quote> list();
    
    Page<Quote> list(Pageable pageable);
    
    Iterable<Quote> list(Sort sort);

    Quote findById(Long id);

    Iterable<Quote> findByAuthor(String author);
    
    Iterable<Quote> findByAuthorContaining(String author);
    
    Iterable<Quote> findByQuoteContaining(String quote);
}

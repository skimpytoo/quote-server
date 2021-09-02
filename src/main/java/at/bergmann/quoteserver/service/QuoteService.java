package at.bergmann.quoteserver.service;

import at.bergmann.quoteserver.entity.Quote;
import org.springframework.stereotype.Service;


public interface QuoteService {

    Iterable<Quote> list();

    Quote findById(final Long id);

    Iterable<Quote> findByAuthor(final String author);

}

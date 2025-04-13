package at.bergmann.quoteserver.service;

import at.bergmann.quoteserver.entity.Quote;
import at.bergmann.quoteserver.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class QuoteServiceImpl implements QuoteService {
    @Autowired
    QuoteRepository quoteRepository;

    @Override
    public Iterable<Quote> list() {
        return quoteRepository.findAll();
    }

    @Override
    public Page<Quote> list(Pageable pageable) {
        return quoteRepository.findAll(pageable);
    }

    @Override
    public Iterable<Quote> list(Sort sort) {
        return quoteRepository.findAll(sort);
    }

    @Override
    public Quote findById(Long id) {
        return quoteRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Quote> findByAuthor(String author) {
        return quoteRepository.findByAuthor(author);
    }

    @Override
    public Iterable<Quote> findByAuthorContaining(String author) {
        return quoteRepository.findByAuthorContainingIgnoreCase(author);
    }

    @Override
    public Iterable<Quote> findByQuoteContaining(String quote) {
        return quoteRepository.findByQuoteContainingIgnoreCase(quote);
    }
}

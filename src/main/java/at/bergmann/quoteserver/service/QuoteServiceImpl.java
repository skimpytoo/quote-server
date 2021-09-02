package at.bergmann.quoteserver.service;

import at.bergmann.quoteserver.entity.Quote;
import at.bergmann.quoteserver.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteServiceImpl implements QuoteService{
    @Autowired
    QuoteRepository quoteRepository;

    public Iterable<Quote> list(){
        return quoteRepository.findAll();
    }

    @Override
    public Quote findById(Long id) {
        return quoteRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Quote> findByAuthor(String author) {
        return quoteRepository.findByAuthor(author);
    }
}

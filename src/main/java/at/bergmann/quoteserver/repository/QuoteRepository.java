package at.bergmann.quoteserver.repository;

import at.bergmann.quoteserver.entity.Quote;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface  QuoteRepository extends CrudRepository<Quote,Long> {

    Optional<Quote> findById(Long id);

    List<Quote> findByAuthor(String author);
}

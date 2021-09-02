package at.bergmann.quoteserver.controller;

import at.bergmann.quoteserver.entity.Quote;
import at.bergmann.quoteserver.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/quotes")
public class QuoteController {
    @Autowired
    QuoteService quoteService;

    @GetMapping
    public Iterable<Quote> list() {
        return quoteService.list();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Quote get(@PathVariable Long id) {
        return quoteService.findById(id);
    }

}

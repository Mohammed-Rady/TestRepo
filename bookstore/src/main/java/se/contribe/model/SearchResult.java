package se.contribe.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class SearchResult {

    private List<Book> matchingBooks = new ArrayList<Book>();

    public List<Book> getMatchingBooks() {
        return matchingBooks;
    }

    public void setMatchingBooks(List<Book> matchingBooks) {
        this.matchingBooks = matchingBooks;
    }

}

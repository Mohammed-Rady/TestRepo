package se.contribe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.contribe.model.Book;
import se.contribe.model.SearchResult;
import se.contribe.model.ShoppingCart;
import se.contribe.service.BookListImpl;

@Controller
@Scope("session")
public class BookstoreController {

    @Autowired
    private BookListImpl bookListImpl;

    @Autowired
    private ShoppingCart cart;

    @Autowired
    private SearchResult searchResult;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHomePage(Model model) {
        Book[] list = bookListImpl.list("");
        model.addAttribute("bookList", list);

        Book book = new Book();
        model.addAttribute("book", book);
        return "home";
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book bk, BindingResult bindingResult, Model model) {

        boolean newItem = true;

        if (cart.getBooksInCart().size() > 0) {
            for (int i = 0; i < cart.getBooksInCart().size(); i++) {
                if (cart.getBooksInCart().get(i).getId() == bk.getId()) {
                    cart.getBooksInCart().get(i).setToBuyQuantity(cart.getBooksInCart().get(i).getToBuyQuantity() + 1);
                    newItem = false;
                }
            }
        }

        if (newItem) {
            cart.getBooksInCart().add(bk);
        }

        model.addAttribute("addMessage", "Book has been added successfully to your cart!");

        Book[] list = bookListImpl.list("");
        model.addAttribute("bookList", list);

        return "home";
    }

    @RequestMapping(value = "/searchBook", method = { RequestMethod.GET, RequestMethod.POST })
    public String searchBook(@ModelAttribute("book") Book bk, BindingResult bindingResult, Model model) {

        if (null != bk.getTitle() || null != bk.getAuthor()) {
            searchResult.setMatchingBooks(bookListImpl.search(bk));
            model.addAttribute("searchResult", searchResult);
        }

        return "search";
    }

    @RequestMapping(value = "/addSearchBook", method = RequestMethod.POST)
    public String addSearchBook(@ModelAttribute("book") Book bk, BindingResult bindingResult, Model model) {
        cart.getBooksInCart().add(bk);
        model.addAttribute("addMessage", "Book has been added successfully to your cart!");

        model.addAttribute("searchResult", searchResult);

        return "search";
    }

    @RequestMapping(value = { "/checkout", "/deleteBook" }, method = { RequestMethod.GET, RequestMethod.POST })
    public String shoppingCart(@ModelAttribute("book") Book bk, BindingResult bindingResult, Model model) {

        if (bk.getTitle() != null) {
            for (int i = 0; i < cart.getBooksInCart().size(); i++) {
                if (cart.getBooksInCart().get(i).getId() == bk.getId()) {
                    cart.getBooksInCart().remove(i);
                    break;
                }
            }
        }

        Book[] books = new Book[cart.getBooksInCart().size()];

        for (int i = 0; i < books.length; i++) {
            books[i] = cart.getBooksInCart().get(i);
        }

        int[] buyStatus = bookListImpl.buy(books);

        for (int i = 0; i < books.length; i++) {
            books[i].setStatus(buyStatus[i]);
        }

        model.addAttribute("shoppingCart", cart);

        return "shoppingcart";
    }

}

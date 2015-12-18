package se.contribe.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import se.contribe.model.Book;
import se.contribe.model.BuyStatus;

@Service
public class BookListImpl implements BookList {

    public Book[] list(String string) {
        Book[] booksArray = null;
        try {

            URL url = new URL(" http://www.contribe.se/bookstoredata/bookstoredata.txt");

            // read text returned by server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            String line;
            int count = 0;
            List<Book> books = new ArrayList<Book>();
            while ((line = in.readLine()) != null) {
                String[] split = line.split(";");
                Book book = new Book();
                book.setId(count);
                book.setTitle(split[0]);
                book.setAuthor(split[1]);
                try {
                    book.setPrice(new BigDecimal(NumberFormat.getNumberInstance(java.util.Locale.US).parse(split[2])
                            .doubleValue()));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                book.setQuantity(Integer.parseInt(split[3]));

                books.add(book);
                count++;
            }
            in.close();
            booksArray = new Book[books.size()];
            for (int i = 0; i < booksArray.length; i++) {
                booksArray[i] = books.get(i);
            }

        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
        return booksArray;
    }

    public boolean add(Book book, int amount) {
        return book.getQuantity() > amount;
    }

    public int[] buy(Book... books) {
        int[] quantities = new int[books.length];
        for (int i = 0; i < quantities.length; i++) {
            if (books[i].getToBuyQuantity() > books[i].getQuantity()) {
                quantities[i] = BuyStatus.Does_not_exist.getValue();
            } else if ((books[i].getToBuyQuantity() <= books[i].getQuantity())) {
                quantities[i] = BuyStatus.OK.getValue();
            } else if ((books[i].getQuantity() == 0)) {
                quantities[i] = BuyStatus.NOT_IN_STOCK.getValue();
            }
        }
        return quantities;
    }

    public static void main(String[] args) {
        BookListImpl BookListImpl = new BookListImpl();
        BookListImpl.list("");
    }

    public List<Book> search(Book bk) {
        Book[] list = this.list("");
        List<Book> searchResult = new ArrayList<Book>();

        for (int i = 0; i < list.length; i++) {
            if ((list[i].getTitle().contains(bk.getTitle()) && !"".equals(bk.getTitle()))
                    || (list[i].getAuthor().contains(bk.getAuthor()) && !"".equals(bk.getAuthor()))) {
                searchResult.add(list[i]);
            }
        }

        return searchResult;
    }
}

package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
public class BookController {

    private List<Book> bookList;
    private int id;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookController(){
        this.bookList = new ArrayList<Book>();
        this.id = 1;
    }

    // post request /create-book
    // pass book as request body
    @PostMapping("/create-book")
    public ResponseEntity createBook(@RequestBody Book book){

        book.setId(this.id);
        this.id = this.id+1; //incrementing the id
        bookList.add(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @GetMapping("/get-book-by-id/{id}")
    public ResponseEntity getBookById(@PathVariable int id){

        for(Book b:bookList){

            if(b.getId()==id){
                return new ResponseEntity<>(b,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/delete-book-by-id/{id}")
    public ResponseEntity deleteBookById(@PathVariable Integer id){


        for(int i=0;i<bookList.size();i++){

            if(bookList.get(i).getId()==id){
                bookList.remove(i);
                return new ResponseEntity<>("Book deleted successfully",HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/get-all-books")
    public ResponseEntity getAllBooks(){

        return new ResponseEntity<>(bookList, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-all-books")
    public ResponseEntity deleteAllBooks(){

        bookList = new ArrayList<>();
        id = 1;

        return new ResponseEntity<>("All books deleted", HttpStatus.CREATED);
    }

    @GetMapping("/get-books-by-author")
    public ResponseEntity getBooksByAuthor(@RequestParam String author){

        for(Book b:bookList){

            if(b.getAuthor()==author){
                return new ResponseEntity<>(b, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("No book found", HttpStatus.BAD_GATEWAY);
    }

    @GetMapping("/get-books-by-genre")
    public ResponseEntity getBooksByGenre(@RequestParam String genre){

        for(Book b:bookList){

            if(b.getGenre().equals(genre)){
                return new ResponseEntity<>(b, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("No book found", HttpStatus.BAD_GATEWAY);
    }
}

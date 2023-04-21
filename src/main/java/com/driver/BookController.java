package com.driver;
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    private ArrayList<Book> booklist;
    private int id;
    public BookRepository() {
        this.booklist = new ArrayList<>();
        this.id=1;
    }

    public Book save(Book book){
        book.setId(id++);
        booklist.add(book);
        return book;
    }

    public Book findBookById(int id){
        for(Book book : booklist){
            if(book.getId()==id){
                return book;
            }
        }
        return null;
    }

    public List<Book> findAll(){
        return booklist;
    }

    public void deleteBookById(int id){
        for (Book book:booklist){
            if(book.getId()==id){
                booklist.remove(book);
            }
        }
    }

    public void deleteAll(){

        booklist.clear();
    }

    public List<Book> findBooksByAuthor(String author){
        List<Book> list = new ArrayList<>();
        for(Book book : booklist){
            if(book.getAuthor().equals(author)){
                list.add(book);
            }
        }
        return list;
    }

    public List<Book> findBooksByGenre(String genre){
        List<Book> list = new ArrayList<>();
        for(Book book : booklist){
            if(book.getGenre().equals(genre)){
                list.add(book);
            }
        }
        return list;
    }
}

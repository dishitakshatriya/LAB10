package com.example.BookService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class BookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

}

@Component
class ItemInitializer implements CommandLineRunner{
    private final IBookRepo repo;
    public ItemInitializer(IBookRepo _repo) {
        this.repo = _repo;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i=0; i<=5; i++) {
            Book book = new Book();
            book.setName("Book" + i);
            book.setAuthor("Author" + i);
            book.setGenre("Genre" + i);
            
            repo.save(book);
        }
        
        repo.findAll().forEach(System.out::println);
    }
}
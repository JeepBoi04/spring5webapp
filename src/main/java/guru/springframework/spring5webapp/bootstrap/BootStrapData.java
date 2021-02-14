package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started Debug - In");
        Author sk = new Author("King","Stephen");
        Book bob = new Book("Bag of Bones", "123456789");
        Publisher pub = new Publisher("CP Publishing","123 Main Steet","AnyTown USA","MI","49111");
        publisherRepository.save(pub);

        System.out.println("Started Debug - After news");

        sk.getBooks().add(bob);
        authorRepository.save(sk);

        bob.getAuthors().add(sk);
        bob.setPublisher(pub);
        bookRepository.save(bob);


        Book carrie = new Book("Carrie","987654321");
        carrie.getAuthors().add(sk);
        carrie.setPublisher(pub);

        bookRepository.save(carrie);


        System.out.println("Books in Repo: " + bookRepository.count());
        System.out.println("Books in Pub Repo: " + pub.getBooks().size());

    }
}

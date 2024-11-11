

package com.baeldung.web.controller;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.baeldung.model.Book;
import com.baeldung.persistence.BookRepository;
import com.baeldung.web.error.Checks;
import com.baeldung.web.resource.BookResource;
import com.baeldung.web.resource.CartResource;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.baeldung.model.Cart;
import com.baeldung.web.resource.NewBookResource;

@ExtendWith(MockitoExtension.class)
public class CartControllerAddBookToCartTest {
    @InjectMocks
    private CartController cartController;
    @Mock
    private BookRepository bookRepo;
    @Mock
    private Book bookToAdd;
    @Mock
    private BookResource bookResource;
    @Mock
    private CartResource cartResource;
    @Test
    @Tag("valid")
    public void testAddValidBookToCart() {
        when(bookResource.getBook()).thenReturn(bookToAdd);
        when(bookToAdd.getIsbn()).thenReturn("1234567890");
        when(bookRepo.findByIsbn(bookToAdd.getIsbn())).thenReturn(bookToAdd);
        CartResource result = cartController.addBookToCart(bookResource);
        assertNotNull(result);
        verify(bookRepo, times(1)).findByIsbn(bookToAdd.getIsbn());
    }
    @Test
    @Tag("invalid")
    public void testAddInvalidIsbnBookToCart() {
        when(bookResource.getBook()).thenReturn(bookToAdd);
        when(bookToAdd.getIsbn()).thenReturn("invalidIsbn");
        when(bookRepo.findByIsbn(bookToAdd.getIsbn())).thenReturn(null);
        assertThrows(EntityNotFoundException.class, () -> {
            cartController.addBookToCart(bookResource);
        });
        verify(bookRepo, times(1)).findByIsbn(bookToAdd.getIsbn());
    }
    @Test
    @Tag("boundary")
    public void testAddNullBookToCart() {
        when(bookResource.getBook()).thenReturn(null);
        assertThrows(NullPointerException.class, () -> {
            cartController.addBookToCart(bookResource);
        });
    }
    @Test
    @Tag("boundary")
    public void testAddBookToFullCart() {
        // Assuming that the cart can only hold 10 books
        when(cartResource.getBooks().size()).thenReturn(10);
        when(bookResource.getBook()).thenReturn(bookToAdd);
        when(bookToAdd.getIsbn()).thenReturn("1234567890");
        when(bookRepo.findByIsbn(bookToAdd.getIsbn())).thenReturn(bookToAdd);
        assertThrows(IllegalStateException.class, () -> {
            cartController.addBookToCart(bookResource);
        });
        verify(bookRepo, times(1)).findByIsbn(bookToAdd.getIsbn());
    }
}
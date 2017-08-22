package com.yocool;

import com.yocool.model.Book;
import com.yocool.model.Customer;
import com.yocool.repo.BookRepository;
import com.yocool.repo.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TBlogApplication.class)
@WebAppConfiguration
public class TBlogApplicationTests {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void contextLoads() {
		Book book = new Book("精通Spring  Boot",
				"ISBN12316663",
				"q",
				666);
		bookRepository.save(book);
	}

	@Test
	public void testSelect(){
		List<Book> bookList=bookRepository.findAll();
		Assert.notNull(bookList);
		for(Book book:bookList){
			System.out.println(book.toString());
		}
	}

	@Test
	public void testCustomerSelect(){
		List<Customer> customers=customerRepository.findAll();
		Assert.isTrue(customers!=null&&customers.size()>0);
		customers.forEach(s->{
			System.out.println(s.getFirstName()+","+s.getLastName());
		});
	}


}

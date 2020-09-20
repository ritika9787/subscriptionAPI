package com.gymondo.rest.subscriptionAPI.repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.gymondo.rest.subscriptionAPI.entity.Product;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {
	
	@Autowired
    private ProductRepository productRepo;
    @Autowired
    private TestEntityManager entityManager;

	@Test
	@Rollback(false)
	public void testFetchAllProducts() {
		entityManager.persist(new Product("Baby Shampoo", "Cetaphil", 10.5F));
	     
	    List<Product> products = (List<Product>) productRepo.findAll();
	    assertThat(products).size().isGreaterThan(0);
	}
	
	@Test
	@Rollback(false)
	public void testFetchSingleProduct() {
		entityManager.persist(new Product("Baby Shampoo", "Cetaphil", 10.5F));
	     
	    Product product = productRepo.findOne(1);
	    assertThat(product.getName()).isEqualTo("Baby Shampoo");
	}

}

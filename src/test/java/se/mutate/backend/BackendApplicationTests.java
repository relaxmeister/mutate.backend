package se.mutate.backend;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import se.mutate.backend.model.formdata.FormData;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/spring/app-config.xml")
@SpringBootTest
class BackendApplicationTests {

	@Autowired
	private Validator validator;

	@Test
	void testValidateFormData() {
		FormData formData = new FormData();

		formData.setName("JonformData.setLastname(\"Hallström\")as");
		formData.setLastname("Hallström");
		formData.setCity("Stockholm");
		formData.setEmail("baskdas");

		BindingResult errors = new BeanPropertyBindingResult(formData, "data");
		validator.validate(formData, errors); // populerar errors



		assertNotNull(formData.getName());
		assertEquals("Jonas", formData.getName());
		//assertThat(channel.getName()).isEqualTo(CHANNEL_NAME);
		System.out.println("-----");
		System.out.println(errors.getAllErrors().size());
		System.out.println("-----");
		/*Order order = new Order();
		order.setCustomerName("a");
		order.setPhoneNumber("1234");
		order.setAddress("b");
		BindingResult errors = new BeanPropertyBindingResult(order, "order");
		validator.validate(order, errors);
		assertEquals("Invalid customer name: a", getValidationErrorMessage(errors, "customerName"));
		assertEquals("phone number entered [1234] is invalid. It must have at least 10 digits", getValidationErrorMessage(errors, “phoneNumber”));
		assertEquals("Invalid size for input: b", getValidationErrorMessage(errors, “address”));*/

	}

	private String getValidationErrorMessage(BindingResult result, String field) {

		if (result.hasErrors()) {
			FieldError fieldError = result.getFieldError(field);
			return fieldError.toString();
		}
		return "";

	}
}

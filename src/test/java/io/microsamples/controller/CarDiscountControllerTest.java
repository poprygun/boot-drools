package io.microsamples.controller;

import io.microsamples.model.Car;
import io.microsamples.service.DealershipService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CarDiscountControllerTest {

    @Mock
    private DealershipService dealershipService;


    @Test
    public void shouldRespondWithDiscount(){

        Car expected = new Car();
        int discount = 7;
        expected.setDiscount(discount);
        when(dealershipService.getCarDiscount(any(Car.class))).thenReturn(expected);

        given().standaloneSetup(new CarDiscountController(dealershipService))
                .queryParam("brand", "ford")
                .when()
                .get("getDiscount")
//                .prettyPrint();
                .then()
                .assertThat()
                .body("fullForm", is(not(isEmptyString())))
                .body("discount", equalTo(discount));
    }

}
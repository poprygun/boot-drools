package io.microsamples.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.microsamples.model.Car;
import io.microsamples.service.DealershipService;

@RestController
public class CarDiscountController {

	private final DealershipService dealershipService;

	@Autowired
	public CarDiscountController(DealershipService dealershipService) {
		this.dealershipService = dealershipService;
	}

	@RequestMapping(value = "/getDiscount", method = RequestMethod.GET, produces = "application/json")
	public Car getDiscounts(@RequestParam(required = true) String brand) {

		Car car = new Car();

		car.setBrand(brand);

        return dealershipService.getCarDiscount(car);
	}

}

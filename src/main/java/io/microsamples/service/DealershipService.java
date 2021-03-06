package io.microsamples.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.microsamples.model.Car;

@Service
public class DealershipService {

	private final KieContainer kieContainer;

	@Autowired
	public DealershipService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}

	public Car getCarDiscount(Car product) {
		KieSession kieSession = kieContainer.newKieSession("rulesSession");
		kieSession.insert(product);
		kieSession.fireAllRules();
		kieSession.dispose();
		return product;
	}
}
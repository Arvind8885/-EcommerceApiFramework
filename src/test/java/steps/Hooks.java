package steps;

import io.cucumber.java.After;

public class Hooks {

	@After
	public void afterHook() throws InterruptedException {
		Thread.sleep(500);
		System.out.println("-------------------------------------------------------------------------------------------------");
	}

}

package demo;

import java.io.Serializable;

public class Order implements Serializable {

	float price;

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	
}

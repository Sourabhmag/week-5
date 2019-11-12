package springCore;

import org.springframework.stereotype.Component;

@Component
public class Car implements Vehicle {
	private int id;
	private String name;
	private int price;

	@Override
	public void drive() {
		// TODO Auto-generated method stub
		System.out.println("driving car");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", name=" + name + ", price=" + price + "]";
	}

}

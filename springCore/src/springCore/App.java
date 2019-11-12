package springCore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import utility.Util;

public class App {

	public static void main(String[] args) {
		ApplicationContext context  = new ClassPathXmlApplicationContext("spring.xml");
		Vehicle car = (Vehicle) context.getBean("car");
		//Vehicle bike = (Vehicle)context.getBean("vehicle");
		
		Bike bike = (Bike)context.getBean("bike");
		System.out.println("Enter id of car");
		car.setId(Util.readInt());
		
		System.out.println("Enter Name of car");
		car.setName(Util.readString());
		
		System.out.println("Enter price of car");
		car.setPrice(Util.readInt());
		
//		System.out.println("Enter id of bike");
//		bike.setId(Util.readInt());
//		
//		System.out.println("Enter Name of bike");
//		bike.setName(Util.readString());
//		
//		System.out.println("Enter price of bike");
//		bike.setPrice(Util.readInt());
		
		car.drive();
		System.out.println("Car id :"+car.getId());
		System.out.println("Car Name :"+car.getName());
		System.out.println("Car price :"+car.getPrice());
		car.toString();
		
		System.out.println();
		
		bike.drive();
		System.out.println("bike id :"+bike.getId());
		System.out.println("bike Name :"+bike.getName());
		System.out.println("bike price :"+bike.getPrice());
		bike.toString();
		
	}

}

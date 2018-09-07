package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

interface Drawable {
	public String draw(String name,int width,int height);
	//public void draw1();
}
interface Paint{
	public void withPaint();
	public void withoutPaint();
}
public class LambdaExpression {

	public static void main(String args[]) {

		Drawable dr = (name,width,height) -> {
			return "Draw "+name+ " Width "+width+" Height "+height;
		};
		
		System.out.println(dr.draw("Circle",1,1));
		
		List<String> list = new ArrayList();
		list.add("raghu");
		list.add("ram");
		list.add("reddy");
		
		list.forEach(
		  (n)->System.out.println(n)
		);
		
		Paint p = new Paint() {
			
			public void withPaint() {
				System.out.println("With Paint");
			}
			
			public void withoutPaint() {
				System.out.println("Without Paint");
			}
			
		};
		
		p.withoutPaint();
		p.withPaint();
		

	}
}

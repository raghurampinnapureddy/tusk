package com.example.demo.samples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListToMap {
	public static void main(String args[]) {
		
		List<Student> list = new ArrayList();
		Student st1 = new Student(1,"Raghu");
		Student st2 = new Student(2,"Ram");
		Student st3 = new Student(3,"Reddy");
		Student st4 = new Student(3,"Reddy");
		
		list.add(st1);
		list.add(st2);
		list.add(st3);
		list.add(st4);
			
		
		System.out.println(listToMapJava7Below(list));
		System.out.println(listToMapJava8(list));
		
	}
	
	public static Map<Integer,String> listToMapJava7Below(List<Student> students) {
		
		 Map<Integer,String> studentMap = new HashMap<Integer,String>();
		 
		 for(Student student:students) {
			 studentMap.put(student.getId(), student.getName());
		 }
		
		 return studentMap;
	}
	
	public static Map<Integer,String> listToMapJava8(List<Student> students) {
		
		Map<Integer,String> studentMap = students.stream().collect(Collectors.toMap(Student::getId,Student::getName,(oldValue,newValue)->oldValue,LinkedHashMap::new));
		
		 return studentMap;
	}

}

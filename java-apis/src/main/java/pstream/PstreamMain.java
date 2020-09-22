package pstream;

import java.util.ArrayList;
import java.util.List;

public class PstreamMain {

	public static void main(String[] args) {
		List<Student> ss=new ArrayList<Student>();
		for(int i=1;i<1000000;i++){
			ss.add(new Student(Integer.toString(i),(int)(Math.random()*100)));
		}
		long b=System.currentTimeMillis();
//		double ave=ss.parallelStream().mapToInt(s->s.score).average().getAsDouble();
		double ave=ss.stream().mapToInt(s->s.score).average().getAsDouble();
		
		long e=System.currentTimeMillis();
		System.out.println("cal spend time:"+(e-b)+"ms");
		System.out.println(ave);
	}

}

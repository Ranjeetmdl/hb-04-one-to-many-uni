package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entities.Course;
import com.luv2code.hibernate.entities.Instructor;
import com.luv2code.hibernate.entities.InstructorDetail;
import com.luv2code.hibernate.entities.Review;

public class CreateCourseAndReviewsDemo {

	public static void main(String[] args) {

		//create the SessionFactory
		SessionFactory factory=new Configuration()
				               .configure("hibernate.cfg.xml")
				               .addAnnotatedClass(Instructor.class)
				               .addAnnotatedClass(InstructorDetail.class)
				               .addAnnotatedClass(Course.class)
				               .addAnnotatedClass(Review.class)
				               .buildSessionFactory();
		//create the session 
		Session session = factory.getCurrentSession();
		
		try {
	
			//start/begin a transaction
			session.beginTransaction();
			
		    //create a course
			Course theCourse = new Course("The Piano-Ultimate piano class");
			
			//add some reviews to the course
			theCourse.addReviews(new Review("Great course..loved it!!"));
			theCourse.addReviews(new Review("what a dumb course...instructor doesn't "
					+ "know about the piano!!"));
			theCourse.addReviews(new Review("cool course..well structured!!"));
			
			//save the course
			System.out.println("\nsaving course :"+theCourse);
			System.out.println("\nand associated reviews :"+theCourse.getReviews());
			session.save(theCourse);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			factory.close();
		}
	}

}

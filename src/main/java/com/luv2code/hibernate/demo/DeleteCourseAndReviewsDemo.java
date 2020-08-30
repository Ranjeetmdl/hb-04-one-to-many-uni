package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entities.Course;
import com.luv2code.hibernate.entities.Instructor;
import com.luv2code.hibernate.entities.InstructorDetail;
import com.luv2code.hibernate.entities.Review;

public class DeleteCourseAndReviewsDemo {

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
			
		   //get the course
			int theId=10;
			Course theCourse = session.get(Course.class, theId);
			
			//delete the course
			System.out.println("\nDeleting Course :"+theCourse);
			System.out.println("\nAssociated Revies :"+theCourse.getReviews());
			session.delete(theCourse);
			
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

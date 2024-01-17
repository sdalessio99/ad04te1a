package Ejercicios;

import java.time.LocalDate;

import Entidades.Address;
import Entidades.Student;
import Entidades.Tuition;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Crear {

	/**
	 * 1. OneToOne unidireccional entre entidades Student y Tuition (matrícula)
	 */
	public static void main(String[] args) {

		// sustituir Session por entityManager
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ud4");
		EntityManager entityManager = factory.createEntityManager();
		
		
		
		try {			
			// crea un objeto Student
			System.out.println("Creando un nuevo objeto Student con su dirección y matrícula (tuition)");
			Student student = createStudent();
			Tuition tuition = new Tuition();
			tuition.setFee(4000.00);
			student.setTuition(tuition);		
			
			// comienza la transacción
			entityManager.getTransaction().begin();
			
			// guarda el objeto Student
			System.out.println("Guardando el estudiante...");
		
			//guarda el Student y con CascadeType.ALL guarda también el Tuition
			entityManager.persist(student);
			
			// hace commit de la transaccion
			entityManager.getTransaction().commit();
					
			System.out.println("Hecho!");
		}
		catch ( Exception e ) {
			// rollback ante alguna excepción
			System.out.println("Realizando Rollback");
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
		finally {
			entityManager.close();
			entityManager.close();
		}
	}
	private static Student createStudent() {
		Student tempStudent = new Student();
		Address tempAddress = new Address();
		
		tempStudent.setFirstName("Iñaki");
		tempStudent.setLastName("Laspiur");
		tempStudent.setEmail("ilaspiur@birt.eus");
		tempStudent.getPhones().add("687123456");
		tempStudent.getPhones().add("699212345");
		tempStudent.setBirthdate(LocalDate.parse("1985-04-04"));
		tempAddress.setAddressLine1("Burdin kale 8");
		tempAddress.setAddressLine2("1A");
		tempAddress.setCity("Zarautz");
		tempAddress.setZipCode("20080");
		tempStudent.setAddress(tempAddress);
		return tempStudent;		
	}
}
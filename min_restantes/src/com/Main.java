package com;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		/* 
		 * Crear un programa en Java que realice lo siguiente:
	Debe solicitar al usuario un día de la semana (de lunes a viernes).
	Debe solicitar al usuario una hora (horas y minutos).
	Debe calcular cuantos minutos faltan para el fin de semana. Se considera que el fin de semana comienza el viernes a las 15:00 hrs.
	Debe mostrar el resultado por consola.
	Debe ser capaz de identificar si los datos de entrada son válidos (día de la semana válido, hora válida, minutos válidos), en caso contrario debe mandar un mensaje de error y volver a solicitar los datos.
	Prueba tu programa con las siguientes entradas para asegurarte que funcione correctamente:
	lunes
	14
	30
	Faltan 5730 minutos para el fin de semana.
	Entradas: Lunes, 14:30 hrs
	Salida:
	Pruébalo con las combinaciones que se te ocurran.

		 */
		
		
		Scanner scanner = new Scanner (System.in);
		
		DayOfWeek day = null;
		LocalTime time = null;
		
		//Solicitar y validar que no sean campos vacíos y que sea un día válido	
		do {
		System.out.println("Enter the day of week: ");
		String dayInput = scanner.nextLine();
		
		try { 
			day = DayOfWeek.valueOf(dayInput.toUpperCase());
		} catch (IllegalArgumentException e) {
			System.out.println("The data entered is not a valid day of the week. Please enter it again.");
			continue;
		}
		
		
		
		// Solicitar y validar formato de hora
		System.out.println("Enter the time in hh:mm format:  ");
		String timeInput = scanner.nextLine();
		
		try {
			time = LocalTime.parse(timeInput, DateTimeFormatter.ofPattern("HH:mm"));
		} catch (Exception e) {
			System.out.println("Time format is not valid. Please enter it again");
			continue;
		}
		
		break;
		
		} while (true);
		
		
		//Definir variables para separar el dato ingresado en horas y minutos
		int hour = time.getHour();
		int min = time.getMinute();
		
		//Fecha y hora actual
		LocalDateTime actualDateTime = LocalDateTime.now();
		
		//Próximo viernes
		LocalDateTime nextFriday = actualDateTime.with(TemporalAdjusters.next(DayOfWeek.FRIDAY))
				.withHour(15).withMinute(0).withSecond(0).withNano(0);
		
		//Fecha ingresada
		LocalDateTime inputDate = actualDateTime.with(TemporalAdjusters.previousOrSame(day))
				.withHour(hour).withMinute(min).withSecond(0).withNano(0);
		
		//Cálculo entre la fecha ingresada y el próximo viernes (Fin de semana)
		long nextWeekend = java.time.Duration.between(inputDate, nextFriday).toMinutes();
		
		System.out.println( nextWeekend + " minutes left until the weekend");
		//System.out.println("hora actual " + actualDateTime);
		//System.out.println("Viernes " + nextFriday);
		//System.out.println("ingreso" + inputDate);


	}

}

package com.lovelacetecnologia.util;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormatadoraNumerosDatasHorasUtil {

	public static String date(LocalDate date) {

		DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		String dataFormatada = dataFormat.format(date);

		return dataFormatada;
	
	}
	
	
	public static String time(LocalTime time) {

		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm"); 

		String horaFormatada = timeFormat.format(time);

		return horaFormatada;

	}
	
	
	public static String number(Double number) {

		Locale locale = new Locale("pt","BR");
		
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
		
		String numeroFormatado = numberFormat.format(number);

		return numeroFormatado;

	}

}

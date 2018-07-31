package com.lovelacetecnologia.util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import com.lovelacetecnologia.model.EnviarEmail;

public class EnviadorDeEmailUtil {

	public void enviarSemAnexo(EnviarEmail enviarEmail) throws EmailException {

		Email email = new SimpleEmail();

		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("teste@teste.com ", "teste"));
		email.setSSLOnConnect(true);
		email.setFrom("teste@teste.com ");
		email.setSubject(enviarEmail.getAssunto());
		email.setMsg(enviarEmail.getMensagem());
		email.addTo(enviarEmail.getPara());
		email.send();
		

	}

	public void enviarEmailComAnexo(EnviarEmail enviarEmail) throws EmailException {
		
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath("docs/images.jpeg"); //caminho da imagem
		attachment.setName("Seu Mac Book AIR Está Consertado ");
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		
		MultiPartEmail email = new MultiPartEmail();
		
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("teste@teste.com ", "teste"));
		email.setSSLOnConnect(true);
		email.setFrom("teste@teste.com ");
		email.setSubject(enviarEmail.getAssunto()); 
		email.setMsg(enviarEmail.getMensagem()); 
		email.addTo(enviarEmail.getPara()); 
		email.attach(attachment); 
		email.send();
		
	}

}

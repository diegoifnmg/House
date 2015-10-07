/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.diegoalvescosta.tcc.Controller;


import java.net.MalformedURLException;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author diego
 */
public class CommonsMail {
	
	public CommonsMail() throws EmailException, MalformedURLException {
		enviaEmailSimples();
	}
	
	/**
	 * envia email simples(somente texto)
	 * @throws EmailException
	 */
	public void enviaEmailSimples() throws EmailException {
		
		SimpleEmail email = new SimpleEmail();
		email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
		email.addTo("comercialdiego@hotmail.com", "Diego"); //destinatário
		email.setFrom("diego.ifnmg@gmail.com", "Eu"); // remetente
		email.setSubject("Teste -> Email simples"); // assunto do e-mail
		email.setMsg("Teste de Email utilizando commons-email"); //conteudo do e-mail
		email.setAuthentication("diego.ifnmg@gmail.com", "diegoa1bdf");
		email.setSmtpPort(465);
		email.setSSL(true);
		email.setTLS(true);
		email.send();	
	}
	
	/**
	 * @param args
	 * @throws EmailException 
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws EmailException, MalformedURLException {
		new CommonsMail();
                
	}

}

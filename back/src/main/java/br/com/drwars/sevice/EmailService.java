package br.com.drwars.sevice;


import br.com.drwars.entites.Oferta;
import br.com.drwars.entites.Usuario;
import br.com.drwars.exception.CampoInvalidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.logging.Logger;

@Service
public  class EmailService {

    private static Logger logger = Logger.getLogger(EmailService.class.getName());


    @Autowired
    private JavaMailSender mailSender;
    
    @PostConstruct
    private void init() {
    	/*
        try {
	        MimeMessage mimeMessage = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
	        helper.setSubject("Servidor COMETA iniciado");
	        helper.setText("Servidor Iniciado",true);
	        helper.setTo("magno.mabreu@gmail.com");
	        helper.setFrom("cometa@cmabreu.com.br");
            mailSender.send(mimeMessage);
            System.out.println( "Email enviado com sucesso!" );
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println( "Erro ao enviar email.");
        } 
        */   	
    }


    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }


    public void sendEmailPassword(Usuario usuario, String senha) throws MessagingException, CampoInvalidoException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String texto = "<b>Usuario:</b> "+usuario.getUsername()+"</br>  <b>Senha:</b> "+senha;
        helper.setSubject("Usuário e senha de acesso ao sistema Biometano Trade ");
        helper.setText(texto,true);
        helper.setTo(usuario.getEmail());
        helper.setFrom("biometano@drwarslabs.com");
        try {
            mailSender.send(mimeMessage);
            System.out.println( "Email enviado com sucesso!" );
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println( "Erro ao enviar email.");
            throw  new CampoInvalidoException("Dados incorretos! Não foi possível enviar o e-mail.");
        }
    }

    public void sendCriacaoOferta(Usuario usuario, Oferta oferta) throws MessagingException, CampoInvalidoException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String texto = "<b>Uma nova oferta foi cadastrado pela empresa "+oferta.getEmpresa().getNomeFatasia()+" no sistema  </b> ";
        helper.setSubject("Sistema Biometano Trade ");
        helper.setText(texto,true);
        helper.setTo(usuario.getEmail());
        helper.setFrom("biometano@drwarslabs.com");
        try {
            mailSender.send(mimeMessage);
            System.out.println( "Email enviado com sucesso!" );
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println( "Erro ao enviar email.");
            //throw new CampoInvalidoException("Não foi possível enviar o e-mail");

        }
    }


    public void sendNovaRodadaOferta(Usuario usuario, Oferta oferta) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String texto = "<b>Uma nova rodada de negociação foi cadastrado pela empresa  </b> ";
        helper.setSubject("Sistema Biometano Trade ");
        helper.setText(texto,true);
        helper.setTo(usuario.getEmail());
        helper.setFrom("biometano@drwarslabs.com");
        try {
            mailSender.send(mimeMessage);
            System.out.println( "Email enviado com sucesso!" );
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println( "Erro ao enviar email.");
        }
    }

}

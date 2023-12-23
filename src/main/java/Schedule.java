import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import Parceiro.Parceiro;
import Documento.Documento;
import Usuario.Usuario;
import NvAvisos.NvAvisos;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import Util.Util;



/**

 * Classe para manipular a execução de tarefas agendadas automaticamentes

 * @author Roberto

 * @version 1.0

 */

public class Schedule {



 Timer timer;

 /**

  * Método para iniciar a execução das tarefas.

  */

 public void iniciar() {



     timer = new Timer();

     //Executa tarefa a cada 24 horas a partir da primeira

     //       timer.schedule(new tarefasDiarias(),

     //        0,

     //        1 * 1000 * 60 * 60 * 24);



     //Executa tarefa todo dia as 6 da manha

     Calendar calendar = Calendar.getInstance();

     calendar.set(Calendar.HOUR_OF_DAY, 01);

     calendar.set(Calendar.MINUTE, 11);

     calendar.set(Calendar.SECOND, 0);

     Date time = calendar.getTime();

     timer.schedule(new tarefasDiarias(), time);

 }

 /**

  * Método para interromper a execução das tarefas.

  */

 public void parar() {

     timer.cancel();

 }

 /**

  * Método que contém as tarefas agendadas que serão executadas.

  */

 class tarefasDiarias extends TimerTask {

    @Override
     public void run() {

              Date data = new Date();
              Locale local = new Locale("pt", "BR");
	      DateFormat dateFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);
              Properties props = new Properties();
              props.put("mail.smtp.host", "smtp.gmail.com");
              props.put("mail.transport.protocol", "smtp");
     props.put("mail.smtp.socketFactory.port", "587");
     
     props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
     props.put("mail.smtp.auth", "true");
     props.put("mail.smtp.port", "587");
     
     props.put("mail.smtp.starttls.enable", "true");
     
     Session session = Session.getInstance(props, new Authenticator()
     {
       protected PasswordAuthentication getPasswordAuthentication() {
         return new PasswordAuthentication("robertinhosouzapai@gmail.com", "mjnm teob cdaj gvcx");
 
       }

     });
     session.setDebug(true);
     
    /*     */     
     try
     {
       Message message = new MimeMessage(session);
       message.setFrom(new InternetAddress("comercial@suaempresa.com.br", "ENG NAV"));
       
       Address[] toUser = InternetAddress.parse("roberto.souza.si@outlook.com");
       
 
       message.setRecipients(Message.RecipientType.TO, toUser);
       message.setSubject("Comunicado - Aviso de Abertura da Janela ");
       message.setText("Venho através desta, comunicar que a janela da  1ª Vistoria Anual"  + 
       " do Certificado de Segurança da Navegação Nº ENGNAV0321" + " está programada para ocorrer até 31/12/2023" );

       Transport.send(message);

       
       System.out.println("Feito!!!");
     }
     catch (MessagingException e) {
       throw new RuntimeException(e);
     } catch (UnsupportedEncodingException ex) {
       Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
     }
   }
         
         
         
         
         
         //Aqui ficam as tarefas que vão ser executadas...

     }



}

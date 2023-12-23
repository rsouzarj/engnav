/*     */ package Util;

/*     */
 /*     */ import Parceiro.Parceiro;
import Documento.Documento;
/*     */ import Usuario.Usuario;
import NvAvisos.NvAvisos;
import com.google.gson.Gson;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
import java.net.URLConnection;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.SQLException;
import java.text.DateFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
import java.time.LocalDate;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
import java.util.Locale;
/*     */ import java.util.Properties;
/*     */ import java.util.UUID;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.mail.Address;
/*     */ import javax.mail.Authenticator;
import javax.mail.BodyPart;
/*     */ import javax.mail.Message;
/*     */ import javax.mail.Message.RecipientType;
/*     */ import javax.mail.MessagingException;
/*     */ import javax.mail.Multipart;
/*     */ import javax.mail.PasswordAuthentication;
/*     */ import javax.mail.Session;
/*     */ import javax.mail.Transport;
/*     */ import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
/*     */ import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/*     */ public class Util /*     */ {

    /*     */ public static final String raizArquivos = "c:\\temp\\erp\\";
    /*  50 */    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    /*  51 */    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    /*     */
 /*     */ public static void main(String[] args) {
        /*  54 */ Util util = new Util();
        /*  55 */ PastaDisco pasta = PastaDisco.Logo;
        /*  56 */ System.out.println("Logo" + pasta);
        /*     */    }

    /*     */
 /*     */ public static String gerarChave() {
        /*  60 */ UUID uuid = UUID.randomUUID();
        /*  61 */ return uuid.toString();
        /*     */    }

    /*     */
 /*     */ public static void executarSql(String pSql) /*     */ {
        /*     */ try {
            /*  67 */ Conexao conexao = new Conexao();
            /*     */
 /*  69 */ Connection conn = Conexao.getConnection();
            /*  70 */ conn.close();
            /*  71 */ conn = Conexao.getConnection();
            /*     */
 /*     */
 /*     */
 /*  75 */ PreparedStatement ps = conn.prepareStatement(pSql);
            /*  76 */ ps.execute();
            /*     */        } /*     */ catch (SQLException ex) {
            /*  79 */ Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            /*     */        }
        /*     */    }

    /*     */
 /*     */ public static String Consultar(String urlT) throws IOException /*     */ {
        /*  85 */ InputStream is = null;
        /*  86 */ BufferedReader reader = null;
        /*     */ try /*     */ {
            /*  89 */ URL url = new URL(urlT);
            /*  90 */ HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            /*  91 */ conn.setReadTimeout(60000);
            /*  92 */ conn.setConnectTimeout(15000);
            /*  93 */ conn.setRequestMethod("GET");
            /*  94 */ conn.setDoInput(true);
            /*  95 */ conn.connect();
            /*  96 */ conn.getResponseCode();
            /*     */
 /*  98 */ is = conn.getInputStream();
            /*  99 */ reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            /*     */
 /* 101 */ StringBuilder sb = new StringBuilder();
            /* 102 */ String line = null;
            /*     */
 /*     */
 /* 105 */ while ((line = reader.readLine()) != null) {
                /* 106 */ sb.append(line);
                /*     */            }
            /*     */
 /* 109 */ return sb.toString();
            /*     */        } finally {
            /* 111 */ if (is != null) {
                /*     */ try {
                    /* 113 */ is.close();
                    /*     */                } catch (IOException e) {
                    /* 115 */ e.printStackTrace();
                    /*     */                }
                /*     */            }
            /*     */
 /* 119 */ if (reader != null) {
                /*     */ try {
                    /* 121 */ reader.close();
                    /*     */                } catch (IOException e) {
                    /* 123 */ e.printStackTrace();
                    /*     */                }
                /*     */            }
            /*     */        }
        /*     */    }

    /*     */
 /*     */ public static String buscarConteudoDentroJson(String pStringJson, String pChave) {
        /* 130 */ int pInicial = pStringJson.indexOf("\"" + pChave + "\": \"") + pChave.length() + 10;
        /* 131 */ int pFinal = pInicial;
        /*     */
 /* 133 */ for (int i = 0; i < 50; i++) {
            /* 134 */ pFinal++;
            /* 135 */ if (pStringJson.substring(pFinal, pFinal + 1).equals("\"")) {
                /* 136 */ return pStringJson.substring(pInicial, pFinal);
                /*     */            }
            /*     */        }
        /*     */
 /* 140 */ return null;
        /*     */    }

    public static boolean enviarPorEmail(String recebedor, String assunto, Multipart corpo, Multipart pasta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*     */
 /*     */
    public Parceiro buscarDadosReceitaFederal(Parceiro pParceiro) throws IOException {
        if (pParceiro == null) {
            return null;
        }
        URL url = new URL("https://www.receitaws.com.br/v1/cnpj/" + pParceiro.getDocumento().replace(".", "").replace("-", "").replace("/", ""));

        URLConnection openConnection = url.openConnection();
        if (openConnection == null) {
            throw new IOException("Não foi possível conectar com o serviço de consulta de CNPJ receitaws");
        }
        InputStream is = openConnection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        Gson gson = new Gson();
        ReceitaWSCNPJ obj = gson.fromJson(reader, ReceitaWSCNPJ.class);
        pParceiro.setNome(obj.getNome());
        /* 157 */ pParceiro.setFantasia(obj.getFantasia());
        /* 158 */ pParceiro.setLogradouro(obj.getLogradouro());
        /* 159 */ pParceiro.setNumero(obj.getNumero());
        /* 160 */ pParceiro.setComplemento(obj.getComplemento());
        /* 161 */ pParceiro.setBairro(obj.getBairro());
        /* 162 */ pParceiro.setCidade(obj.getMunicipio());
        /* 163 */ pParceiro.setUf(obj.getUf());
        /* 164 */ pParceiro.setCep(obj.getCep());
        /* 165 */ pParceiro.setTelefone1(obj.getTelefone());
        /* 166 */ pParceiro.setEmail(obj.getEmail());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            pParceiro.setDataNascimento(formatter.parse(obj.getAbertura().trim().substring(0, 10)));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        /*     */
 /* 179 */ return pParceiro;
        /*     */    }

    public Documento buscarDadosDataJud(Documento pDocumento) throws IOException {
        if (pDocumento == null) {
            return null;
        }
        URL url = new URL("https://api-publica.datajud.cnj.jus.br/api_publica_tjrj/_search" + pDocumento.getCodigo().replace(".", "").replace("-", ""));

        URLConnection openConnection = url.openConnection();
        if (openConnection == null) {
            throw new IOException("Não foi possível conectar com o serviço de consulta do Datajud");
        }
        InputStream is = openConnection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        Gson gson = new Gson();
        Datajud obj = gson.fromJson(reader, Datajud.class);
        pDocumento.setAssunto(obj.getAssuntosnome());

//	      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//		try {
//			pParceiro.setDataNascimento(formatter.parse(obj.getAbertura().trim().substring(0, 10)));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
        /* 179 */ return pDocumento;
        /*     */    }

    /*     */
 /*     */ private static int calcularDigito(String str, int[] peso) {
        /* 183 */ int soma = 0;
        /* 184 */ for (int indice = str.length() - 1; indice >= 0; indice--) {
            /* 185 */ int digito = Integer.parseInt(str.substring(indice, indice + 1));
            /* 186 */ soma += digito * peso[(peso.length - str.length() + indice)];
            /*     */        }
        /* 188 */ soma = 11 - soma % 11;
        /* 189 */ return soma > 9 ? 0 : soma;
        /*     */    }

    /*     */
 /*     */ public static boolean validarCPF(String pCpf) {
        /* 193 */ String cpf = pCpf.replace(".", "").replace("-", "");
        /* 194 */ if ((cpf == null) || (cpf.length() != 11)) {
            /* 195 */ return false;
            /*     */        }
        /*     */
 /* 198 */ Integer digito1 = Integer.valueOf(calcularDigito(cpf.substring(0, 9), pesoCPF));
        /* 199 */ Integer digito2 = Integer.valueOf(calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF));
        /* 200 */ return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
        /*     */    }

    /*     */
 /*     */ public static boolean validarCNPJ(String pCnpj) {
        /* 204 */ String cnpj = pCnpj.replace(".", "").replace("/", "").replace("-", "");
        /* 205 */ if ((cnpj == null) || (cnpj.length() != 14)) {
            /* 206 */ return false;
            /*     */        }
        /*     */
 /* 209 */ Integer digito1 = Integer.valueOf(calcularDigito(cnpj.substring(0, 12), pesoCNPJ));
        /* 210 */ Integer digito2 = Integer.valueOf(calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ));
        /* 211 */ return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
        /*     */    }

    /*     */
 /*     */ public void copiarArquivoDisco(String pSeqEmpresa, PastaDisco pPastaDisco, String pNomeArquivo, InputStream in) {
        /*     */ try {
            /* 216 */ String caminho = "c:\\temp\\erp\\" + String.valueOf(pSeqEmpresa) + "\\" + pPastaDisco + "\\";
            /*     */
 /*     */
 /* 219 */ File diretorio = new File(caminho);
            /* 220 */ diretorio.mkdirs();
            /*     */
 /* 222 */ System.out.println(caminho);
            /* 223 */ OutputStream out = new FileOutputStream(new File(caminho + pNomeArquivo));
            /*     */
 /* 225 */ int read = 0;
            /* 226 */ byte[] bytes = new byte['Ѐ'];
            /* 227 */ while ((read = in.read(bytes)) != -1) {
                /* 228 */ out.write(bytes, 0, read);
                /*     */            }
            /*     */
 /* 231 */ in.close();
            /* 232 */ out.flush();
            /* 233 */ out.close();
            /*     */        } catch (IOException e) {
            /* 235 */ System.out.println(e.getMessage());
            /*     */        }
        /*     */    }

    /*     */
 /*     */ public boolean localizarArquivo(String pSeqEmpresa, String pNomeArquivo, PastaDisco pPastaDisco) {
        /* 240 */ boolean retorno = false;
        /* 241 */ String vNomeArquivo = pNomeArquivo;
        /*     */
 /* 243 */ int i = 0;
        /* 244 */ File arq = new File("c:\\temp\\erp\\" + pSeqEmpresa + "\\" + pPastaDisco + "\\");
        /* 245 */ File[] arquivos = arq.listFiles();
        /* 246 */ while (i != arquivos.length) {
            /* 247 */ if (arquivos[i].getName().equals(vNomeArquivo)) {
                /* 248 */ retorno = true;
                /*     */            }
            /* 250 */ i++;
            /*     */        }
        /*     */
 /* 253 */ return retorno;
        /*     */    }

    /*     */
 /*     */ public String NomeDoMes(int i, int tipo) {
        /* 257 */ String[] mes = {"janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro"};
        /*     */
 /*     */
 /*     */
 /* 261 */ if (tipo == 0) {
            /* 262 */ return mes[(i - 1)];
            /*     */        }
        /* 264 */ return mes[(i - 1)].substring(0, 3);
        /*     */    }

    /*     */
 /*     */
 /*     */ public String DataPorExtenso(String cidade, Date dt) /*     */ {
        /* 270 */ if (dt == null) {
            /* 271 */ return "";
            /*     */        }
        /*     */
 /* 274 */ int d = dt.getDate();
        /* 275 */ int m = dt.getMonth() + 1;
        /* 276 */ int a = dt.getYear() + 1900;
        /*     */
 /* 278 */ Calendar data = new GregorianCalendar(a, m - 1, d);
        /*     */
 /*     */
 /* 281 */ if (d < 10) {
            /* 282 */ return cidade + "0" + d + " de " + NomeDoMes(m, 0) + " de " + a + ".";
            /*     */        }
        /* 284 */ return cidade + d + " de " + NomeDoMes(m, 0) + " de " + a + ".";
        /*     */    }

    /*     */
 /*     */ public String DataToString(Date dt) /*     */ {
        /* 289 */ if (dt == null) {
            /* 290 */ return "__/__/____";
            /*     */        }
        /*     */
 /* 293 */ int d = dt.getDate();
        /* 294 */ int m = dt.getMonth() + 1;
        /* 295 */ int a = dt.getYear() + 1900;
        /*     */
 /* 297 */ String retorno = "";
        /*     */
 /* 299 */ if (d < 10) {
            /* 300 */ retorno = "0" + d;
            /*     */        } else {
            /* 302 */ retorno = "" + d;
            /*     */        }
        /*     */
 /* 305 */ if (m < 10) {
            /* 306 */ retorno = retorno + "/" + "0" + m;
            /*     */        } else {
            /* 308 */ retorno = retorno + "/" + m;
            /*     */        }
        /*     */
 /* 311 */ return retorno + "/" + a;
        /*     */    }

    /*     */
 /*     */ public static void enviarEmail(String pEmail, Usuario pUsuario) {
        /* 315 */ Properties props = new Properties();
        /*     */
 /*     */
 /*     */
 /* 319 */ props.put("mail.smtp.host", "smtp.gmail.com");
        /* 320 */ props.put("mail.transport.protocol", "smtp");
        /* 321 */ props.put("mail.smtp.socketFactory.port", "587");
        /*     */
 /* 323 */ props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        /* 324 */ props.put("mail.smtp.auth", "true");
        /* 325 */ props.put("mail.smtp.port", "587");
        /*     */
 /* 327 */ props.put("mail.smtp.starttls.enable", "true");
        /*     */
 /* 329 */ Session session = Session.getInstance(props, new Authenticator() /*     */ {
            /*     */ protected PasswordAuthentication getPasswordAuthentication() {
                /* 332 */ return new PasswordAuthentication("contato.robertinhodj@gmail.com", "llvpozyiftrswsyj");
                /*     */
 /*     */            }
            /*     */
 /*     */
 /*     */
 /* 338 */        });
        /* 339 */ session.setDebug(true);
        /*     */
 /*     */ try /*     */ {
            /* 343 */ Message message = new MimeMessage(session);
            /* 344 */ message.setFrom(new InternetAddress("suporte@connectisystem.com.br", "SUPORTE"));
            /*     */
 /* 346 */ Address[] toUser = InternetAddress.parse(pEmail);
            /*     */
 /*     */
 /* 349 */ message.setRecipients(Message.RecipientType.TO, toUser);
            /* 350 */ message.setSubject("CONNECTI Service - Recuperação de Senha ");
            /* 351 */ message.setText("ConnecTI informa: \n\n\n Seu usuário: " + pUsuario.getUsuario() + " \n Sua Senha: " + pUsuario.getSenha() + " \n\n\n ** Recomendamos a troca da senha após o recebimento desse e-mail.**");
            /*     */
 /*     */
 /*     */
 /* 355 */ Transport.send(message);
            /*     */
 /* 357 */ System.out.println("Feito!!!");
            /*     */        } /*     */ catch (MessagingException e) {
            /* 360 */ throw new RuntimeException(e);
            /*     */        } catch (UnsupportedEncodingException ex) {
            /* 362 */ Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            /*     */        }
        /*     */    }

    /*     */
 /*     */ public static boolean enviarEmailParceiro(String pEmail, Multipart pConteudo) /*     */ {
        /* 368 */ boolean retorno = false;
        /*     */
 /* 370 */ Properties props = new Properties();
        /*     */
 /*     */
 /*     */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.transport.protocol", "smtp");
        /* 321 */ props.put("mail.smtp.socketFactory.port", "587");
        /*     */
 /* 323 */ props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        /* 324 */ props.put("mail.smtp.auth", "true");
        /* 325 */ props.put("mail.smtp.port", "587");
        /*     */
 /* 327 */ props.put("mail.smtp.starttls.enable", "true");
        /*     */
 /* 329 */ Session session = Session.getInstance(props, new Authenticator() /*     */ {
            /*     */ protected PasswordAuthentication getPasswordAuthentication() {
                /* 332 */ return new PasswordAuthentication("robertinhosouzapai@gmail.com", "mjnm teob cdaj gvcx");
                /*     */
 /*     */            }
            /*     */
 /*     */
 /*     */
 /* 393 */        });
        /* 394 */ session.setDebug(true);
        /*     */
 /*     */ try /*     */ {
            /* 398 */ Message message = new MimeMessage(session);
            /*     */ try {
                /* 400 */ message.setFrom(new InternetAddress("suporte@Empresa.com.br", "Empresa"));
                /*     */            } /*     */ catch (UnsupportedEncodingException ex) {
                /* 403 */ Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                /*     */            }
            /*     */
 /* 406 */ Address[] toUser = InternetAddress.parse(pEmail);
            /*     */
 /*     */
 /* 409 */ message.setRecipients(Message.RecipientType.TO, toUser);
            /* 410 */ message.setSubject("EMPRESA - Pesquisa de Satisfação ");
            /*     */
 /* 412 */ message.setContent(pConteudo);
            /*     */
 /*     */
 /*     */
 /* 416 */ Transport.send(message);
            /*     */
 /* 418 */ System.out.println("Feito!!!");
            /* 419 */ retorno = true;
            /*     */        } /*     */ catch (MessagingException e) {
            /* 422 */ retorno = false;
            /* 423 */ throw new RuntimeException(e);
            /*     */        }
        /* 425 */ return retorno;
        /*     */    }

    /*     */
    public static boolean enviarEmailParceiro1(String pEmail, Multipart pConteudo) /*     */ {
        /* 368 */ boolean retorno = false;
        /*     */
 /* 370 */ Properties props = new Properties();
        /*     */
 /*     */
 /*     */
 /* 374 */ props.put("mail.smtp.host", "email-ssl.com.br");
        /* 375 */ props.put("mail.transport.protocol", "smtp");
        /*     */
 /* 377 */ props.put("mail.smtp.socketFactory.port", "465");
        /* 378 */ props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        /* 379 */ props.put("mail.smtp.auth", "true");
        /* 380 */ props.put("mail.smtp.port", "587");
        /*     */
 /* 382 */ props.put("mail.smtp.starttls.enable", "true");
        /*     */
 /* 384 */ Session session = Session.getInstance(props, new Authenticator() /*     */ {
            /*     */ protected PasswordAuthentication getPasswordAuthentication() {
                /* 387 */ return new PasswordAuthentication("contato@empresa.com.br", "Empresa");
                /*     */
 /*     */            }
            /*     */
 /*     */
 /*     */
 /* 393 */        });
        /* 394 */ session.setDebug(true);
        /*     */
 /*     */ try /*     */ {
            /* 398 */ Message message = new MimeMessage(session);
            /*     */ try {
                /* 400 */ message.setFrom(new InternetAddress("suporte@EMPRESA.com.br", "EMPRESA"));
                /*     */            } /*     */ catch (UnsupportedEncodingException ex) {
                /* 403 */ Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                /*     */            }
            /*     */
 /* 406 */ Address[] toUser = InternetAddress.parse(pEmail);
            /*     */
 /*     */
 /* 409 */ message.setRecipients(Message.RecipientType.TO, toUser);
            /* 410 */ message.setSubject("EMPRESA - Pesquisa de Satisfação ");
            /*     */
 /* 412 */ message.setContent(pConteudo);
            /*     */
 /*     */
 /*     */
 /* 416 */ Transport.send(message);
            /*     */
 /* 418 */ System.out.println("Feito!!!");
            /* 419 */ retorno = true;
            /*     */        } /*     */ catch (MessagingException e) {
            /* 422 */ retorno = false;
            /* 423 */ throw new RuntimeException(e);
            /*     */        }
        /* 425 */ return retorno;
        /*     */    }

    public NvAvisos enviarAviso(NvAvisos pCer) {
        MimeBodyPart textPart = new MimeBodyPart();
        Multipart mps = new MimeMultipart();
        Date data = new Date();
        Locale local = new Locale("pt", "BR");
        DateFormat dateFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.transport.protocol", "smtp");
        /* 321 */ props.put("mail.smtp.socketFactory.port", "587");
        /*     */
 /* 323 */ props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        /* 324 */ props.put("mail.smtp.auth", "true");
        /* 325 */ props.put("mail.smtp.port", "587");
        /*     */
 /* 327 */ props.put("mail.smtp.starttls.enable", "true");
        /*     */
 /* 329 */ Session session = Session.getInstance(props, new Authenticator() /*     */ {
            /*     */ protected PasswordAuthentication getPasswordAuthentication() {
                /* 332 */ return new PasswordAuthentication("robertinhosouzapai@gmail.com", "mjnm teob cdaj gvcx");
                /*     */
 /*     */            }

            /* 338 */        });
        /* 339 */ session.setDebug(true);
        /*     */
 /*     */ /*     */
 /*     */ try /*     */ {
            /* 343 */ Message message = new MimeMessage(session);
            /* 344 */ message.setFrom(new InternetAddress("comercial@suaempresa.com.br", "ENG NAV"));
            /*     */
 /* 346 */ Address[] toUser = InternetAddress.parse(pCer.getEmail());
            /*     */
            String pCerconteudo = "<b>" + pCer.getCliente() + "</b>,<br><br><br><br><br> Venho através desta, comunicar que a janela da <b>" + pCer.getArealiza()
                    + "</b>, do <b>Certificado de Segurança da Navegação Nº ENGNAV" + pCer.getIdentificacao() + "</b> está programada para ocorrer até <b>"
                    + dateFormat.format(pCer.getDataFinal())
                    + " </b><br><br> Solicitamos a gentileza de entrar em contato com nosso setor comercial"
                    + "<br><br><br><br><br>Agradecemos a preferência,<br><br><br><br>Atenciosamente,<br><br><br><br><b>EMPRESA</b><br/>";


            /* 349 */ message.setRecipients(Message.RecipientType.TO, toUser);
            /* 350 */ message.setSubject("Comunicado - Aviso de Abertura da Janela ");
            /* 351 */ message.setContent(pCerconteudo, "text/html");

            /* 355 */ Transport.send(message);

            /*     */
 /* 357 */ System.out.println("Feito!!!");
            /*     */        } /*     */ catch (MessagingException e) {
            /* 360 */ throw new RuntimeException(e);
            /*     */        } catch (UnsupportedEncodingException ex) {
            /* 362 */ Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            /*     */        }
        /*     */
        return null;
    }

    public Documento enviarOS(Documento pDocumento) {
        Date data = new Date();
        Locale local = new Locale("pt", "BR");
        DateFormat dateFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.transport.protocol", "smtp");
        /* 321 */ props.put("mail.smtp.socketFactory.port", "587");
        /*     */
 /* 323 */ props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        /* 324 */ props.put("mail.smtp.auth", "true");
        /* 325 */ props.put("mail.smtp.port", "587");
        /*     */
 /* 327 */ props.put("mail.smtp.starttls.enable", "true");
        /*     */
 /* 329 */ Session session = Session.getInstance(props, new Authenticator() /*     */ {
            /*     */ protected PasswordAuthentication getPasswordAuthentication() {
                /* 332 */ return new PasswordAuthentication("robertinhosouzapai@gmail.com", "mjnm teob cdaj gvcx");
                /*     */
 /*     */            }

            /* 338 */        });
        /* 339 */ session.setDebug(true);
        /*     */
 /*     */ /*     */
 /*     */ try /*     */ {
            /* 343 */ Message message = new MimeMessage(session);
            /* 344 */ message.setFrom(new InternetAddress("comercial@suaempresa.com.br", "ENG NAV"));
            /*     */
 /* 346 */ Address[] toUser = InternetAddress.parse("contato@engnavcertificadora.com");
            /*     */
 /*     */
 /* 349 */ message.setRecipients(Message.RecipientType.TO, toUser);
            /* 350 */ message.setSubject("OS " + pDocumento.getCodigo());
            /* 351 */ message.setText("Ordem de Serviço Nº  " + pDocumento.getCodigo()
                    + " foi gerada no sistema, será necessário fazer o agendamento da vistoria ");// + dateFormat.format(pCer.getDataFinal()));

            /* 355 */ Transport.send(message);

            /*     */
 /* 357 */ System.out.println("Feito!!!");
            /*     */        } /*     */ catch (MessagingException e) {
            /* 360 */ throw new RuntimeException(e);
            /*     */        } catch (UnsupportedEncodingException ex) {
            /* 362 */ Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            /*     */        }
        /*     */
        return null;

    }
    

       public static void getPegaDataAtual() {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");

        Calendar c = new GregorianCalendar(2004, 9, 2);

        System.out.println("Data: " + sd.format(c.getTime()));

        c.add(Calendar.DAY_OF_MONTH, 30);
        System.out.println("Sessenta dias depois: " + sd.format(c.getTime()));

        /* c.add(Calendar.DAY_OF_MONTH, -1); 
     System.out.println("Um dia antes: " + sd.format(c.getTime())); 
      
     c.add(Calendar.MONTH, -1); 
     System.out.println("Um mês antes: " + sd.format(c.getTime())); 
      
     c = new GregorianCalendar(2003, Calendar.MARCH, 3); 
     System.out.println("Data: " + sd.format(c.getTime())); 
      
     c.add(Calendar.DAY_OF_MONTH, -1); 
     System.out.println("Um dia antes: " + sd.format(c.getTime())); 
      
     c.add(Calendar.MONTH, -1); 
     System.out.println("Um mês antes: " + sd.format(c.getTime()));*/
    }

}

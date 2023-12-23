/*     */ package WSConsumir;
/*     */ 
/*     */ import com.github.kevinsawicki.http.HttpRequest;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.HashMap;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;

/*     */ public class WSUtil
/*     */ {
/*     */   public static String executarGet(String url)
/*     */   {
/*     */     try
/*     */     {
/*  29 */       HttpRequest req = HttpRequest.get(url);
/*     */       
/*  31 */       if (req.ok()) {
/*  32 */         return req.body();
/*     */       }
/*     */       
/*  35 */       return null;
/*     */     }
/*     */     catch (Exception e) {
/*  38 */       e.printStackTrace(); }
/*  39 */     return null;
/*     */   }
/*     */   
/*     */   public static String sendGet(String pUrl, String pMetodo)
/*     */   {
/*     */     try {
/*  45 */       URL obj = new URL(pUrl);
/*  46 */       HttpURLConnection con = (HttpURLConnection)obj.openConnection();
/*  47 */       con.setRequestMethod(pMetodo);
/*     */       
/*  49 */       con.setRequestProperty("Content-Type", "application/json;charset=ISO-8859-1");
/*     */       
/*  51 */       int responseCode = con.getResponseCode();
/*     */       
/*  53 */       BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
/*     */       
/*  55 */       StringBuffer response = new StringBuffer();
/*     */       String inputLine;
/*  57 */       while ((inputLine = in.readLine()) != null) {
/*  58 */         response.append(inputLine);
/*     */       }
/*  60 */       in.close();
/*  61 */       return response.toString();
/*     */     }
/*     */     catch (IOException ex) {
/*  64 */       Logger.getLogger(WSUtil.class.getName()).log(Level.SEVERE, null, ex); }
/*  65 */     return null;
/*     */   }
/*     */   
/*     */   public static String sendPost(String pUrl, String pUrlParametros, String pMetodo)
/*     */   {
/*     */     try {
/*  71 */       URL obj = new URL(pUrl);
/*  72 */       HttpURLConnection con = (HttpURLConnection)obj.openConnection();
/*  73 */       con.setRequestMethod(pMetodo);
/*     */       
/*  75 */       con.setRequestProperty("Content-Type", "application/json;charset=ISO-8859-1");
/*     */       
/*     */ 
/*  78 */       con.setDoOutput(true);
/*  79 */       con.setDoInput(true);
/*     */       
/*  81 */       DataOutputStream wr = new DataOutputStream(con.getOutputStream());
/*  82 */       wr.writeBytes(pUrlParametros);
/*  83 */       wr.flush();
/*  84 */       wr.close();
/*     */       
/*  86 */       int responseCode = con.getResponseCode();
/*     */       
/*  88 */       BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
/*     */       
/*  90 */       StringBuffer response = new StringBuffer();
/*     */       String inputLine;
/*  92 */       while ((inputLine = in.readLine()) != null) {
/*  93 */         response.append(inputLine);
/*     */       }
/*     */       
/*  96 */       in.close();
/*  97 */       return response.toString();
/*     */     } catch (IOException ex) {
/*  99 */       Logger.getLogger(WSUtil.class.getName()).log(Level.SEVERE, null, ex); }
/* 100 */     return null;
/*     */   }
/*     */   
/*     */   public static StringBuffer sendPostCIGAM(String pUrl, HashMap<String, String> pParametros)
/*     */   {
/*     */     try
/*     */     {
/* 107 */       URL url = new URL(pUrl);
/* 108 */       System.out.println("URL: " + url);
/* 109 */       HttpURLConnection con = (HttpURLConnection)url.openConnection();
/* 110 */       con.setRequestMethod("POST");
/* 111 */       con.setDoOutput(true);
/*     */       
/*     */ 
/* 114 */       String parametro = "";
/*     */       
/* 116 */       for (String key : pParametros.keySet()) {
/* 117 */         String value = (String)pParametros.get(key);
/* 118 */         parametro = parametro + key + "=" + value + "&";
/*     */       }
/*     */       
/*     */ 
/* 122 */       System.out.println("Parametro: " + parametro);
/*     */       
/* 124 */       con.getOutputStream().write(parametro.getBytes("UTF-8"));
/* 125 */       con.getInputStream();
/*     */       
/* 127 */       BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
/*     */       
/* 129 */       System.out.println(in);
/* 130 */       StringBuffer response = new StringBuffer();
/*     */       String inputLine;
/* 132 */       while ((inputLine = in.readLine()) != null) {
/* 133 */         response.append(inputLine);
/*     */       }
/*     */       
/* 136 */       in.close();
/* 137 */       return response;
/*     */     } catch (MalformedURLException ex) {
/* 139 */       Logger.getLogger(WSUtil.class.getName()).log(Level.SEVERE, null, ex);
/* 140 */       return null;
/*     */     } catch (IOException ex) {
/* 142 */       Logger.getLogger(WSUtil.class.getName()).log(Level.SEVERE, null, ex); }
/* 143 */     return null;
/*     */   }
/*     */ }



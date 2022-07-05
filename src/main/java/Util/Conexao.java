
/*    */ package Util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.sql.Connection;
/*    */ import java.sql.DriverManager;
/*    */ import java.sql.SQLException;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
 
/*    */ public class Conexao
/*    */ {
/*    */   static Connection conn;
/*    */   
/*    */   public static void main(String[] args)
/*    */     throws SQLException
/*    */   {
/* 23 */     Connection con = getConnection();
/* 24 */     
/*    */   }
/*    */   
/*    */   public static Connection getConnection() {
/*    */     try {
/* 29 */       if ((conn != null) && 
/* 30 */         (!conn.isClosed())) {
/* 31 */         return conn;
/*    */       }
/*    */     }
/*    */     catch (SQLException ex) {
/* 35 */       Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
/*    */     }
/*    */     
/*    */     try
             {

/*Local*/  String url = "jdbc:oracle:thin:@localhost:1521:AWS";
               String usuario = "aws";      
               String senha = "suporte";
/*Produção  String url = "jdbc:oracle:thin:@191.252.59.211:1521:XE";
               String usuario = "aws_erp_user";      
               String senha = "ltGZCEfw";*/
               
/* 46 */       Class.forName("oracle.jdbc.driver.OracleDriver");
/* 47 */       Connection con = null;
/* 48 */       con = DriverManager.getConnection(url, usuario, senha);
/* 49 */       conn = con;
/* 50 */       return con;
/*    */     } catch (Exception e) {
/* 52 */       System.out.println(e); }
/* 53 */     return null;
/*    */   }
/*    */ }



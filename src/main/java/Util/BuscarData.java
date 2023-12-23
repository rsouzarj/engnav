/*    */ package Util;
/*    */ 
/*    */ import java.sql.Connection;
/*    */ import java.sql.PreparedStatement;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.util.Date;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;

/*    */ public class BuscarData
/*    */ {
/*    */   public static Date buscarDataBanco()
/*    */   {
/* 24 */     Date retorno = null;
/* 25 */     Connection conn = Conexao.getConnection();
/* 26 */     String sql = "SELECT SYSDATE DATA FROM DUAL";
/*    */     
/*    */     try
/*    */     {
/* 30 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 31 */       ResultSet rs = ps.executeQuery();
/*    */       
/* 33 */       while (rs.next()) {
/* 34 */         retorno = rs.getDate("DATA");
/*    */       }
/*    */       
/* 37 */       rs.close();
/* 38 */       ps.close();
/*    */     }
/*    */     catch (SQLException ex) {
/* 41 */       Logger.getLogger(Sequence.class.getName()).log(Level.SEVERE, null, ex);
/*    */     }
/* 43 */     return retorno;
/*    */   }
/*    */ }



/*    */ package Util;
/*    */ 
/*    */ import java.sql.Connection;
/*    */ import java.sql.PreparedStatement;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;

/*    */ public class Sequence
/*    */ {
/*    */   public static String buscarNumeroSequence(String pSequence)
/*    */   {
/* 22 */     Long retorno = Long.valueOf(0L);
/* 23 */     Connection conn = Conexao.getConnection();
/* 24 */     String sql = "SELECT " + pSequence + ".nextVal ID FROM DUAL";
/*    */     
/*    */     try
/*    */     {
/* 28 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 29 */       ResultSet rs = ps.executeQuery();
/*    */       
/* 31 */       while (rs.next()) {
/* 32 */         retorno = Long.valueOf(rs.getLong("ID"));
/*    */       }
/* 34 */       rs.close();
/* 35 */       ps.close();
/*    */     }
/*    */     catch (SQLException ex) {
/* 38 */       Logger.getLogger(Sequence.class.getName()).log(Level.SEVERE, null, ex);
/*    */     }
/* 40 */     return String.valueOf(retorno);
/*    */   }
/*    */ }

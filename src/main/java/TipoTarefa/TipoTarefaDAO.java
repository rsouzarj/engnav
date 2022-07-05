/*     */ package TipoTarefa;
/*     */ 
/*     */ import ClausulaSQL.ClausulaWhere;
/*     */ import Util.Conexao;
/*     */ import Util.Sequence;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;



/*     */ public class TipoTarefaDAO
/*     */ {
/*     */   public TipoTarefa inserir(TipoTarefa tipoTarefa)
/*     */   {
/*     */     try
/*     */     {
/*  28 */       String seq = Sequence.buscarNumeroSequence("SEQ_TIPO_TAREFA");
/*  29 */       tipoTarefa.setSeqTipoTarefa(seq);
/*  30 */       Conexao conexao = new Conexao();
/*  31 */       Connection conn = Conexao.getConnection();
/*  32 */       String sql = "insert into TIPO_TAREFA (SEQ_TIPO_TAREFA,DATA_CADASTRO,SEQ_DEPARTAMENTO,SEQ_EMPRESA,HORAS,SEQ_USUARIO,NOME,SITUACAO) values  (?,?,?,?,?,?,?,?)";
/*     */       
/*     */ 
/*     */ 
/*  36 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*  38 */       ps.setString(1, tipoTarefa.getSeqTipoTarefa());
/*     */       try {
/*  40 */         ps.setDate(2, new java.sql.Date(tipoTarefa.getDataCadastro().getTime()));
/*     */       } catch (NullPointerException e) {
/*  42 */         ps.setDate(2, null);
/*     */       }
/*  44 */       ps.setObject(3, tipoTarefa.getSeqDepartamento(), 1);
/*  45 */       ps.setString(4, tipoTarefa.getSeqEmpresa());
/*  46 */       ps.setString(5, tipoTarefa.getHoras());
/*  47 */       ps.setObject(6, tipoTarefa.getSeqUsuario(), 1);
/*  48 */       ps.setString(7, tipoTarefa.getNome());
/*  49 */       ps.setString(8, tipoTarefa.getSituacao());
/*     */       
/*  51 */       ps.execute();
/*  52 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  55 */       Logger.getLogger(TipoTarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  56 */       System.out.println(ex.getMessage());
/*     */     }
/*  58 */     return tipoTarefa;
/*     */   }
/*     */   
/*     */   public TipoTarefa alterar(TipoTarefa tipoTarefa) {
/*     */     try {
/*  63 */       Conexao conexao = new Conexao();
/*  64 */       Connection conn = Conexao.getConnection();
/*  65 */       String sql = "update TIPO_TAREFA set DATA_CADASTRO = ?,SEQ_DEPARTAMENTO = ?,SEQ_EMPRESA = ?,HORAS = ?,SEQ_USUARIO = ?,NOME = ?,SITUACAO = ? where SEQ_TIPO_TAREFA = ?";
/*     */       
/*  67 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       try
/*     */       {
/*  70 */         ps.setDate(1, new java.sql.Date(tipoTarefa.getDataCadastro().getTime()));
/*     */       } catch (NullPointerException e) {
/*  72 */         ps.setDate(1, null);
/*     */       }
/*  74 */       ps.setObject(2, tipoTarefa.getSeqDepartamento(), 1);
/*  75 */       ps.setString(3, tipoTarefa.getSeqEmpresa());
/*  76 */       ps.setString(4, tipoTarefa.getHoras());
/*  77 */       ps.setObject(5, tipoTarefa.getSeqUsuario(), 1);
/*  78 */       ps.setString(6, tipoTarefa.getNome());
/*  79 */       ps.setString(7, tipoTarefa.getSituacao());
/*  80 */       ps.setString(8, tipoTarefa.getSeqTipoTarefa());
/*  81 */       ps.execute();
/*  82 */       ps.close();
/*     */     }
/*     */     catch (SQLException ex) {
/*  85 */       Logger.getLogger(TipoTarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
/*  86 */       System.out.println(ex.getMessage());
/*     */     }
/*     */     
/*  89 */     return tipoTarefa;
/*     */   }
/*     */   
/*     */   public List<TipoTarefa> listar(ClausulaWhere sClausula) {
/*     */     try {
/*  94 */       Conexao conexao = new Conexao();
/*  95 */       Connection conn = Conexao.getConnection();
/*  96 */       String sql = "SELECT * FROM TIPO_TAREFA" + sClausula.montarsClausula();
/*  97 */       System.out.println(sql);
/*     */       
/*  99 */       List<TipoTarefa> listaTipoTarefa = new ArrayList();
/* 100 */       PreparedStatement ps = conn.prepareStatement(sql);
/* 101 */       ResultSet rs = ps.executeQuery();
/*     */       
/* 103 */       while (rs.next()) {
/* 104 */         TipoTarefa tipoTarefa = new TipoTarefa();
/* 105 */         tipoTarefa.setSeqTipoTarefa(rs.getString("SEQ_TIPO_TAREFA"));
/* 106 */         tipoTarefa.setDataCadastro(rs.getDate("DATA_CADASTRO"));
/* 107 */         tipoTarefa.setSeqDepartamento(rs.getString("SEQ_DEPARTAMENTO"));
/* 108 */         tipoTarefa.setSeqEmpresa(rs.getString("SEQ_EMPRESA"));
/* 109 */         tipoTarefa.setHoras(rs.getString("HORAS"));
/* 110 */         tipoTarefa.setSeqUsuario(rs.getString("SEQ_USUARIO"));
/* 111 */         tipoTarefa.setNome(rs.getString("NOME"));
/* 112 */         tipoTarefa.setSituacao(rs.getString("SITUACAO"));
/* 113 */         listaTipoTarefa.add(tipoTarefa);
/*     */       }
/*     */       
/* 116 */       ps.execute();
/* 117 */       ps.close();
/*     */       
/* 119 */       return listaTipoTarefa;
/*     */     } catch (SQLException ex) {
/* 121 */       Logger.getLogger(TipoTarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
/* 122 */       System.out.println(ex.getMessage()); }
/* 123 */     return null;
/*     */   }
/*     */   
/*     */   public boolean deletar(TipoTarefa tipoTarefa)
/*     */   {
/*     */     try {
/* 129 */       Conexao conexao = new Conexao();
/* 130 */       Connection conn = Conexao.getConnection();
/* 131 */       String sql = "DELETE FROM TIPO_TAREFA WHERE SEQ_TIPO_TAREFA =  ? ";
/*     */       
/* 133 */       PreparedStatement ps = conn.prepareStatement(sql);
/*     */       
/*     */ 
/* 136 */       ps.setString(1, tipoTarefa.getSeqTipoTarefa());
/*     */       
/* 138 */       ps.execute();
/* 139 */       ps.close();
/*     */       
/* 141 */       return true;
/*     */     } catch (SQLException ex) {
/* 143 */       System.out.println(ex.getMessage()); }
/* 144 */     return false;
/*     */   }
/*     */ }

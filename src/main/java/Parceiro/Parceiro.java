/*     */ package Parceiro;
/*     */ 
import ParceiroCaracteristica.ParceiroCaracteristica;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*     */ public class Parceiro
/*     */ {
/*     */   private String seqParceiro;
/*     */   private String nome;
/*     */   private String tipo;
/*     */   private String documento;
/*     */   private String fantasia;
/*     */   private String logradouro;
/*     */   private String numero;
/*     */   private String complemento;
/*     */   private String bairro;
/*     */   private String cidade;
/*     */   private String uf;
/*     */   private String cep;
/*     */   private String telefone1;
/*     */   private String telefone2;
/*     */   private String telefone3;
/*     */   private String telefone4;
/*     */   private String email;
/*     */   private String pessoaContato;
/*     */   private String situacao;
/*     */   private String seqEmpresa;
/*     */   private String seqTipoParceiro;
/*     */   private Date dataCadastro;
/*     */   private String codigo;
/*     */   private String senha;
/*     */   private Date dataNascimento;
/*     */   private String atividadePrincipal;
/*     */   private String chaveOrigem;
/*     */   private String ie;
/*     */   private String im;
/*     */   private String tag1;
/*     */   private String tag2;
/*     */   private String tag3;
/*     */   private String tag4;
/*     */   private String unidadeNegocio;
/*     */   private String longitude;
/*     */   private String latitude;
/*     */   private String seqUsuario;
/*     */   private String origemParceiro;
/*     */   private String tipoParceiro;
/*     */   private String seqParceiroInclusao;
/*     */   private String seqTabelaPreco;
/*     */   private String pais;
/*     */   private String site;
            private String grupo;
            private String perfil;
            private String cargo;
            List<ParceiroCaracteristica> listaParceiroCaracteristica;
            
            public Parceiro()
            {
                this.listaParceiroCaracteristica = new ArrayList();
            }
/*     */   
/*     */   public String getSeqParceiro()
/*     */   {
/*  62 */     return this.seqParceiro;
/*     */   }
/*     */   
/*     */   public void setSeqParceiro(String seqParceiro) {
/*  66 */     this.seqParceiro = seqParceiro;
/*     */   }
/*     */   
/*     */   public String getNome() {
/*  70 */     return this.nome;
/*     */   }
/*     */   
/*     */   public void setNome(String nome) {
/*  74 */     this.nome = nome;
/*     */   }
/*     */   
/*     */   public String getTipo() {
/*  78 */     return this.tipo;
/*     */   }
/*     */   
/*     */   public void setTipo(String tipo) {
/*  82 */     this.tipo = tipo;
/*     */   }
/*     */   
/*     */   public String getDocumento() {
/*  86 */     return this.documento;
/*     */   }
/*     */   
/*     */   public void setDocumento(String documento) {
/*  90 */     this.documento = documento;
/*     */   }
/*     */   
/*     */   public String getFantasia() {
/*  94 */     return this.fantasia;
/*     */   }
/*     */   
/*     */   public void setFantasia(String fantasia) {
/*  98 */     this.fantasia = fantasia;
/*     */   }
/*     */   
/*     */   public String getLogradouro() {
/* 102 */     return this.logradouro;
/*     */   }
/*     */   
/*     */   public void setLogradouro(String logradouro) {
/* 106 */     this.logradouro = logradouro;
/*     */   }
/*     */   
/*     */   public String getNumero() {
/* 110 */     return this.numero;
/*     */   }
/*     */   
/*     */   public void setNumero(String numero) {
/* 114 */     this.numero = numero;
/*     */   }
/*     */   
/*     */   public String getComplemento() {
/* 118 */     return this.complemento;
/*     */   }
/*     */   
/*     */   public void setComplemento(String complemento) {
/* 122 */     this.complemento = complemento;
/*     */   }
/*     */   
/*     */   public String getBairro() {
/* 126 */     return this.bairro;
/*     */   }
/*     */   
/*     */   public void setBairro(String bairro) {
/* 130 */     this.bairro = bairro;
/*     */   }
/*     */   
/*     */   public String getCidade() {
/* 134 */     return this.cidade;
/*     */   }
/*     */   
/*     */   public void setCidade(String cidade) {
/* 138 */     this.cidade = cidade;
/*     */   }
/*     */   
/*     */   public String getUf() {
/* 142 */     return this.uf;
/*     */   }
/*     */   
/*     */   public void setUf(String uf) {
/* 146 */     this.uf = uf;
/*     */   }
/*     */   
/*     */   public String getCep() {
/* 150 */     return this.cep;
/*     */   }
/*     */   
/*     */   public void setCep(String cep) {
/* 154 */     this.cep = cep;
/*     */   }
/*     */   
/*     */   public String getTelefone1() {
/* 158 */     return this.telefone1;
/*     */   }
/*     */   
/*     */   public void setTelefone1(String telefone1) {
/* 162 */     this.telefone1 = telefone1;
/*     */   }
/*     */   
/*     */   public String getTelefone2() {
/* 166 */     return this.telefone2;
/*     */   }
/*     */   
/*     */   public void setTelefone2(String telefone2) {
/* 170 */     this.telefone2 = telefone2;
/*     */   }
/*     */   
/*     */   public String getTelefone3() {
/* 174 */     return this.telefone3;
/*     */   }
/*     */   
/*     */   public void setTelefone3(String telefone3) {
/* 178 */     this.telefone3 = telefone3;
/*     */   }
/*     */   
/*     */   public String getTelefone4() {
/* 182 */     return this.telefone4;
/*     */   }
/*     */   
/*     */   public void setTelefone4(String telefone4) {
/* 186 */     this.telefone4 = telefone4;
/*     */   }
/*     */   
/*     */   public String getEmail() {
/* 190 */     return this.email;
/*     */   }
/*     */   
/*     */   public void setEmail(String email) {
/* 194 */     this.email = email;
/*     */   }
/*     */   
/*     */   public String getPessoaContato() {
/* 198 */     return this.pessoaContato;
/*     */   }
/*     */   
/*     */   public void setPessoaContato(String pessoaContato) {
/* 202 */     this.pessoaContato = pessoaContato;
/*     */   }
/*     */   
/*     */   public String getSituacao() {
/* 206 */     return this.situacao;
/*     */   }
/*     */   
/*     */   public void setSituacao(String situacao) {
/* 210 */     this.situacao = situacao;
/*     */   }
/*     */   
/*     */   public String getSeqEmpresa() {
/* 214 */     return this.seqEmpresa;
/*     */   }
/*     */   
/*     */   public void setSeqEmpresa(String seqEmpresa) {
/* 218 */     this.seqEmpresa = seqEmpresa;
/*     */   }
/*     */   
/*     */   public String getSeqTipoParceiro() {
/* 222 */     return this.seqTipoParceiro;
/*     */   }
/*     */   
/*     */   public void setSeqTipoParceiro(String seqTipoParceiro) {
/* 226 */     this.seqTipoParceiro = seqTipoParceiro;
/*     */   }
/*     */   
/*     */   public Date getDataCadastro() {
/* 230 */     return this.dataCadastro;
/*     */   }
/*     */   
/*     */   public void setDataCadastro(Date dataCadastro) {
/* 234 */     this.dataCadastro = dataCadastro;
/*     */   }
/*     */   
/*     */   public String getCodigo() {
/* 238 */     return this.codigo;
/*     */   }
/*     */   
/*     */   public void setCodigo(String codigo) {
/* 242 */     this.codigo = codigo;
/*     */   }
/*     */   
/*     */   public String getSenha() {
/* 246 */     return this.senha;
/*     */   }
/*     */   
/*     */   public void setSenha(String senha) {
/* 250 */     this.senha = senha;
/*     */   }
/*     */   
/*     */   public Date getDataNascimento() {
/* 254 */     return this.dataNascimento;
/*     */   }
/*     */   
/*     */   public void setDataNascimento(Date dataNascimento) {
/* 258 */     this.dataNascimento = dataNascimento;
/*     */   }
/*     */   
/*     */   public String getAtividadePrincipal() {
/* 262 */     return this.atividadePrincipal;
/*     */   }
/*     */   
/*     */   public void setAtividadePrincipal(String atividadePrincipal) {
/* 266 */     this.atividadePrincipal = atividadePrincipal;
/*     */   }
/*     */   
/*     */   public String getChaveOrigem() {
/* 270 */     return this.chaveOrigem;
/*     */   }
/*     */   
/*     */   public void setChaveOrigem(String chaveOrigem) {
/* 274 */     this.chaveOrigem = chaveOrigem;
/*     */   }
/*     */   
/*     */   public String getIe() {
/* 278 */     return this.ie;
/*     */   }
/*     */   
/*     */   public void setIe(String ie) {
/* 282 */     this.ie = ie;
/*     */   }
/*     */   
/*     */   public String getIm() {
/* 286 */     return this.im;
/*     */   }
/*     */   
/*     */   public void setIm(String im) {
/* 290 */     this.im = im;
/*     */   }
/*     */   
/*     */   public String getTag1() {
/* 294 */     return this.tag1;
/*     */   }
/*     */   
/*     */   public void setTag1(String tag1) {
/* 298 */     this.tag1 = tag1;
/*     */   }
/*     */   
/*     */   public String getTag2() {
/* 302 */     return this.tag2;
/*     */   }
/*     */   
/*     */   public void setTag2(String tag2) {
/* 306 */     this.tag2 = tag2;
/*     */   }
/*     */   
/*     */   public String getTag3() {
/* 310 */     return this.tag3;
/*     */   }
/*     */   
/*     */   public void setTag3(String tag3) {
/* 314 */     this.tag3 = tag3;
/*     */   }
/*     */   
/*     */   public String getTag4() {
/* 318 */     return this.tag4;
/*     */   }
/*     */   
/*     */   public void setTag4(String tag4) {
/* 322 */     this.tag4 = tag4;
/*     */   }
/*     */   
/*     */   public String getUnidadeNegocio() {
/* 326 */     return this.unidadeNegocio;
/*     */   }
/*     */   
/*     */   public void setUnidadeNegocio(String unidadeNegocio) {
/* 330 */     this.unidadeNegocio = unidadeNegocio;
/*     */   }
/*     */   
/*     */   public String getStringitude() {
/* 334 */     return this.longitude;
/*     */   }
/*     */   
/*     */   public void setStringitude(String longitude) {
/* 338 */     this.longitude = longitude;
/*     */   }
/*     */   
/*     */   public String getLatitude() {
/* 342 */     return this.latitude;
/*     */   }
/*     */   
/*     */   public void setLatitude(String latitude) {
/* 346 */     this.latitude = latitude;
/*     */   }
/*     */   
/*     */   public String getSeqUsuario() {
/* 350 */     return this.seqUsuario;
/*     */   }
/*     */   
/*     */   public void setSeqUsuario(String seqUsuario) {
/* 354 */     this.seqUsuario = seqUsuario;
/*     */   }
/*     */   
/*     */   public String getTipoParceiro() {
/* 358 */     return this.tipoParceiro;
/*     */   }
/*     */   
/*     */   public void setTipoParceiro(String tipoParceiro) {
/* 362 */     this.tipoParceiro = tipoParceiro;
/*     */   }
/*     */   
/*     */   public String getSeqParceiroInclusao() {
/* 366 */     return this.seqParceiroInclusao;
/*     */   }
/*     */   
/*     */   public void setSeqParceiroInclusao(String seqParceiroInclusao) {
/* 370 */     this.seqParceiroInclusao = seqParceiroInclusao;
/*     */   }
/*     */   
/*     */   public String getSeqTabelaPreco() {
/* 374 */     return this.seqTabelaPreco;
/*     */   }
/*     */   
/*     */   public void setSeqTabelaPreco(String seqTabelaPreco) {
/* 378 */     this.seqTabelaPreco = seqTabelaPreco;
/*     */   }
/*     */   
/*     */   public String getOrigemParceiro() {
/* 382 */     return this.origemParceiro;
/*     */   }
/*     */   
/*     */   public void setOrigemParceiro(String origemParceiro) {
/* 386 */     this.origemParceiro = origemParceiro;
/*     */   }

/*     */   public String getPais() {
/* 382 */     return this.pais;
/*     */   }
/*     */   
/*     */   public void setPais(String pais) {
/* 386 */     this.pais = pais;
/*     */   }

/*     */   public String getSite() {
/* 382 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(String site) {
/* 386 */     this.site = site;
/*     */   }

/*     */   public String getGrupo() {
/* 382 */     return this.grupo;
/*     */   }
/*     */   
/*     */   public void setGrupo(String grupo) {
/* 386 */     this.grupo = grupo;
/*     */   }

/*     */   public String getPerfil() {
/* 382 */     return this.perfil;
/*     */   }
/*     */   
/*     */   public void setPerfil(String perfil) {
/* 386 */     this.perfil = perfil;
/*     */   }

/*     */   public String getCargo() {
/* 382 */     return this.cargo;
/*     */   }
/*     */   
/*     */   public void setCargo(String cargo) {
/* 386 */     this.cargo = cargo;
/*     */   }

  public List<ParceiroCaracteristica> getListaParceiroCaracteristica() {
    return this.listaParceiroCaracteristica;
  }
  
  public void setListaParceiroCaracteristica(List<ParceiroCaracteristica> listaParceiroCaracteristica) {
    this.listaParceiroCaracteristica = listaParceiroCaracteristica;
  }
 }


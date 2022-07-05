package WS;

import Comissao.Comissao;
import Comissao.ComissaoService;
import CondicaoPagamento.CondicaoPagamento;
import CondicaoPagamento.CondicaoPagamentoService;
import Documento.Documento;
import Documento.DocumentoService;
import FormaPagamento.FormaPagamento;
import FormaPagamento.FormaPagamentoService;
import MaterialPreco.MaterialPreco;
import MaterialPreco.MaterialPrecoService;
import Parceiro.Parceiro;
import Parceiro.ParceiroService;
import TipoParceiro.TipoParceiro;
import TipoParceiro.TipoParceiroService;
import Uf.Uf;
import Uf.UfService;
import Usuario.Usuario;
import Usuario.UsuarioService;
import Util.Modulo;
import Util.Retorno;
import Util.Situacao;
import Util.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("ws")
public class ERPWS {
	@Context
	private UriInfo context;

	@GET
	@Produces({ "application/json" })
	public String getJson() {
		return "Meu Primeiro WS RestFULL";
	}

	@PUT
	@Consumes({ "application/json" })
	public void putJson(String content) {
	}

	@GET
	@Path("Usuario/ValidarUsuario/{pChave}/{pUsuario}/{pSenha}")
	@Produces({ "application/json" })
	public String validarAcessoUsuario(@PathParam("pChave") String pChave, @PathParam("pUsuario") String pUsuario,
			@PathParam("pSenha") String pSenha) {
		Usuario usuario = new Usuario();
		UsuarioService usuarioService = new UsuarioService();
		usuario = usuarioService.validarAcesso(pUsuario, pSenha, pChave, Modulo.Comercial);

		Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();

		return gson.toJson(usuario);
	}

	@GET
	@Path("Usuario/EnviarSenhaEmail/{pEmail}")
	@Produces({ "application/json" })
	public String enviarSenhaEmail(@PathParam("pEmail") String pEmail) {
		UsuarioService usuarioService = new UsuarioService();
		boolean retorno = usuarioService.enviarEmailSenha(pEmail);

		Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();

		return gson.toJson(Boolean.valueOf(retorno));
	}

	@GET
	@Path("Usuario/ListarComissao/{pSeqUsuario}")
	@Produces({ "application/json" })
	public String listarComissao(@PathParam("pSeqUsuario") String pSeqUsuario) {
		List<Comissao> retorno = new ArrayList();

		ComissaoService comissaoService = new ComissaoService();
		retorno = comissaoService.listarLiberadoPorUsuario(pSeqUsuario);
		Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
		return gson.toJson(retorno);
	}

	@POST
	@Path("Usuario/SalvarUsuario")
	@Consumes({ "application/json" })
	public String salvarUsuario(String pConteudo) {
		Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
		Usuario usuario = (Usuario) gson.fromJson(pConteudo, Usuario.class);

		UsuarioService usuarioService = new UsuarioService();
		usuario = usuarioService.salvarUsuario(usuario);

		return gson.toJson(usuario);
	}

	@GET
	@Path("Parceiro/ListarParceiro/{pSeqUsuario}")
	@Produces({ "application/json" })
	public String listarParceiro(@PathParam("pSeqUsuario") String pSeqUsuario) {
		List<Parceiro> retorno = new ArrayList();
		ParceiroService parceiroService = new ParceiroService();
		retorno = parceiroService.listarParceiro(pSeqUsuario, "");

		Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
		return gson.toJson(retorno);
	}

	@GET
	@Path("Parceiro/BuscarParceiro/{pSeqEmpresa}/{pCodigo}")
	@Produces({ "application/json" })
	public String buscarParceiro(@PathParam("pSeqEmpresa") String pSeqEmpresa, @PathParam("pCodigo") String pCodigo) {
		Parceiro retorno = new Parceiro();
		ParceiroService parceiroService = new ParceiroService();
		retorno = parceiroService.buscar(pSeqEmpresa, pCodigo);

		Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
		return gson.toJson(retorno);
	}

	@POST
	@Path("Parceiro/SalvarParceiro")
	@Consumes({ "application/json" })
	public String salvarParceiro(String pConteudo) {
		Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
		Parceiro parceiro = (Parceiro) gson.fromJson(pConteudo, Parceiro.class);

		ParceiroService parceiroService = new ParceiroService();
		Retorno retorno = parceiroService.salvar(parceiro);

		return gson.toJson(retorno);
	}

	@GET
	@Path("Parceiro/ListarTipoParceiro/{pSeqEmpresa}")
	@Produces({ "application/json" })
	public String listarTipoParceiro(@PathParam("pSeqEmpresa") String pSeqEmpresa) {
		List<TipoParceiro> retorno = new ArrayList();
		TipoParceiroService tipoparceiroService = new TipoParceiroService();
		retorno = tipoparceiroService.listar(pSeqEmpresa, Situacao.ATIVO);

		Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
		return gson.toJson(retorno);
	}

	@GET
	@Path("Util/ListarUF")
	@Produces({ "application/json" })
	public String listarUf() {
		List<Uf> retorno = new ArrayList();

		retorno = new ArrayList();
		UfService ufService = new UfService();
		retorno = ufService.listar();

		Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();

		return gson.toJson(retorno);
	}

	@GET
	@Path("Parceiro/BuscarDadosReceita")
	@Consumes({ "application/json" })
	public String buscarDadosReceita(String pConteudo) throws IOException {
		Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
		Parceiro parceiro = (Parceiro) gson.fromJson(pConteudo, Parceiro.class);

		Util util = new Util();
		parceiro = util.buscarDadosReceitaFederal(parceiro);

		return gson.toJson(parceiro);
	}

	@POST
	@Path("Pedido/SalvarPedido")
	@Consumes({ "application/json" })
	public String salvarPedido(String pConteudo) {
		Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
		Documento documento = (Documento) gson.fromJson(pConteudo, Documento.class);

		DocumentoService documentoService = new DocumentoService();
		documento = documentoService.salvar(documento);

		return gson.toJson(documento);
	}

	@GET
	@Path("Pedido/ListarPedidoParceiro/{pSeqEmpresa}/{pSeqParceiro}")
	@Produces({ "application/json" })
	public String listarPedidoParceiro(@PathParam("pSeqEmpresa") String pSeqEmpresa,
			@PathParam("pSeqParceiro") String pSeqParceiro) {
		List<Documento> retorno = new ArrayList();
		DocumentoService documentoservice = new DocumentoService();
		retorno = documentoservice.listarPorParceiro(pSeqEmpresa, pSeqParceiro);

		Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
		return gson.toJson(retorno);
	}

	@GET
	@Path("Pedido/ListarPedidoVendedor/{pSeqEmpresa}/{pSeqUsuario}")
	@Produces({ "application/json" })
	public String listarPedidoVendedor(@PathParam("pSeqEmpresa") String pSeqEmpresa,
			@PathParam("pSeqUsuario") String pSeqUsuario) {
		List<Documento> retorno = new ArrayList();
		DocumentoService documentoservice = new DocumentoService();
		retorno = documentoservice.listarPorVendedor(pSeqEmpresa, pSeqUsuario);

		Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
		return gson.toJson(retorno);
	}

	@GET
	@Path("Produto/ListarProduto/{pSeqEmpresa}/{pChaveOrigem}")
	@Produces({ "application/json" })
	public String listarProdutos(@PathParam("pSeqEmpresa") String pSeqEmpresa,
			@PathParam("pChaveOrigem") String pChaveOrigem) {
		List<MaterialPreco> retorno = new ArrayList();
		MaterialPrecoService materialPrecoService = new MaterialPrecoService();
		retorno = materialPrecoService.listarPorParceiro(pSeqEmpresa, pChaveOrigem);

		Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
		return gson.toJson(retorno);
	}

	@GET
	@Path("Pagamento/ListarCondicaoPagamento/{pSeqEmpresa}")
	@Produces({ "application/json" })
	public String listarCondicaoPagamento(@PathParam("pSeqEmpresa") String pSeqEmpresa) {
		List<CondicaoPagamento> retorno = new ArrayList();
		CondicaoPagamentoService condicaoPagamentoService = new CondicaoPagamentoService();
		retorno = condicaoPagamentoService.listar(pSeqEmpresa, "", Situacao.ATIVO);

		Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
		return gson.toJson(retorno);
	}

	@GET
	@Path("Pagamento/ListarFormaPagamento/{pSeqEmpresa}")
	@Produces({ "application/json" })
	public String listarFormaPagamento(@PathParam("pSeqEmpresa") String pSeqEmpresa) {
		List<FormaPagamento> retorno = new ArrayList();
		FormaPagamentoService formaPagamentoService = new FormaPagamentoService();
		retorno = formaPagamentoService.listar(pSeqEmpresa, "", Situacao.ATIVO);

		Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
		return gson.toJson(retorno);
	}
}

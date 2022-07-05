package WS;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ClausulaSQL.ClausulaWhere;
import ClausulaSQL.GeneroCondicaoWhere;
import ClausulaSQL.OperacaoCondicaoWhere;
import ClausulaSQL.TipoCondicaoWhere;
import NvCertificado.NvCertificado;
import NvCertificado.NvCertificadoService;
import NvEmbarcacao.NvEmbarcacao;
import NvEmbarcacao.NvEmbarcacaoService;
import NvEmbarcacaoDetalhe.NvEmbarcacaoDetalhe;
import NvEmbarcacaoDetalhe.NvEmbarcacaoDetalheService;
import NvVistoria.NvVistoria;
import NvVistoria.NvVistoriaService;
import Util.Conexao;
import Util.Util;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

@javax.servlet.annotation.WebServlet({ "/operacional/certificado/*" })
public class RelatorioOperacionalCertificadoDownload extends javax.servlet.http.HttpServlet {
	
	
	Util util = new Util();
	NvCertificadoService nvCertificadoService = new NvCertificadoService();
	NvVistoria nvVistoria = new NvVistoria();
	NvCertificado nvCertificado = new NvCertificado();
	NvEmbarcacao nvEmbarcacao = new NvEmbarcacao();
	NvVistoriaService nvVistoriaService = new NvVistoriaService();
	NvEmbarcacaoService nvEmbarcacaoService = new NvEmbarcacaoService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		String[] pathInfo = request.getPathInfo().split("/");
		System.out.println(request.getPathInfo());
		String empresa = pathInfo[1];
		String uuid = pathInfo[2];
		try {
			imprimir(empresa,uuid, response);
		}catch (Exception e) {
			PrintWriter out = response.getWriter();
	        response.setContentType("text/plain");
	        response.setCharacterEncoding("UTF-8");
	        out.print("Erro Interno");
	        out.flush();
	        return;
		}
	}

	public void imprimir(String empresa, String uuid,HttpServletResponse response) throws JRException, IOException {
		ClausulaWhere condicao = new ClausulaWhere();
		condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio,
				"NV_CERTIFICADO.seq_empresa", GeneroCondicaoWhere.igual,
				empresa,
				TipoCondicaoWhere.Numero);
		
		condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "NV_CERTIFICADO.UUID",
				GeneroCondicaoWhere.igual, uuid, TipoCondicaoWhere.Texto);
		List<NvCertificado> certificado = nvCertificadoService.listar(condicao);
		if(certificado == null || certificado.isEmpty()) {
			PrintWriter out = response.getWriter();
	        response.setContentType("text/plain");
	        response.setCharacterEncoding("UTF-8");
	        out.print("Certificado não encontrado");
	        out.flush();
	        return;
		}
		nvCertificado = certificado.get(0);
		
		imprimir(uuid,response);
	}
	
	
	public void imprimir(String uuid,HttpServletResponse response) throws JRException, IOException {

		this.nvVistoria = this.nvVistoriaService
				.buscar(this.nvCertificado.getSeqNvVistoria());
		this.nvEmbarcacao = this.nvEmbarcacaoService
				.buscar(this.nvCertificado.getSeqNvEmbarcacao());

		HashMap parametro = new HashMap();
		Conexao conexao = new Conexao();
		Connection conn = Conexao.getConnection();

		String caminho = "";
		
		if (this.nvCertificado.getObservacao() == null) {
			this.nvCertificado.setObservacao(" ");
		}
		if (this.nvCertificado.getObservacao().contains("font-size: 13.3333px;")) {
			this.nvCertificado.setObservacao(this.nvCertificado.getObservacao()
					.replace("font-size: 13.3333px;", ""));
			this.nvCertificado.setObservacao(
					this.nvCertificado.getObservacao().replace("font-size: 10pt;", ""));
			this.nvCertificado = this.nvCertificadoService.salvar(this.nvCertificado);
		}

		if (this.nvCertificado.getStatus().equals("Provisório")) {
			parametro.put("pCondicao", "(PROVISÓRIO)");
			if (this.nvCertificado.getSeqNvTipoCertificado().equals("164")) {
				caminho = "/relatorio/CERTIFICADO NACIONAL DE BORDA LIVRE PARA NAVEGAÇÃO DE MAR ABERTO/BORDA LIVRE MAR ABERTO PROVISÓRIO.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("28")) {
				caminho = "/relatorio/CERTIFICADO NACIONAL DE ARQUEAÇÃO/ARQUEAÇÃO PROVISÓRIO.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("165")) {
				caminho = "/relatorio/BLNI/BORDA LIVRE NAVEGAÇÃO INTERIOR PROVISÓRIO.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("103")) {
				caminho = "/relatorio/CSN/MAR ABERTO/CSN MAR ABERTO_2_1.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("141")) {
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/07/07 - PROV-COND.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("101")) {
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/06/06 - PROV-COND.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("163")) {
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/05/05 - PROV-COND.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("173")) {
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/04/04 - PROV-COND.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("161")) {
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/03/03 - PROV-COND.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("162")) {
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/02/02 - PROV-COND.jasper";
			}
			else {
				PrintWriter out = response.getWriter();
		        response.setContentType("text/plain");
		        response.setCharacterEncoding("UTF-8");
		        out.print("Modelo de certificado provisório não encontrado!");
		        out.flush();
		        return;
			}

		}
		else if (this.nvCertificado.getStatus().equals("Definitivo")) {
			parametro.put("pCondicao", "");
			if (this.nvCertificado.getSeqNvTipoCertificado().equals("28")) {
				caminho = "/relatorio/CERTIFICADO NACIONAL DE ARQUEAÇÃO/ARQUEAÇÃO DEFINITIVA.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("165")) {
				caminho = "/relatorio/BLNI/BORDA LIVRE NAVEGAÇÃO INTERIOR DEFINITIVO.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("102")) {
				caminho = "/relatorio/CERTIFICADO DE SEGURANÇA DA NAVEGAÇÃO/Certificado de Segurança da Navegação - NI (Embarcações Não-Propulsadas).jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("164")) {
				caminho = "/relatorio/CERTIFICADO NACIONAL DE BORDA LIVRE PARA NAVEGAÇÃO DE MAR ABERTO/BORDA LIVRE MAR ABERTO DEFINITIVO.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("103")) {
				caminho = "/relatorio/CSN/MAR ABERTO/CSN MAR ABERTO DEFINITIVO.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("141")) {
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/07/07 - DEFINITIVO.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("101")) {
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/06/06 - DEFINITIVO.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("163")) {
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/05/05 - DEFINITIVO.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("173")) {
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/04/04 - DEFINITIVO.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("161")) {
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/03/03 - DEFINITIVO.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("162")) {
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/02/02 - DEFINITIVO.jasper";
			}
			else {
				PrintWriter out = response.getWriter();
		        response.setContentType("text/plain");
		        response.setCharacterEncoding("UTF-8");
		        out.print("Modelo de certificado definitivo não encontrado!");
		        out.flush();
		        return;
			}

		}
                
                else if (this.nvCertificado.getStatus().equals("Aprovado")) {
			parametro.put("pCondicao", "");
			if (this.nvCertificado.getSeqNvTipoCertificado().equals("201")) {
				//feito
				caminho = "/relatorio/CERTIFICADO HELIDEQUE/RH.jasper";
			}
                        else if (this.nvCertificado.getSeqNvTipoCertificado().equals("202")) {
				//feito
				caminho =  "/relatorio/CERTIFICADO HELIDEQUE/RTPH.jasper";
			}
                        else if (this.nvCertificado.getSeqNvTipoCertificado().equals("203")) {
				//feito
				caminho =  "/relatorio/CERTIFICADO HELIDEQUE/RB.jasper";
			}
                        else if (this.nvCertificado.getSeqNvTipoCertificado().equals("204")) {
				//feito
				caminho =  "/relatorio/CERTIFICADO HELIDEQUE/CA.jasper";
			}
                        else if (this.nvCertificado.getSeqNvTipoCertificado().equals("205")) {
				//feito
				caminho =  "/relatorio/CERTIFICADO HELIDEQUE/SC.jasper";
			}
                        else if (this.nvCertificado.getSeqNvTipoCertificado().equals("206")) {
				//feito
				caminho =  "/relatorio/CERTIFICADO HELIDEQUE/MCTAP.jasper";
			}
                       else {
				PrintWriter out = response.getWriter();
		        response.setContentType("text/plain");
		        response.setCharacterEncoding("UTF-8");
		        out.print("Modelo de certificado definitivo não encontrado!");
		        out.flush();
		        return;
			}
                }
                
                
		else if (this.nvCertificado.getStatus().equals("Condicional")) {
			parametro.put("pCondicao", "(CONDICIONAL)");

			if (this.nvCertificado.getSeqNvTipoCertificado().equals("28")) {
				caminho = "/relatorio/CERTIFICADO NACIONAL DE ARQUEAÇÃO/ARQUEAÇÃO CONDICIONAL.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("165")) {
				caminho = "/relatorio/BLNI/BORDA LIVRE NAVEGAÇÃO INTERIOR CONDICIONAL.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("103")) {
				caminho = "/relatorio/CSN/MAR ABERTO/CSN MAR ABERTO_2_1.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("164")) {
				caminho = "/relatorio/CERTIFICADO NACIONAL DE BORDA LIVRE PARA NAVEGAÇÃO DE MAR ABERTO/BORDA LIVRE MAR ABERTO CONDICIONAL.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("141")) {
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/07/07 - PROV-COND.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("101")) {
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/06/06 - PROV-COND.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("163")) {
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/05/05 - PROV-COND.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("173")) {
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/04/04 - PROV-COND.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("161")) {
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/03/03 - PROV-COND.jasper";
			}
			else if (this.nvCertificado.getSeqNvTipoCertificado().equals("162")) {
				caminho = "/relatorio/CSN/NAVEGAÇÃO INTERIOR/02/02 - PROV-COND.jasper";
			}
			else {
				PrintWriter out = response.getWriter();
		        response.setContentType("text/plain");
		        response.setCharacterEncoding("UTF-8");
		        out.print("Modelo de certificado condicional não encontrado!");
		        out.flush();
		        return;
			}
		}

		if (this.nvVistoria.getDataVistoria() != null) {
			parametro.put("pDataVistoria",
					this.util.DataToString(this.nvVistoria.getDataVistoria()));
		}
		else {
			parametro.put("pDataVistoria", "__/__/____");
		}

		if (this.nvVistoria.getDataDocagem() != null) {
			parametro.put("pDataDocagem",
					this.util.DataToString(this.nvVistoria.getDataDocagem()));
		}
		else {
			parametro.put("pDataDocagem", "__/__/____");
		}

		if (this.nvEmbarcacao.getDataInscricao() != null) {
			Integer pDataInscricao = Integer
					.valueOf(this.nvEmbarcacao.getDataInscricao().getYear() + 1900);
			parametro.put("pDataInscricao", pDataInscricao.toString());
		}
		else {
			parametro.put("pDataInscricao", "");
		}

		if (this.nvEmbarcacao.getRequisitosTransporteColetivo().equals("Sim")) {
			parametro.put("pSim", "X");
		}
		else if (this.nvEmbarcacao.getRequisitosTransporteColetivo().equals("Não")) {
			parametro.put("pNao", "X");
		}

		parametro.put("pSeqCertificado",
				Long.valueOf(this.nvCertificado.getSeqNvCertificado()));
		parametro.put("pImagem", "../../images/brasao.gif");
		parametro.put("pEmissao",
				this.util.DataPorExtenso(
						"Expedido em: " + this.nvCertificado.getLocalEmissao() + ", ",
						this.nvCertificado.getDataCadastro()));
		Date data = new Date();
		Locale local = new Locale("pt", "BR");
		DateFormat dateFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);
		parametro.put("pValidade",
				"Válido até: " + (this.nvCertificado.getDataValidade() != null
						? dateFormat.format(this.nvCertificado.getDataValidade())
						: ""));
		parametro.put("pDataValidade",
				this.util.DataPorExtenso("", this.nvCertificado.getDataValidade()));

		System.out.println(
				"Area Navegação tipo: " + this.nvEmbarcacao.getAreaNavegacaoTipo());

		if (this.nvEmbarcacao.getAreaNavegacaoTipo().contains("Área 01 e 02")) {
			parametro.put("pAreaNavegacao", "ÁREA DE NAVEGAÇÃO: ( X ) 1  ( X ) 2");
			parametro.put("x1", "X");
			parametro.put("x2", "X");
		}
		else if (this.nvEmbarcacao.getAreaNavegacaoTipo().contains("Área 01")) {
			parametro.put("pAreaNavegacao", "ÁREA DE NAVEGAÇÃO: ( X ) 1  (  ) 2");
			parametro.put("pNumeroCirculo", "1");
			parametro.put("x1", "X");
		}
		else if (this.nvEmbarcacao.getAreaNavegacaoTipo().contains("Área 02")) {
			parametro.put("pAreaNavegacao", "ÁREA DE NAVEGAÇÃO: (  ) 1  ( X ) 2");
			parametro.put("pNumeroCirculo", "2");
			parametro.put("x2", "X");
		}
		else {
			parametro.put("pAreaNavegacao", "ÁREA DE NAVEGAÇÃO: (  ) 1  (  ) 2");
			parametro.put("pNumeroCirculo", "");
		}

		switch (this.nvEmbarcacao.getTipo()) {
		case "A":
			parametro.put("pEmbarcacaoTipo",
					"EMBARCAÇÃO DO TIPO: ( X ) A     (  ) B     (  ) C      (  ) D     (  ) E  ");
			break;
		case "B":
			parametro.put("pEmbarcacaoTipo",
					"EMBARCAÇÃO DO TIPO: (  ) A     ( X ) B     (  ) C      (  ) D     (  ) E  ");
			break;
		case "C":
			parametro.put("pEmbarcacaoTipo",
					"EMBARCAÇÃO DO TIPO: (  ) A     (  ) B     ( X ) C      (  ) D     (  ) E  ");
			break;
		case "D":
			parametro.put("pEmbarcacaoTipo",
					"EMBARCAÇÃO DO TIPO: (  ) A     (  ) B     (  ) C      ( X ) D     (  ) E  ");
			break;
		case "E":
			parametro.put("pEmbarcacaoTipo",
					"EMBARCAÇÃO DO TIPO: (  ) A     (  ) B     (  ) C      (  ) D     ( X ) E  ");
			break;
		default:
			parametro.put("pEmbarcacaoTipo",
					"EMBARCAÇÃO DO TIPO: (  ) A     (  ) B     (  ) C      (  ) D     (  ) E  ");
		}

		NvEmbarcacaoDetalheService nvEmbarcacaoDetalheService = new NvEmbarcacaoDetalheService();

		String b = "false";
		for (NvEmbarcacaoDetalhe item : nvEmbarcacaoDetalheService
				.listar(this.nvEmbarcacao.getSeqNvEmbarcacao())) {
			if (!item.getOrdem().equals("01")) {
				if ((item.getDetalhe2() == null) && (item.getDetalhe3() == null)
						&& (item.getDetalhe4() == null)) {
					b = "true";
				}
				else {
					b = "false";
					break;
				}
			}
		}
		parametro.put("pLinhaForm", b);
		parametro.put("pVistoriaInicial",
				this.util.DataToString(this.nvVistoria.getDataVistoria()));
		String reportURL = "http://191.252.59.211/erp/operacional/certificado/"+this.nvCertificado.getSeqEmpresa()+"/"+uuid;
		System.out.println(reportURL);
		parametro.put("pReportURL", reportURL);

		ServletContext scontext = getServletContext();

		String realPath = scontext.getRealPath(caminho);
		System.out.println(realPath);
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(realPath, parametro, conn);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JRPdfExporter exporter = new JRPdfExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

		exporter.exportReport();
		byte[] bytes = baos.toByteArray();

		if ((bytes != null) && (bytes.length > 0)) {
			response.setContentType("application/pdf");
			String fileName = "inline; filename=\"Certificado_"
					+ nvCertificado.getTipoCertificado() + "_"
					+ nvCertificado.getIdentificacao() + "_"
					+ nvEmbarcacao.getNome() + ".pdf\"";
			System.out.println(fileName);
			response.setHeader("Content-disposition",fileName);
			response.setContentLength(bytes.length);
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(bytes, 0, bytes.length);
			outputStream.flush();
			outputStream.close();
		}
	
	}

}
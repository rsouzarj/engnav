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
import NvCertificadoEquipamento.NvCertificadoEquipamento;
import NvCertificadoEquipamento.NvCertificadoEquipamentoService;
import Util.Conexao;
import Util.Util;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

@javax.servlet.annotation.WebServlet({ "/operacional/certificado_equipamento/*" })
public class RelatorioOperacionalCertificadoEqpDownload extends javax.servlet.http.HttpServlet {
	
	
	Util util = new Util();
	NvCertificadoEquipamentoService nvCertificadoEquipamentoService = new NvCertificadoEquipamentoService();
	NvCertificadoEquipamento nvCertificadoEquipamento = new NvCertificadoEquipamento();
	

	protected void doGet(HttpServletRequest request, HttpServletResponse responseeqp)
			throws ServletException, java.io.IOException {
		String[] pathInfo = request.getPathInfo().split("/");
		System.out.println(request.getPathInfo());
		String empresa = pathInfo[1];
		String uuid = pathInfo[2];
		try {
			imprimir(empresa,uuid, responseeqp);
		}catch (Exception e) {
			PrintWriter out = responseeqp.getWriter();
	        responseeqp.setContentType("text/plain");
	        responseeqp.setCharacterEncoding("UTF-8");
	        out.print("Erro Interno");
	        out.flush();
	        return;
		}
	}

	public void imprimir(String empresa, String uuid,HttpServletResponse responseeqp) throws JRException, IOException {
		ClausulaWhere condicao = new ClausulaWhere();
		condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio,
				"NV_CERTIFICADO_EQUIPAMENTO.seq_empresa", GeneroCondicaoWhere.igual,
				empresa,
				TipoCondicaoWhere.Numero);
		
		condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "NV_CERTIFICADO_EQUIPAMENTO.UUID",
				GeneroCondicaoWhere.igual, uuid, TipoCondicaoWhere.Texto);
		List<NvCertificadoEquipamento> certificadoeqp = nvCertificadoEquipamentoService.listar(condicao);
		if(certificadoeqp == null || certificadoeqp.isEmpty()) {
			PrintWriter out = responseeqp.getWriter();
	        responseeqp.setContentType("text/plain");
	        responseeqp.setCharacterEncoding("UTF-8");
	        out.print("Certificado não encontrado");
	        out.flush();
	        return;
		}
		nvCertificadoEquipamento = certificadoeqp.get(0);
		
		imprimir(uuid,responseeqp);
	}
	
	
	public void imprimir(String uuid,HttpServletResponse responseeqp) throws JRException, IOException {


		HashMap parametro = new HashMap();
		Conexao conexao = new Conexao();
		Connection conn = Conexao.getConnection();

		String caminho = "/relatorio/CERTIFICADO EQUIPAMENTO/CERTIFICADO.jasper";
                String reportURL = "http://191.252.59.211/erp/operacional/certificado_equipamento/"+this.nvCertificadoEquipamento.getSeqEmpresa()+"/"+uuid;
		System.out.println(reportURL);
		parametro.put("pSeqCertificado",Integer.valueOf(this.nvCertificadoEquipamento.getSeqCertificado()));
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
			responseeqp.setContentType("application/pdf");
			String fileName = "inline; filename=\"Certificado.pdf\"";
			System.out.println(fileName);
			responseeqp.setHeader("Content-disposition",fileName);
			responseeqp.setContentLength(bytes.length);
			ServletOutputStream outputStream = responseeqp.getOutputStream();
			outputStream.write(bytes, 0, bytes.length);
			outputStream.flush();
			outputStream.close();
		}
	
	}

}
package Util;

import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Cep {
	private static String URL_STRING = "http://cep.republicavirtual.com.br/web_cep.php?cep=%s&formato=xml";
	private int resulCode = -1;
	private String resultText = "busca não realizada.";
	private String cep = null;
	private String bairro = null;
	private String cidade = null;
	private String logradouro = null;
	private String logradouroType = null;
	private String uf = null;
	private Exception exception;

	public static void main(String[] args) {
		Cep cep = Cep.buscarCep("26263240");
		System.out.println(cep.getBairro());
	}

	public Cep() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	private static Document getDocument(String cep) throws DocumentException, MalformedURLException {
		URL url = new URL(String.format(URL_STRING, cep));
		SAXReader reader = new SAXReader();
		Document document = reader.read(url);
		return document;
	}

	private static Element getRootElement(String cep) throws DocumentException, MalformedURLException {
		return Cep.getDocument(cep).getRootElement();
	}

	private static IterableElement getElements(String cep) throws DocumentException, MalformedURLException {
		return new IterableElement(Cep.getRootElement(cep).elementIterator());
	}

	public static Cep buscarCep(String cep) {
		if ((cep = cep.replaceAll("\\D*", "")).length() > 8) {
			cep = cep.substring(0, 8);
		}
		Cep loadCep = new Cep(cep);
		try {
			XmlEnums enums = new XmlEnums();
			for (Element e : Cep.getElements(cep)) {
				enums.getXml(e.getQualifiedName()).setCep(e.getText(), loadCep);
			}
		} catch (DocumentException ex) {
			if (ex.getNestedException() != null && ex.getNestedException() instanceof UnknownHostException) {
				loadCep.setResultText("Site não encontrado.");
				loadCep.setResulCode(-14);
			} else {
				loadCep.setResultText("Nao foi possivel ler o documento xml.");
				loadCep.setResulCode(-15);
			}
			loadCep.setExceptio((Exception) ex);
		} catch (MalformedURLException ex) {
			loadCep.setExceptio(ex);
			loadCep.setResultText("Erro na formação da url.");
			loadCep.setResulCode(-16);
		} catch (Exception ex) {
			loadCep.setExceptio(ex);
			loadCep.setResultText("Erro inesperado.");
			loadCep.setResulCode(-17);
		}
		return loadCep;
	}

	private Cep(String cep) {
		this.cep = cep;
	}

	private void setExceptio(Exception ex) {
		this.exception = ex;
	}

	private void setCidade(String cidade) {
		this.cidade = cidade;
	}

	private void setBairro(String bairro) {
		this.bairro = bairro;
	}

	private void setLogradouroType(String logradouroType) {
		this.logradouroType = logradouroType;
	}

	private void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	private void setResulCode(int resultado) {
		this.resulCode = resultado;
	}

	private void setResultText(String resultado_txt) {
		this.resultText = resultado_txt;
	}

	private void setUf(String uf) {
		this.uf = uf;
	}

	public int getResulCode() {
		return this.resulCode;
	}

	public String getResultText() {
		return this.resultText;
	}

	public boolean wasSuccessful() {
		return this.resulCode == 1 && this.exception == null;
	}

	public boolean isCepNotFound() {
		return this.resulCode == 0;
	}

	public boolean hasException() {
		return this.exception != null;
	}

	public Exception getException() {
		return this.exception;
	}

	public String getBairro() {
		return this.bairro;
	}

	public String getCidade() {
		return this.cidade;
	}

	public String getUf() {
		return this.uf;
	}

	public String getLogradouro() {
		return this.logradouro;
	}

	public String getLogradouroFull() {
		return this.logradouro == null || this.logradouroType == null
				? null
				: this.logradouroType + " " + this.logradouro;
	}

	public String getLogradouroType() {
		return this.logradouroType;
	}

	public String getCep() {
		return this.cep;
	}

	private static class XmlEnums {
		private HashMap<String, Xml> enumsMap;

		public XmlEnums() {
			this.initializeEnums();
		}

		private void initializeEnums() {
			Xml[] enums = Xml.class.getEnumConstants();
			this.enumsMap = new HashMap();
			for (int i = 0; i < enums.length; ++i) {
				this.enumsMap.put(enums[i].name().toLowerCase(), enums[i]);
			}
		}

		public Xml getXml(String xmlName) {
			return this.enumsMap.get(xmlName.toLowerCase());
		}
	}

	private static class IterableElement implements Iterable<Element> {
		private Iterator<Element> itr;

		public IterableElement(Iterator itr) {
			this.itr = itr;
		}

		@Override
		public Iterator<Element> iterator() {
			return this.itr;
		}
	}

	private static enum Xml {
		CIDADE {

			@Override
			public void setCep(String text, Cep webServiceCep) {
				webServiceCep.setCidade(text);
			}
		},
		BAIRRO {

			@Override
			public void setCep(String text, Cep webServiceCep) {
				webServiceCep.setBairro(text);
			}
		},
		TIPO_LOGRADOURO {

			@Override
			public void setCep(String text, Cep webServiceCep) {
				webServiceCep.setLogradouroType(text);
			}
		},
		LOGRADOURO {

			@Override
			public void setCep(String text, Cep webServiceCep) {
				webServiceCep.setLogradouro(text);
			}
		},
		RESULTADO {

			@Override
			public void setCep(String text, Cep webServiceCep) {
				webServiceCep.setResulCode(Integer.parseInt(text));
			}
		},
		RESULTADO_TXT {

			@Override
			public void setCep(String text, Cep webServiceCep) {
				webServiceCep.setResultText(text);
			}
		},
		UF {

			@Override
			public void setCep(String text, Cep webServiceCep) {
				webServiceCep.setUf(text);
			}
		};

		private Xml() {
		}

		public abstract void setCep(String var1, Cep var2);

	}

}
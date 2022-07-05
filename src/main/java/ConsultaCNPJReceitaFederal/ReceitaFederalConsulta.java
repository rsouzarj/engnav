
package ConsultaCNPJReceitaFederal;

/**
 *
 * @author beto
 */

import Parceiro.Parceiro;


public class ReceitaFederalConsulta {

	private String captchaBase64;
	private byte[] captcha;
	private String respostaCaptcha;
	
	private String htmlResultaConsulta;
	private Parceiro parceiro;
	
	public String getCaptchaBase64() {
		return captchaBase64;
	}
	
	public void setCaptchaBase64(String captchaBase64) {
		this.captchaBase64 = captchaBase64;
	}
	
	public void setCaptcha(byte[] captcha) {
		this.captcha = captcha;
	}

	public byte[] getCaptcha() {
		return this.captcha;
	}
	
	public String getRespostaCaptcha() {
		return respostaCaptcha;
	}
	
	public void setRespostaCaptcha(String respostaCaptcha) {
		this.respostaCaptcha = respostaCaptcha;
	}
	
	public String getHtmlResultaConsulta() {
		return htmlResultaConsulta;
	}
	
	public void setHtmlResultaConsulta(String htmlResultaConsulta) {
		this.htmlResultaConsulta = htmlResultaConsulta;
	}
	
	public Parceiro getParceiro() {
		return parceiro;
	}
	
	public void setFornecedor(Parceiro parceiro) {
		this.parceiro = parceiro;
	}
}
package com.lovelacetecnologia.model;

public class EnviarEmail {

	private String mensagem;
	private String assunto;
	private String para;

	public EnviarEmail(String mensagem, String assunto, String para) {
		this.mensagem = mensagem;
		this.assunto = assunto;
		this.para = para;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

}

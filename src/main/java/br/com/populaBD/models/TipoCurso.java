package br.com.populaBD.models;

public enum TipoCurso {
	
	ALURA ("Alura"),
	CAELUM ("Caelum");
	
	private String nome;
	
	TipoCurso(String nome){
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}

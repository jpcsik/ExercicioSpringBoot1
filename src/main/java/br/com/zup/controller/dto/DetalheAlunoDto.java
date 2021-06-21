package br.com.zup.controller.dto;

import br.com.zup.modelo.Aluno;

public class DetalheAlunoDto {

	private String nome;
	private String email;

	public DetalheAlunoDto(Aluno aluno) {
		this.nome = aluno.getNome();
		this.email = aluno.getEmail();
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

}

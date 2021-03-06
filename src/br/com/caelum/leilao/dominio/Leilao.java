package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}
	
	public void propoe(Lance lance) {
		
		long total = qtdDeLancesDo(lance.getUsuario());
		
		if(lances.isEmpty() || podeDarLance(lance.getUsuario()) ) {
			lances.add(lance);
		}
	}

	private boolean podeDarLance(Usuario usuario) {
		return !ultimoLanceDado().getUsuario().equals(usuario) && qtdDeLancesDo(usuario) < 5;
	}

	private long qtdDeLancesDo(Usuario usuario) {
		return lances.stream().map(Lance::getUsuario).filter((user) -> user.equals(usuario)).count();
	}

	private Lance ultimoLanceDado() {
		return lances.get(lances.size() - 1);
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public List<Lance> getLances(){
		return Collections.unmodifiableList(lances);
	}
}

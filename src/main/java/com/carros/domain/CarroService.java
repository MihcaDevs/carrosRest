package com.carros.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository rep;
	
	public Iterable<Carro> getCarros(){
		return rep.findAll();
		
	}
	
	public Optional<Carro> getCarroById(Long id) {
		return rep.findById(id);
	}
	
	public Iterable<Carro> getCarroByTipo(String tipo) {
		return rep.findByTipo(tipo);
	}
	
	public Carro insert(Carro carro) {
		Assert.isNull(carro.getId(), "Não foi possível atualizar o registro");
		
		return rep.save(carro);
	}
	
	public Carro update(Carro carro, Long id) {
		Assert.notNull(id, "Não foi possivel atualizar o registro");
		
		//Busca o carro no banco de dados
		Optional<Carro> optional = getCarroById(id);
		if(optional.isPresent()) {
			Carro db = optional.get();
			
		//Copiar as propriedades
			db.setName(carro.getName());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id " + db.getId());
			
			//Atualiza o carro
			rep.save(db);
			
			return db;
			
		} else {
			throw new RuntimeException("Não foi possivel atualizar o registro");
		}
	}
	
	public void delete(Long id) {
		//Optional<Carro> carro = getCarroById(id);
		//if(Carro.isPresent()) {
			rep.deleteById(id);
		}
		
	}
	
	//public Carro save(Carro carro) {
		//return rep.save(carro);
	//}
	
	


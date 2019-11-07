package com.apicampanha.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apicampanha.dto.EnvioDTO;
import com.apicampanha.model.Campanha;
import com.apicampanha.model.Envio;
import com.apicampanha.repository.CampanhaRepository;
import com.apicampanha.repository.EnvioRepository;

import javassist.NotFoundException;

@RestController
@RequestMapping("/envio-api")
public class EnvioController {
	
	@Autowired
	CampanhaRepository campanhaRepository;
	
	@Autowired
	EnvioRepository envioRepository;
	
	@PostMapping
	public ResponseEntity<Envio> saveEnvio(@RequestBody EnvioDTO input) throws NotFoundException {
		Campanha campanha = campanhaRepository.findById(input.getId_campanha())
				.orElseThrow(() -> new NotFoundException("Campanha não encontrada com o id: " + input.getId_campanha()));
		
		Envio envio = new Envio();
		envio.setCampanha(campanha);
		envio.setCelular(input.getCelular());
		envio.setResposta(input.getResposta());
		envio.setDt_resposta(input.getDt_resposta());
		envio.setStatus(input.getStatus());
		envio.setDt_status(input.getDt_status());
		return new ResponseEntity<>(
				envioRepository.save(envio),
				HttpStatus.CREATED
		);
	}
	
	@GetMapping
	public ResponseEntity<List<Envio>> getAllEnvio() {
		return new ResponseEntity<>(
				envioRepository.findAll(),
				HttpStatus.OK
		);
	}
	
	@GetMapping("/get-envio/{id}")
	public ResponseEntity<Optional<Envio>> getAllEnvio(@PathVariable Long id) {
		return new ResponseEntity<>(
				envioRepository.findById(id),
				HttpStatus.OK
		);
	}
	
	
}

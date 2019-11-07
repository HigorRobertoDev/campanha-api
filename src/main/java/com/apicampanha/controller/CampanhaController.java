package com.apicampanha.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.apicampanha.dto.CampanhaDTO;
import com.apicampanha.model.Campanha;
import com.apicampanha.model.Envio;
import com.apicampanha.repository.CampanhaRepository;
import com.apicampanha.repository.EnvioRepository;

@RestController
@RequestMapping("campanha-api")
public class CampanhaController {
	
	@Autowired
	CampanhaRepository campanhaRepository;
	
	@Autowired
	EnvioRepository envioRepository;
	
	@GetMapping
	public ResponseEntity<List<Campanha>> getAll() {
		return new ResponseEntity<>(
				campanhaRepository.findAll(),
				HttpStatus.OK
		);
	}
	
	@GetMapping("/get-campanha/{id}")
	public ResponseEntity<Optional<Campanha>> getCampanhaById(@PathVariable Long id) {
		return new ResponseEntity<>(
				campanhaRepository.findById(id),
				HttpStatus.OK
		);
	}
	
	@GetMapping("all-envio")
	public ResponseEntity<List<Envio>> getAllEnvio() {
		return new ResponseEntity<>(
				envioRepository.findAll(),
				HttpStatus.OK
		);
	}
	
	@PostMapping
	public ResponseEntity<Campanha> saveCampanha(@RequestBody CampanhaDTO input) {
		Campanha campanha = new Campanha();
		campanha.setId_cli(input.getId_cli());
		campanha.setNome_campanha(input.getNome_campanha());
		campanha.setDt_inicio(input.getDt_inicio());
		campanha.setDt_fim(input.getDt_fim());
		campanha.setHr_inicio(input.getHr_inicio());
		campanha.setHr_fim(input.getHr_fim());
		
		return new ResponseEntity<>(
				campanhaRepository.save(campanha),
				HttpStatus.CREATED
		);
	}
	
	@PostMapping("/save-campanha-envio")
	public ResponseEntity<Campanha> saveCampanhaEnvio(@RequestBody CampanhaDTO input) {
		Campanha campanha = new Campanha();
		campanha.setId_cli(input.getId_cli());
		campanha.setNome_campanha(input.getNome_campanha());
		campanha.setDt_inicio(input.getDt_inicio());
		campanha.setDt_fim(input.getDt_fim());
		campanha.setHr_inicio(input.getHr_inicio());
		campanha.setHr_fim(input.getHr_fim());
		
		Campanha response = campanhaRepository.save(campanha);
		
		List<Envio> envios = new ArrayList<>();
		input.getEnvios().stream()
			.forEach(envio -> {
				Envio envioObj = new Envio();
				envioObj.setCelular(envio.getCelular());
				envioObj.setCampanha(response);
				envioObj.setStatus(envio.getStatus());
				envioObj.setDt_status(envio.getDt_status());
				envioObj.setResposta(envio.getResposta());
				envioObj.setDt_resposta(envio.getDt_resposta());
				envios.add(envioObj);
			});
		
		List<Envio> enviosResponse = envioRepository.saveAll(envios);
		response.setEnvios(enviosResponse);
		return new ResponseEntity<>(
				response,
				HttpStatus.CREATED
		);
	}
	
	
	
}

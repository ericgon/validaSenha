package br.com.eric.validasenhaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eric.validasenhaapi.entity.Senha;
import br.com.eric.validasenhaapi.service.ValidaSenhaService;


@RestController
@RequestMapping("/v1/senha")
public class ValidaSenhaController {

	@Autowired
	private ValidaSenhaService senhaService;

	/**
	 * Verifica uma senha
	 * metodo Post
	 *
	 */
	@PostMapping(consumes = "application/json")
	public ResponseEntity<?> validaSenha(@RequestBody Senha senha) {
	
       return new ResponseEntity<Boolean>(senhaService.validaSenha(senha.getSenha()), HttpStatus.OK);

	}

	
}
package br.com.eric.validasenhaapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.eric.validasenhaapi.entity.Senha;
import br.com.eric.validasenhaapi.service.ValidaSenhaService;

@SpringBootTest
@AutoConfigureMockMvc
class ValidaSenhaApiApplicationTests {
	
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ValidaSenhaService validaSenhaService;

	@Test
	void contextLoads() {
		
	}
	
	@Test
	void validaSenhaServiceTamanhoTest() throws Exception{
		
		Senha senhaOk = new Senha();
		senhaOk.setSenha("Ae163-sdg");
		
		Senha senhaNotOk = new Senha();
		senhaNotOk.setSenha("Ae163-sd");
		
	    boolean validacaoRetornoOk = validaSenhaService.validaSenha(senhaOk.getSenha());
	    boolean validacaoRetornoNotOk = validaSenhaService.validaSenha(senhaNotOk.getSenha());

	    Assertions.assertEquals(validacaoRetornoOk, true);
	    Assertions.assertEquals(validacaoRetornoNotOk, false);
			
	}

	@Test
	void validaSenhaLetraminusculaTest() throws Exception{
		
		Senha senhaOk = new Senha();
		senhaOk.setSenha("Ae16395-sdg");
		
		Senha senhaNotOk = new Senha();
		senhaNotOk.setSenha("AE16395-SDG");
		
	    boolean validacaoRetornoOk = validaSenhaService.validaSenha(senhaOk.getSenha());
	    boolean validacaoRetornoNotOk = validaSenhaService.validaSenha(senhaNotOk.getSenha());

	    Assertions.assertEquals(validacaoRetornoOk, true);
	    Assertions.assertEquals(validacaoRetornoNotOk, false);
			
	}

	@Test
	void validaSenhaLetraMaiusculaTest() throws Exception{
		
		Senha senhaOk = new Senha();
		senhaOk.setSenha("Ae16395-sdg");
		
		Senha senhaNotOk = new Senha();
		senhaNotOk.setSenha("ae1685-sdg");
		
	    boolean validacaoRetornoOk = validaSenhaService.validaSenha(senhaOk.getSenha());
	    boolean validacaoRetornoNotOk = validaSenhaService.validaSenha(senhaNotOk.getSenha());

	    Assertions.assertEquals(validacaoRetornoOk, true);
	    Assertions.assertEquals(validacaoRetornoNotOk, false);
			
	}


	@Test
	void validaSenhaCaracterRepetidoTest() throws Exception{
		
		Senha senhaOk = new Senha();
		senhaOk.setSenha("Ae16395-sdg");
		
		Senha senhaNotOk = new Senha();
		senhaNotOk.setSenha("Ae16395-sdg5");
		
	    boolean validacaoRetornoOk = validaSenhaService.validaSenha(senhaOk.getSenha());
	    boolean validacaoRetornoNotOk = validaSenhaService.validaSenha(senhaNotOk.getSenha());

	    Assertions.assertEquals(validacaoRetornoOk, true);
	    Assertions.assertEquals(validacaoRetornoNotOk, false);
			
	}

	@Test
	void validaSenhaCaracterEspecialTest() throws Exception{
		
		Senha senhaOk = new Senha();
		senhaOk.setSenha("Ae16395-sdg");
		
		Senha senhaNotOk = new Senha();
		senhaNotOk.setSenha("Ae16395sdg");
		
	    boolean validacaoRetornoOk = validaSenhaService.validaSenha(senhaOk.getSenha());
	    boolean validacaoRetornoNotOk = validaSenhaService.validaSenha(senhaNotOk.getSenha());

	    Assertions.assertEquals(validacaoRetornoOk, true);
	    Assertions.assertEquals(validacaoRetornoNotOk, false);
			
	}
	
	@Test 
	void validaSenhaApiTestRestOK() throws Exception{
		  
		Senha senhaOk = new Senha();
		senhaOk.setSenha("Ae163-95sdg");
		
		mockMvc.perform(post("/v1/senha") .contentType("application/json")
		.content(objectMapper.writeValueAsString(senhaOk)))
		.andExpect(status().isOk())
        .andExpect(content().string("true"));

	}

	@Test 
	void validaSenhaApiTestRestNotOK() throws Exception{
		  
		Senha senhaOk = new Senha();
		senhaOk.setSenha("ae163-95sdg");
		
		mockMvc.perform(post("/v1/senha") .contentType("application/json")
		.content(objectMapper.writeValueAsString(senhaOk)))
		.andExpect(status().isOk())
        .andExpect(content().string("false"));

	}

}

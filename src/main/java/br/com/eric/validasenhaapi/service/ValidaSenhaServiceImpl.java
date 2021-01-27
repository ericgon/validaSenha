package br.com.eric.validasenhaapi.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

@Service
public class ValidaSenhaServiceImpl implements ValidaSenhaService{
		
	
	/**
	 * Valida uma senha de acordo com os seguintes requisitos:
	 * Nove ou mais caracteres
	 * Ao menos 1 dígito
	 * Ao menos 1 letra minúscula
	 * Ao menos 1 letra maiúscula
	 * Ao menos 1 caractere especial
	 * Considere como especial os seguintes caracteres: !@#$%^&*()-+
	 * Não possuir caracteres repetidos dentro do conjunto
	 * 
	 */
	@Override
	public boolean validaSenha(String senha) {
		
		// remove os espaços em branco
		senha = senha.replace(" ","");
		//verifica se a senha tem ao menos 9 caracteres
		if(senha.length() >= 9) {
			//valida as condições para uma senha dentro do padrao estabelecido
			return isOneNumber(senha) && isOneLetterUpper(senha) && isOneLetterLower(senha) && isOneEspecial(senha) && isNotRepet(senha);   
			
		}
				
		return false;
		
	}

	//verifica se a senha tem ao menos 1 numero
	private boolean isOneNumber(String senha) {

		 return senha.matches(".*[0-9].*");
		
	}

	//verifica se a senha não tem caracter repetido
	private boolean isNotRepet(String senha) {
		
		Map<String, Integer> map = new TreeMap<>();                
        for(char item : senha.toLowerCase().toCharArray())
        {
            Integer s = map.get(String.valueOf(item));
            if (s != null)
            {
                return false;
            }
            else
            {
                map.put(String.valueOf(item), 1);
            }
        }
		
		return true;		
	}

	//verificar se a senha tem ao menos 1 letra maiuscula
	private boolean isOneLetterUpper(String senha) {
		
		return senha.matches(".*[A-Z].*");
	}

	//verifica se a senha tem ao menos 1 letra minuscula
	private boolean isOneLetterLower(String senha) {
		
		return senha.matches(".*[a-z].*");
	}

	//verifica se a senha tem ao menos 1 caracter especial
	private boolean isOneEspecial(String senha) {
	    
		List<String> specialChars = Arrays.asList("!","@","#","$","%","^","&","*","(",")","-","+");

        for (String item: specialChars){
            if(senha.contains(item)){
                return true;
            }
        }
		
        return false;
	}
	
}

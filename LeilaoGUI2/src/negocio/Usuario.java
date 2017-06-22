package negocio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario {
	String nome,cpf,cnpj,email;

public Usuario (String nome,String cpf,String email){
	this.nome = nome;
	this.cpf = cpf;
	this.email = email;
}

@Override
public boolean equals(Object v) { //contains original bugado
      boolean retVal = false;
      if (v instanceof Usuario){
          Usuario ptr = (Usuario) v;
          retVal = (ptr.getCpf().toString().contentEquals(this.getCpf().toString())) || ptr.getEmail().toString().contentEquals(this.getEmail().toString());
      }

   return retVal;
}

public boolean validateEmail(String emailStr) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(emailStr);
        return m.matches();
 }

public boolean validateCpf(String cpf){
	if(!cpf.matches("[0-9]+") || cpf.length()!=11){
		return false;
	}
	return true;
}

public boolean validacaoUsuario(String cpf, String email) {
	if(this.validateCpf(cpf) && this.validateEmail(email)){
		return true;
	}
	return false;
}


public String getNome(){
	return nome;
}

public String getCnpj() {
	return cnpj;
}

public String getEmail() {
	return email;
}

public String getCpf() {
	return cpf;
}


}

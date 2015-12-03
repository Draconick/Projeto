package brandroid.um.capitulo.projeto.modelo;

import java.util.Date;

/**
 * Created by Katrina on 18/11/2015.
 */
public class Pessoa {
    private String user;
    private String senha;
    private String nome;
    private String email;
    private String telefone;
    private String datanasc;


    public Pessoa(String user, String senha, String nome, String email, String telefone, String datanasc) {
        this.user = user;
        this.senha = senha;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.datanasc = datanasc;
    }

    public Pessoa(String user, String senha) {
        this.user = user;
        this.senha = senha;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(String datanasc) {
        this.datanasc = datanasc;
    }
}

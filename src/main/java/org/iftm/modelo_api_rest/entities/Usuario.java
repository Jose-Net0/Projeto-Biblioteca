package org.iftm.modelo_api_rest.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

@Entity
@Table(name="tb_usuario")
@JsonIgnoreProperties({"emprestimos"})
// Representa usuários da biblioteca (dados pessoais, autenticação e vínculo com regras)
public class Usuario {

    // Primary key: identificador do usuário
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="codigo_usuario")
    private Long codigoUsuario;

    // Informações pessoais
    private String nome;
    private String cpf;
    private String matricula;
    private String email;
    private String senha;

    // Classificação do usuário (ex: ALUNO, SERVIDOR)
    @Column (name="tipo_usuario")
    private String tipoUsuario;

    // Credenciais e controle de tentativas de login
    private String login;

    @Column(name = "login_attempts", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int loginAttempts = 0;

    private Date lockoutUntil;

    // Contato e endereço
    private String telefone;
    private String endereco;
    private String status = "ATIVO";

    // Relacionamento: um usuário pode ter vários empréstimos
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Emprestimo> emprestimos = new ArrayList<>();

    // Regra de empréstimo aplicada ao usuário (limites e prazos)
    @ManyToOne
    @JoinColumn(name = "fk_regra", referencedColumnName = "codigo_regra_emprestimo")
    private RegraEmprestimo regraEmprestimo;

    // Bloqueio associado, quando aplicável (motivo e período)
    @ManyToOne
    @JoinColumn(name = "fk_bloqueio", referencedColumnName = "codigo_bloqueio")
    private Bloqueio bloqueio;

    public Usuario() {
    }

    public Usuario(Long codigoUsuario, String nome, String cpf, String matricula, 
                   String email, String senha, String tipoUsuario) {
        this.codigoUsuario = codigoUsuario;
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    public Date getLockoutUntil() {
        return lockoutUntil;
    }

    public void setLockoutUntil(Date lockoutUntil) {
        this.lockoutUntil = lockoutUntil;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public Bloqueio getBloqueio() {
        return bloqueio;
    }

    public void setBloqueio(Bloqueio bloqueio) {
        this.bloqueio = bloqueio;
    }

    public RegraEmprestimo getRegraEmprestimo() {
        return regraEmprestimo;
    }

    public void setRegraEmprestimo(RegraEmprestimo regraEmprestimo) {
        this.regraEmprestimo = regraEmprestimo;
    }

}



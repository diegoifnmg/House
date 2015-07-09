package br.com.diegoalvescosta.tcc.Controller;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.diegoalvescosta.tcc.DomainModel.Pessoa;
import br.com.diegoalvescosta.tcc.Infraestrutura.PessoaDAO;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Diego
 */
@ManagedBean(name = "pessoaController")
@SessionScoped
public class PessoaController implements Serializable {

    /**
     * Creates a new instance of PessoaController
     */
    @EJB
    PessoaDAO dao;
    private Pessoa entidade,filtro;
    
    private List<Pessoa> listagem;

    public PessoaController() {

        entidade = new Pessoa();
        filtro = new Pessoa();
    }
    
    public void filtrar(){
        listagem = dao.Buscar(filtro);
    }

    public String novo(){
        entidade = new Pessoa();
        return "editarPessoa.xhtml";
    }
    
    public String listagem(){
        entidade = new Pessoa();
        return "listagemPessoa.xhtml";
    }
    
    
    public PessoaDAO getDao() {
        return dao;
    }

    public void setDao(PessoaDAO dao) {
        this.dao = dao;
    }

    public Pessoa getEntidade() {
        return entidade;
    }

    public void setEntidade(Pessoa entidade) {
        this.entidade = entidade;
    }

    public Pessoa getFiltro() {
        return filtro;
    }

    public void setFiltro(Pessoa filtro) {
        this.filtro = filtro;
    }
    
    public String abrir() {
        return "editarPessoa.xhtml";
    }

    public String cancelar() {
        return "listagemPessoa.xhtml";
    }

    public String editar() {
        return "editarPessoa.xhtml";
    }

    public List<Pessoa> getListagem() {
        if(listagem == null){
            listagem = dao.Buscar(null);
        }
        return listagem;
    }
    
    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void salvar() {
        if (dao.Salvar(entidade)) {
            exibirMensagem("Salvo com sucesso!");
            entidade = new Pessoa();
        } else {
            exibirMensagem("Falha!");
        }
    }
    
    public String excluir() {
        if (dao.Apagar(entidade)) {
            exibirMensagem("Excluido com sucesso!");
            listagem = null;
            return "listagemPessoa.xhtml";
        } else {
            return "";
        }
    }
}

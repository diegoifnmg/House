/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diegoalvescosta.tcc.Controller;


import br.com.diegoalvescosta.tcc.DomainModel.IPessoaRepositorio;
import br.com.diegoalvescosta.tcc.DomainModel.Pessoa;
import java.io.Serializable;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Maike Jordan
 */
@Named(value = "pessoaConverter")
@SessionScoped
public class PessoaConverter implements Serializable, Converter {

    /**
     * Creates a new instance of ExpedienteConverter
     */
    @EJB
    IPessoaRepositorio dao;
    @PersistenceContext(unitName = "HousePU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    public PessoaConverter() {
    }
    
    
    

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().equals("")) {
            return null;
        } else {
            Long id = Long.parseLong(value);
            return dao.Abrir(id);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.toString().equals("")) {
            return "";
        } else {
            Pessoa f = (Pessoa) value;
            return f.getId().toString();
        }
    }

    

}

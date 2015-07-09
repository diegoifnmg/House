/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diegoalvescosta.tcc.Infraestrutura;

import br.com.diegoalvescosta.tcc.DomainModel.Pessoa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author diego-dell
 */
@Stateless
public class PessoaDAO extends GenericoDAO<Pessoa>{

    public PessoaDAO() {
        super(Pessoa.class);
    }

    @Override
    public List<Pessoa> Buscar(Pessoa obj) {
        
        String Consulta = "select l from Pessoa l WHERE l.ativo = 1 AND l.id != 0";
        
        String filtro = " ";
        
        if (obj != null) {
            //Consulta = Consulta + " where l.nome like '%" + obj.getNome() + "%'";
            
            //Consulta pelo Nome
            if (obj.getNome() != null && obj.getNome().length() > 0) {
                filtro += " AND l.nome like '%" + obj.getNome() + "%' ";

            }
            
            // Se houver filtros, coloca o "where" na consulta
            if (filtro.length() > 0) {
                Consulta += filtro;
            }
            
        }
        
        // Cria a consulta no JPA
        Query query = manager.createQuery(Consulta);

        // Executa a consulta
        return query.getResultList();
       
    }

    @Override
    public boolean Apagar(Pessoa obj) {
        try {
            Query query = manager.createQuery("Update Pessoa p set p.ativo = 0 WHERE p.id =:id");
            query.setParameter("id", obj.getId());
            query.executeUpdate();

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }
    
}

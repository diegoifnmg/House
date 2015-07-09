/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.diegoalvescosta.tcc.Infraestrutura;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Diego Alves
 */
public abstract class GenericoDAO<T> {

    @PersistenceContext(name = "EcoHousePU")
    protected EntityManager manager;
    private Class tipo;

    public GenericoDAO(Class t) {
        tipo = t;
    }

    public boolean Salvar(T obj) {
        try {
            //salva o objeto
            manager.merge(obj);
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public T Abrir(Long id) {
        try {
            T obj = (T) manager.find(tipo, id);
            return obj;
            //abrir
        } catch (Exception ex) {
            return null;
        }
    }

    public abstract List<T> Buscar(T obj);

    public boolean Apagar(T obj) {
        try {
            manager.remove(manager.merge(obj));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}

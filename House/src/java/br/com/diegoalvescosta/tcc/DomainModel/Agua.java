package br.com.diegoalvescosta.tcc.DomainModel;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author diego-dell
 */
@Entity
public class Agua implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUso;
    
    //Dado salvo em Litros/Minuto
    @Column(name = "dado", length = 255)
    private Float dado;
    
    @Column(name = "ativo", length = 255)
    private int ativo;
    
}

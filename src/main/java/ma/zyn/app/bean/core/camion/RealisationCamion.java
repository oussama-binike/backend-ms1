package ma.zyn.app.bean.core.camion;

import java.util.List;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.bean.core.referentiel.Produit;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "realisation_camion")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="realisation_camion_seq",sequenceName="realisation_camion_seq",allocationSize=1, initialValue = 1)
public class RealisationCamion  extends BaseEntity     {




    @Column(length = 500)
    private String libelle;

    private String description;

    private LocalDateTime jour ;

    private BigDecimal nombreCamions = BigDecimal.ZERO;

    private BigDecimal dureeMoyenneTransport = BigDecimal.ZERO;

    private BigDecimal totalTms = BigDecimal.ZERO;

    private ProvennanceCamion provennanceCamion ;
    private DestinationCamion destinationCamion ;

    private List<RealisationCamionProduit> realisationCamionProduits ;

    public RealisationCamion(){
        super();
    }

    public RealisationCamion(Long id){
        this.id = id;
    }

    public RealisationCamion(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public RealisationCamion(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="realisation_camion_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
      @Column(columnDefinition="TEXT")
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public LocalDateTime getJour(){
        return this.jour;
    }
    public void setJour(LocalDateTime jour){
        this.jour = jour;
    }
    public BigDecimal getNombreCamions(){
        return this.nombreCamions;
    }
    public void setNombreCamions(BigDecimal nombreCamions){
        this.nombreCamions = nombreCamions;
    }
    public BigDecimal getDureeMoyenneTransport(){
        return this.dureeMoyenneTransport;
    }
    public void setDureeMoyenneTransport(BigDecimal dureeMoyenneTransport){
        this.dureeMoyenneTransport = dureeMoyenneTransport;
    }
    public BigDecimal getTotalTms(){
        return this.totalTms;
    }
    public void setTotalTms(BigDecimal totalTms){
        this.totalTms = totalTms;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provennance_camion")
    public ProvennanceCamion getProvennanceCamion(){
        return this.provennanceCamion;
    }
    public void setProvennanceCamion(ProvennanceCamion provennanceCamion){
        this.provennanceCamion = provennanceCamion;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_camion")
    public DestinationCamion getDestinationCamion(){
        return this.destinationCamion;
    }
    public void setDestinationCamion(DestinationCamion destinationCamion){
        this.destinationCamion = destinationCamion;
    }
    @OneToMany(mappedBy = "realisationCamion")
    public List<RealisationCamionProduit> getRealisationCamionProduits(){
        return this.realisationCamionProduits;
    }

    public void setRealisationCamionProduits(List<RealisationCamionProduit> realisationCamionProduits){
        this.realisationCamionProduits = realisationCamionProduits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealisationCamion realisationCamion = (RealisationCamion) o;
        return id != null && id.equals(realisationCamion.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


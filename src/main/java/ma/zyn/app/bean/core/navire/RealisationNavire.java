package ma.zyn.app.bean.core.navire;

import java.util.List;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.bean.core.referentiel.ProduitMarchand;
import ma.zyn.app.bean.core.referentiel.Produit;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "realisation_navire")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="realisation_navire_seq",sequenceName="realisation_navire_seq",allocationSize=1, initialValue = 1)
public class RealisationNavire  extends BaseEntity     {




    @Column(length = 500)
    private String libelle;

    private String description;

    @Column(length = 500)
    private String numeroNavire;

    @Column(length = 500)
    private String numeroExpedition;

    private LocalDateTime jour ;

    private BigDecimal tauxCompletude = BigDecimal.ZERO;

    private BigDecimal tauxRemplissage = BigDecimal.ZERO;

    private LocalDateTime dateChargement ;

    private LocalDateTime dateFinChargement ;

    private DestinationNavire destinationNavire ;

    private List<RealisationNavireProduit> realisationNavireProduits ;
    private List<RealisationNavireQualite> realisationNavireQualites ;

    public RealisationNavire(){
        super();
    }

    public RealisationNavire(Long id){
        this.id = id;
    }

    public RealisationNavire(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public RealisationNavire(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="realisation_navire_seq")
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
    public String getNumeroNavire(){
        return this.numeroNavire;
    }
    public void setNumeroNavire(String numeroNavire){
        this.numeroNavire = numeroNavire;
    }
    public String getNumeroExpedition(){
        return this.numeroExpedition;
    }
    public void setNumeroExpedition(String numeroExpedition){
        this.numeroExpedition = numeroExpedition;
    }
    public LocalDateTime getJour(){
        return this.jour;
    }
    public void setJour(LocalDateTime jour){
        this.jour = jour;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_navire")
    public DestinationNavire getDestinationNavire(){
        return this.destinationNavire;
    }
    public void setDestinationNavire(DestinationNavire destinationNavire){
        this.destinationNavire = destinationNavire;
    }
    public BigDecimal getTauxCompletude(){
        return this.tauxCompletude;
    }
    public void setTauxCompletude(BigDecimal tauxCompletude){
        this.tauxCompletude = tauxCompletude;
    }
    public BigDecimal getTauxRemplissage(){
        return this.tauxRemplissage;
    }
    public void setTauxRemplissage(BigDecimal tauxRemplissage){
        this.tauxRemplissage = tauxRemplissage;
    }
    public LocalDateTime getDateChargement(){
        return this.dateChargement;
    }
    public void setDateChargement(LocalDateTime dateChargement){
        this.dateChargement = dateChargement;
    }
    public LocalDateTime getDateFinChargement(){
        return this.dateFinChargement;
    }
    public void setDateFinChargement(LocalDateTime dateFinChargement){
        this.dateFinChargement = dateFinChargement;
    }
    @OneToMany(mappedBy = "realisationNavire")
    public List<RealisationNavireProduit> getRealisationNavireProduits(){
        return this.realisationNavireProduits;
    }

    public void setRealisationNavireProduits(List<RealisationNavireProduit> realisationNavireProduits){
        this.realisationNavireProduits = realisationNavireProduits;
    }
    @OneToMany(mappedBy = "realisationNavire")
    public List<RealisationNavireQualite> getRealisationNavireQualites(){
        return this.realisationNavireQualites;
    }

    public void setRealisationNavireQualites(List<RealisationNavireQualite> realisationNavireQualites){
        this.realisationNavireQualites = realisationNavireQualites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealisationNavire realisationNavire = (RealisationNavire) o;
        return id != null && id.equals(realisationNavire.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


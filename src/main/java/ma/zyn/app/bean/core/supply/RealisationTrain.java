package ma.zyn.app.bean.core.supply;

import java.util.List;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.bean.core.referentiel.Train;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import ma.zyn.app.bean.core.referentiel.ProvennanceTrain;
import ma.zyn.app.bean.core.referentiel.DestinationTrain;
import ma.zyn.app.bean.core.referentiel.TypeWagon;
import ma.zyn.app.bean.core.referentiel.Produit;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "realisation_train")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="realisation_train_seq",sequenceName="realisation_train_seq",allocationSize=1, initialValue = 1)
public class RealisationTrain  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;

    private LocalDateTime jour ;

    private BigDecimal tauxCompletude = BigDecimal.ZERO;

    private BigDecimal tauxRemplissage = BigDecimal.ZERO;

    private BigDecimal tauxChargement = BigDecimal.ZERO;

    @Column(length = 500)
    private String tempsChargement;

    @Column(length = 500)
    private String tempsDechargement;

    private BigDecimal tempsTransite = BigDecimal.ZERO;

    @Column(columnDefinition = "boolean default false")
    private Boolean expedie = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean planifie = false;

    private ProvennanceTrain provennanceTrain ;
    private DestinationTrain destinationTrain ;
    private Train train ;
    private TypeWagon typeWagon ;
    private StadeOperatoire stadeOperatoire ;

    private List<RealisationTrainProduit> realisationTrainProduits ;

    public RealisationTrain(){
        super();
    }

    public RealisationTrain(Long id){
        this.id = id;
    }

    public RealisationTrain(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public RealisationTrain(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="realisation_train_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provennance_train")
    public ProvennanceTrain getProvennanceTrain(){
        return this.provennanceTrain;
    }
    public void setProvennanceTrain(ProvennanceTrain provennanceTrain){
        this.provennanceTrain = provennanceTrain;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_train")
    public DestinationTrain getDestinationTrain(){
        return this.destinationTrain;
    }
    public void setDestinationTrain(DestinationTrain destinationTrain){
        this.destinationTrain = destinationTrain;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train")
    public Train getTrain(){
        return this.train;
    }
    public void setTrain(Train train){
        this.train = train;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_wagon")
    public TypeWagon getTypeWagon(){
        return this.typeWagon;
    }
    public void setTypeWagon(TypeWagon typeWagon){
        this.typeWagon = typeWagon;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stade_operatoire")
    public StadeOperatoire getStadeOperatoire(){
        return this.stadeOperatoire;
    }
    public void setStadeOperatoire(StadeOperatoire stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
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
    public BigDecimal getTauxChargement(){
        return this.tauxChargement;
    }
    public void setTauxChargement(BigDecimal tauxChargement){
        this.tauxChargement = tauxChargement;
    }
    public String getTempsChargement(){
        return this.tempsChargement;
    }
    public void setTempsChargement(String tempsChargement){
        this.tempsChargement = tempsChargement;
    }
    public String getTempsDechargement(){
        return this.tempsDechargement;
    }
    public void setTempsDechargement(String tempsDechargement){
        this.tempsDechargement = tempsDechargement;
    }
    public BigDecimal getTempsTransite(){
        return this.tempsTransite;
    }
    public void setTempsTransite(BigDecimal tempsTransite){
        this.tempsTransite = tempsTransite;
    }
    public Boolean  getExpedie(){
        return this.expedie;
    }
    public void setExpedie(Boolean expedie){
        this.expedie = expedie;
    }
    public Boolean  getPlanifie(){
        return this.planifie;
    }
    public void setPlanifie(Boolean planifie){
        this.planifie = planifie;
    }
    @OneToMany(mappedBy = "realisationTrain")
    public List<RealisationTrainProduit> getRealisationTrainProduits(){
        return this.realisationTrainProduits;
    }

    public void setRealisationTrainProduits(List<RealisationTrainProduit> realisationTrainProduits){
        this.realisationTrainProduits = realisationTrainProduits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealisationTrain realisationTrain = (RealisationTrain) o;
        return id != null && id.equals(realisationTrain.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


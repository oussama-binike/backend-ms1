package  ma.zyn.app.ws.dto.supply;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zyn.app.ws.dto.referentiel.TrainDto;
import ma.zyn.app.ws.dto.referentiel.StadeOperatoireDto;
import ma.zyn.app.ws.dto.referentiel.ProvennanceTrainDto;
import ma.zyn.app.ws.dto.referentiel.DestinationTrainDto;
import ma.zyn.app.ws.dto.referentiel.TypeWagonDto;
import ma.zyn.app.ws.dto.referentiel.ProduitDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class RealisationTrainDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String description  ;
    private String jour ;
    private BigDecimal tauxCompletude  ;
    private BigDecimal tauxRemplissage  ;
    private BigDecimal tauxChargement  ;
    private String tempsChargement  ;
    private String tempsDechargement  ;
    private BigDecimal tempsTransite  ;
    private Boolean expedie  ;
    private Boolean planifie  ;

    private ProvennanceTrainDto provennanceTrain ;
    private DestinationTrainDto destinationTrain ;
    private TrainDto train ;
    private TypeWagonDto typeWagon ;
    private StadeOperatoireDto stadeOperatoire ;

    private List<RealisationTrainProduitDto> realisationTrainProduits ;


    public RealisationTrainDto(){
        super();
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


    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getJour(){
        return this.jour;
    }
    public void setJour(String jour){
        this.jour = jour;
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


    public Boolean getExpedie(){
        return this.expedie;
    }
    public void setExpedie(Boolean expedie){
        this.expedie = expedie;
    }


    public Boolean getPlanifie(){
        return this.planifie;
    }
    public void setPlanifie(Boolean planifie){
        this.planifie = planifie;
    }


    public ProvennanceTrainDto getProvennanceTrain(){
        return this.provennanceTrain;
    }

    public void setProvennanceTrain(ProvennanceTrainDto provennanceTrain){
        this.provennanceTrain = provennanceTrain;
    }
    public DestinationTrainDto getDestinationTrain(){
        return this.destinationTrain;
    }

    public void setDestinationTrain(DestinationTrainDto destinationTrain){
        this.destinationTrain = destinationTrain;
    }
    public TrainDto getTrain(){
        return this.train;
    }

    public void setTrain(TrainDto train){
        this.train = train;
    }
    public TypeWagonDto getTypeWagon(){
        return this.typeWagon;
    }

    public void setTypeWagon(TypeWagonDto typeWagon){
        this.typeWagon = typeWagon;
    }
    public StadeOperatoireDto getStadeOperatoire(){
        return this.stadeOperatoire;
    }

    public void setStadeOperatoire(StadeOperatoireDto stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
    }



    public List<RealisationTrainProduitDto> getRealisationTrainProduits(){
        return this.realisationTrainProduits;
    }

    public void setRealisationTrainProduits(List<RealisationTrainProduitDto> realisationTrainProduits){
        this.realisationTrainProduits = realisationTrainProduits;
    }



}

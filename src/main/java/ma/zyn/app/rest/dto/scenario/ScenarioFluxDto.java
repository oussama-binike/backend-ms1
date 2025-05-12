package  ma.zyn.app.ws.dto.scenario;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.ws.dto.referentiel.LiaisonDto;
import ma.zyn.app.ws.dto.referentiel.EtatDemandeDto;
import ma.zyn.app.ws.dto.referentiel.ProduitMarchandDto;
import ma.zyn.app.ws.dto.referentiel.TypeDemandeDto;
import ma.zyn.app.ws.dto.referentiel.StadeOperatoireDto;
import ma.zyn.app.ws.dto.planmaintenance.TauxRendementStadeOperatoireDto;
import ma.zyn.app.ws.dto.referentiel.StatusScenarioFluxDto;
import ma.zyn.app.ws.dto.demande.DemandeDto;
import ma.zyn.app.ws.dto.planmaintenance.PlanDisponibiliteDto;
import ma.zyn.app.ws.dto.referentiel.ClientDto;
import ma.zyn.app.ws.dto.stock.SuiviStockDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScenarioFluxDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String description  ;
    private String dateEffet ;

    private ExerciceDto exercice ;
    private StatusScenarioFluxDto statusScenarioFlux ;

    private List<DemandeDto> demandes ;
    private List<PlanDisponibiliteDto> planDisponibilites ;
    private List<TauxRendementStadeOperatoireDto> tauxRendementStadeOperatoires ;
    private List<SuiviStockDto> suiviStocks ;


    public ScenarioFluxDto(){
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
    public String getDateEffet(){
        return this.dateEffet;
    }
    public void setDateEffet(String dateEffet){
        this.dateEffet = dateEffet;
    }


    public ExerciceDto getExercice(){
        return this.exercice;
    }

    public void setExercice(ExerciceDto exercice){
        this.exercice = exercice;
    }
    public StatusScenarioFluxDto getStatusScenarioFlux(){
        return this.statusScenarioFlux;
    }

    public void setStatusScenarioFlux(StatusScenarioFluxDto statusScenarioFlux){
        this.statusScenarioFlux = statusScenarioFlux;
    }



    public List<DemandeDto> getDemandes(){
        return this.demandes;
    }

    public void setDemandes(List<DemandeDto> demandes){
        this.demandes = demandes;
    }
    public List<PlanDisponibiliteDto> getPlanDisponibilites(){
        return this.planDisponibilites;
    }

    public void setPlanDisponibilites(List<PlanDisponibiliteDto> planDisponibilites){
        this.planDisponibilites = planDisponibilites;
    }
    public List<TauxRendementStadeOperatoireDto> getTauxRendementStadeOperatoires(){
        return this.tauxRendementStadeOperatoires;
    }

    public void setTauxRendementStadeOperatoires(List<TauxRendementStadeOperatoireDto> tauxRendementStadeOperatoires){
        this.tauxRendementStadeOperatoires = tauxRendementStadeOperatoires;
    }
    public List<SuiviStockDto> getSuiviStocks(){
        return this.suiviStocks;
    }

    public void setSuiviStocks(List<SuiviStockDto> suiviStocks){
        this.suiviStocks = suiviStocks;
    }



}

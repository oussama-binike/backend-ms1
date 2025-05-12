package  ma.zyn.app.ws.dto.planmaintenance;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zyn.app.ws.dto.referentiel.StadeOperatoireDto;
import ma.zyn.app.ws.dto.scenario.ScenarioFluxDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlanDisponibiliteDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String description  ;
    private BigDecimal nombreHeureArret  ;
    private String dateArretDebut ;
    private String dateArretFin ;

    private StadeOperatoireDto stadeOperatoire ;
    private ScenarioFluxDto scenarioFlux ;



    public PlanDisponibiliteDto(){
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


    public BigDecimal getNombreHeureArret(){
        return this.nombreHeureArret;
    }
    public void setNombreHeureArret(BigDecimal nombreHeureArret){
        this.nombreHeureArret = nombreHeureArret;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateArretDebut(){
        return this.dateArretDebut;
    }
    public void setDateArretDebut(String dateArretDebut){
        this.dateArretDebut = dateArretDebut;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateArretFin(){
        return this.dateArretFin;
    }
    public void setDateArretFin(String dateArretFin){
        this.dateArretFin = dateArretFin;
    }


    public StadeOperatoireDto getStadeOperatoire(){
        return this.stadeOperatoire;
    }

    public void setStadeOperatoire(StadeOperatoireDto stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
    }
    public ScenarioFluxDto getScenarioFlux(){
        return this.scenarioFlux;
    }

    public void setScenarioFlux(ScenarioFluxDto scenarioFlux){
        this.scenarioFlux = scenarioFlux;
    }






}

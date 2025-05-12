package  ma.zyn.app.ws.dto.stock;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zyn.app.ws.dto.referentiel.LiaisonDto;
import ma.zyn.app.ws.dto.scenario.ScenarioFluxDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuiviStockDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String description  ;
    private BigDecimal volumeReel  ;
    private BigDecimal volumeEstime  ;
    private String dateFlux ;
    private Integer repereSourceDebut  = 0 ;
    private Integer repereSourceFin  = 0 ;
    private Integer repereDestinationDebut  = 0 ;
    private Integer repereDestinationFin  = 0 ;
    private Integer positionRouePelle  = 0 ;
    private Integer positionStacker  = 0 ;

    private LiaisonDto liaison ;
    private ScenarioFluxDto scenarioFlux ;



    public SuiviStockDto(){
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


    public BigDecimal getVolumeReel(){
        return this.volumeReel;
    }
    public void setVolumeReel(BigDecimal volumeReel){
        this.volumeReel = volumeReel;
    }


    public BigDecimal getVolumeEstime(){
        return this.volumeEstime;
    }
    public void setVolumeEstime(BigDecimal volumeEstime){
        this.volumeEstime = volumeEstime;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateFlux(){
        return this.dateFlux;
    }
    public void setDateFlux(String dateFlux){
        this.dateFlux = dateFlux;
    }


    public Integer getRepereSourceDebut(){
        return this.repereSourceDebut;
    }
    public void setRepereSourceDebut(Integer repereSourceDebut){
        this.repereSourceDebut = repereSourceDebut;
    }


    public Integer getRepereSourceFin(){
        return this.repereSourceFin;
    }
    public void setRepereSourceFin(Integer repereSourceFin){
        this.repereSourceFin = repereSourceFin;
    }


    public Integer getRepereDestinationDebut(){
        return this.repereDestinationDebut;
    }
    public void setRepereDestinationDebut(Integer repereDestinationDebut){
        this.repereDestinationDebut = repereDestinationDebut;
    }


    public Integer getRepereDestinationFin(){
        return this.repereDestinationFin;
    }
    public void setRepereDestinationFin(Integer repereDestinationFin){
        this.repereDestinationFin = repereDestinationFin;
    }


    public Integer getPositionRouePelle(){
        return this.positionRouePelle;
    }
    public void setPositionRouePelle(Integer positionRouePelle){
        this.positionRouePelle = positionRouePelle;
    }


    public Integer getPositionStacker(){
        return this.positionStacker;
    }
    public void setPositionStacker(Integer positionStacker){
        this.positionStacker = positionStacker;
    }


    public LiaisonDto getLiaison(){
        return this.liaison;
    }

    public void setLiaison(LiaisonDto liaison){
        this.liaison = liaison;
    }
    public ScenarioFluxDto getScenarioFlux(){
        return this.scenarioFlux;
    }

    public void setScenarioFlux(ScenarioFluxDto scenarioFlux){
        this.scenarioFlux = scenarioFlux;
    }






}

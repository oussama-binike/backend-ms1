package  ma.zyn.app.ws.dto.planmaintenance;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zyn.app.ws.dto.scenario.ScenarioFluxDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class TauxRendementStadeOperatoireDto  extends AuditBaseDto {

    private String jour ;
    private BigDecimal tauxRendementGlobal  ;

    private ScenarioFluxDto scenarioFlux ;



    public TauxRendementStadeOperatoireDto(){
        super();
    }




    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getJour(){
        return this.jour;
    }
    public void setJour(String jour){
        this.jour = jour;
    }


    public BigDecimal getTauxRendementGlobal(){
        return this.tauxRendementGlobal;
    }
    public void setTauxRendementGlobal(BigDecimal tauxRendementGlobal){
        this.tauxRendementGlobal = tauxRendementGlobal;
    }


    public ScenarioFluxDto getScenarioFlux(){
        return this.scenarioFlux;
    }

    public void setScenarioFlux(ScenarioFluxDto scenarioFlux){
        this.scenarioFlux = scenarioFlux;
    }






}

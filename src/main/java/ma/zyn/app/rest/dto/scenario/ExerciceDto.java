package  ma.zyn.app.ws.dto.scenario;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.ws.dto.referentiel.StatusExerciceDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExerciceDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String description  ;
    private Integer mois  = 0 ;
    private Integer annee  = 0 ;
    private String dateDebut ;
    private String dateFin ;
    private String dateRetrospective ;

    private StatusExerciceDto statusExercice ;



    public ExerciceDto(){
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


    public Integer getMois(){
        return this.mois;
    }
    public void setMois(Integer mois){
        this.mois = mois;
    }


    public Integer getAnnee(){
        return this.annee;
    }
    public void setAnnee(Integer annee){
        this.annee = annee;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateDebut(){
        return this.dateDebut;
    }
    public void setDateDebut(String dateDebut){
        this.dateDebut = dateDebut;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateFin(){
        return this.dateFin;
    }
    public void setDateFin(String dateFin){
        this.dateFin = dateFin;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateRetrospective(){
        return this.dateRetrospective;
    }
    public void setDateRetrospective(String dateRetrospective){
        this.dateRetrospective = dateRetrospective;
    }


    public StatusExerciceDto getStatusExercice(){
        return this.statusExercice;
    }

    public void setStatusExercice(StatusExerciceDto statusExercice){
        this.statusExercice = statusExercice;
    }






}

package  ma.zyn.app.ws.dto.aleas;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zyn.app.ws.dto.referentiel.StadeOperatoireDto;
import ma.zyn.app.ws.dto.reclamation.ActionEntrepriseDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArretNonPlanifieDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String commentaire  ;
    private BigDecimal dureeEstimee  ;
    private String dateArret ;
    private String dateDebut ;
    private String dateFin ;

    private StadeOperatoireDto stadeOperatoire ;
    private CauseArretDto causeArret ;
    private ActionEntrepriseDto actionEntreprise ;



    public ArretNonPlanifieDto(){
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


    public String getCommentaire(){
        return this.commentaire;
    }
    public void setCommentaire(String commentaire){
        this.commentaire = commentaire;
    }


    public BigDecimal getDureeEstimee(){
        return this.dureeEstimee;
    }
    public void setDureeEstimee(BigDecimal dureeEstimee){
        this.dureeEstimee = dureeEstimee;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateArret(){
        return this.dateArret;
    }
    public void setDateArret(String dateArret){
        this.dateArret = dateArret;
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


    public StadeOperatoireDto getStadeOperatoire(){
        return this.stadeOperatoire;
    }

    public void setStadeOperatoire(StadeOperatoireDto stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
    }
    public CauseArretDto getCauseArret(){
        return this.causeArret;
    }

    public void setCauseArret(CauseArretDto causeArret){
        this.causeArret = causeArret;
    }
    public ActionEntrepriseDto getActionEntreprise(){
        return this.actionEntreprise;
    }

    public void setActionEntreprise(ActionEntrepriseDto actionEntreprise){
        this.actionEntreprise = actionEntreprise;
    }






}

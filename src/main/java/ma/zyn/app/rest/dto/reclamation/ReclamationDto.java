package  ma.zyn.app.ws.dto.reclamation;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zyn.app.ws.dto.referentiel.ElementChimiqueDto;
import ma.zyn.app.ws.dto.referentiel.ProduitMarchandDto;
import ma.zyn.app.ws.dto.referentiel.EntiteDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReclamationDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String commentaire  ;
    private BigDecimal quantite  ;
    private Boolean fonde  ;
    private String motifReclamation  ;
    private String dateOccurence ;
    private String dateReception ;
    private String actionEntreprise  ;

    private EntiteDto entiteEmettrice ;
    private EntiteDto entiteDistinataire ;
    private ProduitMarchandDto produitMarchand ;
    private EtatReclamationDto etatReclamation ;

    private List<ReclamationElementChimiqueDto> reclamationElementChimiques ;


    public ReclamationDto(){
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


    public BigDecimal getQuantite(){
        return this.quantite;
    }
    public void setQuantite(BigDecimal quantite){
        this.quantite = quantite;
    }


    public Boolean getFonde(){
        return this.fonde;
    }
    public void setFonde(Boolean fonde){
        this.fonde = fonde;
    }


    public String getMotifReclamation(){
        return this.motifReclamation;
    }
    public void setMotifReclamation(String motifReclamation){
        this.motifReclamation = motifReclamation;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateOccurence(){
        return this.dateOccurence;
    }
    public void setDateOccurence(String dateOccurence){
        this.dateOccurence = dateOccurence;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateReception(){
        return this.dateReception;
    }
    public void setDateReception(String dateReception){
        this.dateReception = dateReception;
    }


    public String getActionEntreprise(){
        return this.actionEntreprise;
    }
    public void setActionEntreprise(String actionEntreprise){
        this.actionEntreprise = actionEntreprise;
    }


    public EntiteDto getEntiteEmettrice(){
        return this.entiteEmettrice;
    }

    public void setEntiteEmettrice(EntiteDto entiteEmettrice){
        this.entiteEmettrice = entiteEmettrice;
    }
    public EntiteDto getEntiteDistinataire(){
        return this.entiteDistinataire;
    }

    public void setEntiteDistinataire(EntiteDto entiteDistinataire){
        this.entiteDistinataire = entiteDistinataire;
    }
    public ProduitMarchandDto getProduitMarchand(){
        return this.produitMarchand;
    }

    public void setProduitMarchand(ProduitMarchandDto produitMarchand){
        this.produitMarchand = produitMarchand;
    }
    public EtatReclamationDto getEtatReclamation(){
        return this.etatReclamation;
    }

    public void setEtatReclamation(EtatReclamationDto etatReclamation){
        this.etatReclamation = etatReclamation;
    }



    public List<ReclamationElementChimiqueDto> getReclamationElementChimiques(){
        return this.reclamationElementChimiques;
    }

    public void setReclamationElementChimiques(List<ReclamationElementChimiqueDto> reclamationElementChimiques){
        this.reclamationElementChimiques = reclamationElementChimiques;
    }



}

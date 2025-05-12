package  ma.zyn.app.ws.dto.navire;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zyn.app.ws.dto.referentiel.ProduitMarchandDto;
import ma.zyn.app.ws.dto.referentiel.ProduitDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class RealisationNavireDto  extends AuditBaseDto {

    private String libelle  ;
    private String description  ;
    private String numeroNavire  ;
    private String numeroExpedition  ;
    private String jour ;
    private BigDecimal tauxCompletude  ;
    private BigDecimal tauxRemplissage  ;
    private String dateChargement ;
    private String dateFinChargement ;

    private DestinationNavireDto destinationNavire ;

    private List<RealisationNavireProduitDto> realisationNavireProduits ;
    private List<RealisationNavireQualiteDto> realisationNavireQualites ;


    public RealisationNavireDto(){
        super();
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


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateChargement(){
        return this.dateChargement;
    }
    public void setDateChargement(String dateChargement){
        this.dateChargement = dateChargement;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateFinChargement(){
        return this.dateFinChargement;
    }
    public void setDateFinChargement(String dateFinChargement){
        this.dateFinChargement = dateFinChargement;
    }


    public DestinationNavireDto getDestinationNavire(){
        return this.destinationNavire;
    }

    public void setDestinationNavire(DestinationNavireDto destinationNavire){
        this.destinationNavire = destinationNavire;
    }



    public List<RealisationNavireProduitDto> getRealisationNavireProduits(){
        return this.realisationNavireProduits;
    }

    public void setRealisationNavireProduits(List<RealisationNavireProduitDto> realisationNavireProduits){
        this.realisationNavireProduits = realisationNavireProduits;
    }
    public List<RealisationNavireQualiteDto> getRealisationNavireQualites(){
        return this.realisationNavireQualites;
    }

    public void setRealisationNavireQualites(List<RealisationNavireQualiteDto> realisationNavireQualites){
        this.realisationNavireQualites = realisationNavireQualites;
    }



}

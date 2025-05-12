package  ma.zyn.app.ws.dto.camion;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zyn.app.ws.dto.referentiel.ProduitDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class RealisationCamionDto  extends AuditBaseDto {

    private String libelle  ;
    private String description  ;
    private String jour ;
    private BigDecimal nombreCamions  ;
    private BigDecimal dureeMoyenneTransport  ;
    private BigDecimal totalTms  ;

    private ProvennanceCamionDto provennanceCamion ;
    private DestinationCamionDto destinationCamion ;

    private List<RealisationCamionProduitDto> realisationCamionProduits ;


    public RealisationCamionDto(){
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


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getJour(){
        return this.jour;
    }
    public void setJour(String jour){
        this.jour = jour;
    }


    public BigDecimal getNombreCamions(){
        return this.nombreCamions;
    }
    public void setNombreCamions(BigDecimal nombreCamions){
        this.nombreCamions = nombreCamions;
    }


    public BigDecimal getDureeMoyenneTransport(){
        return this.dureeMoyenneTransport;
    }
    public void setDureeMoyenneTransport(BigDecimal dureeMoyenneTransport){
        this.dureeMoyenneTransport = dureeMoyenneTransport;
    }


    public BigDecimal getTotalTms(){
        return this.totalTms;
    }
    public void setTotalTms(BigDecimal totalTms){
        this.totalTms = totalTms;
    }


    public ProvennanceCamionDto getProvennanceCamion(){
        return this.provennanceCamion;
    }

    public void setProvennanceCamion(ProvennanceCamionDto provennanceCamion){
        this.provennanceCamion = provennanceCamion;
    }
    public DestinationCamionDto getDestinationCamion(){
        return this.destinationCamion;
    }

    public void setDestinationCamion(DestinationCamionDto destinationCamion){
        this.destinationCamion = destinationCamion;
    }



    public List<RealisationCamionProduitDto> getRealisationCamionProduits(){
        return this.realisationCamionProduits;
    }

    public void setRealisationCamionProduits(List<RealisationCamionProduitDto> realisationCamionProduits){
        this.realisationCamionProduits = realisationCamionProduits;
    }



}

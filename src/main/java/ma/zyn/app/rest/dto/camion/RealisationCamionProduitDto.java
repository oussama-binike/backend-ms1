package  ma.zyn.app.ws.dto.camion;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;


import ma.zyn.app.ws.dto.referentiel.ProduitDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class RealisationCamionProduitDto  extends AuditBaseDto {

    private BigDecimal tsm  ;

    private ProduitDto produit ;
    private RealisationCamionDto realisationCamion ;



    public RealisationCamionProduitDto(){
        super();
    }




    public BigDecimal getTsm(){
        return this.tsm;
    }
    public void setTsm(BigDecimal tsm){
        this.tsm = tsm;
    }


    public ProduitDto getProduit(){
        return this.produit;
    }

    public void setProduit(ProduitDto produit){
        this.produit = produit;
    }
    public RealisationCamionDto getRealisationCamion(){
        return this.realisationCamion;
    }

    public void setRealisationCamion(RealisationCamionDto realisationCamion){
        this.realisationCamion = realisationCamion;
    }






}

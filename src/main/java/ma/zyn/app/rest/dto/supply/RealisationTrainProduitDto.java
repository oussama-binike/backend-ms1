package  ma.zyn.app.ws.dto.supply;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;


import ma.zyn.app.ws.dto.referentiel.ProduitDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class RealisationTrainProduitDto  extends AuditBaseDto {

    private BigDecimal volume  ;
    private BigDecimal tsm  ;

    private ProduitDto produit ;
    private RealisationTrainDto realisationTrain ;



    public RealisationTrainProduitDto(){
        super();
    }




    public BigDecimal getVolume(){
        return this.volume;
    }
    public void setVolume(BigDecimal volume){
        this.volume = volume;
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
    public RealisationTrainDto getRealisationTrain(){
        return this.realisationTrain;
    }

    public void setRealisationTrain(RealisationTrainDto realisationTrain){
        this.realisationTrain = realisationTrain;
    }






}

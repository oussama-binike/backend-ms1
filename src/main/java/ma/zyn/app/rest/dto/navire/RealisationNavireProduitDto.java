package  ma.zyn.app.ws.dto.navire;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;


import ma.zyn.app.ws.dto.referentiel.ProduitDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class RealisationNavireProduitDto  extends AuditBaseDto {

    private BigDecimal tsm  ;
    private BigDecimal volume  ;

    private ProduitDto produit ;
    private RealisationNavireDto realisationNavire ;



    public RealisationNavireProduitDto(){
        super();
    }




    public BigDecimal getTsm(){
        return this.tsm;
    }
    public void setTsm(BigDecimal tsm){
        this.tsm = tsm;
    }


    public BigDecimal getVolume(){
        return this.volume;
    }
    public void setVolume(BigDecimal volume){
        this.volume = volume;
    }


    public ProduitDto getProduit(){
        return this.produit;
    }

    public void setProduit(ProduitDto produit){
        this.produit = produit;
    }
    public RealisationNavireDto getRealisationNavire(){
        return this.realisationNavire;
    }

    public void setRealisationNavire(RealisationNavireDto realisationNavire){
        this.realisationNavire = realisationNavire;
    }






}

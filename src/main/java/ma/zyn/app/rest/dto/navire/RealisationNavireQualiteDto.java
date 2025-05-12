package  ma.zyn.app.ws.dto.navire;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;


import ma.zyn.app.ws.dto.referentiel.ProduitMarchandDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class RealisationNavireQualiteDto  extends AuditBaseDto {

    private BigDecimal tsm  ;
    private BigDecimal volume  ;

    private ProduitMarchandDto produitMarchand ;
    private RealisationNavireDto realisationNavire ;



    public RealisationNavireQualiteDto(){
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


    public ProduitMarchandDto getProduitMarchand(){
        return this.produitMarchand;
    }

    public void setProduitMarchand(ProduitMarchandDto produitMarchand){
        this.produitMarchand = produitMarchand;
    }
    public RealisationNavireDto getRealisationNavire(){
        return this.realisationNavire;
    }

    public void setRealisationNavire(RealisationNavireDto realisationNavire){
        this.realisationNavire = realisationNavire;
    }






}

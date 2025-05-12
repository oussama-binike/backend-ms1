package  ma.zyn.app.ws.dto.referentiel;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class CharteChimiqueDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String description  ;
    private BigDecimal minimum  ;
    private BigDecimal maximum  ;
    private BigDecimal average  ;
    private String methodeAnalyse  ;
    private String unite  ;

    private ProduitDto produit ;
    private ElementChimiqueDto elementChimique ;



    public CharteChimiqueDto(){
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


    public BigDecimal getMinimum(){
        return this.minimum;
    }
    public void setMinimum(BigDecimal minimum){
        this.minimum = minimum;
    }


    public BigDecimal getMaximum(){
        return this.maximum;
    }
    public void setMaximum(BigDecimal maximum){
        this.maximum = maximum;
    }


    public BigDecimal getAverage(){
        return this.average;
    }
    public void setAverage(BigDecimal average){
        this.average = average;
    }


    public String getMethodeAnalyse(){
        return this.methodeAnalyse;
    }
    public void setMethodeAnalyse(String methodeAnalyse){
        this.methodeAnalyse = methodeAnalyse;
    }


    public String getUnite(){
        return this.unite;
    }
    public void setUnite(String unite){
        this.unite = unite;
    }


    public ProduitDto getProduit(){
        return this.produit;
    }

    public void setProduit(ProduitDto produit){
        this.produit = produit;
    }
    public ElementChimiqueDto getElementChimique(){
        return this.elementChimique;
    }

    public void setElementChimique(ElementChimiqueDto elementChimique){
        this.elementChimique = elementChimique;
    }






}

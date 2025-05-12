package  ma.zyn.app.ws.dto.referentiel;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChartePhysiqueDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String description  ;
    private BigDecimal minimumSize  ;
    private BigDecimal maximumSize  ;
    private BigDecimal valeur  ;

    private ProduitDto produit ;



    public ChartePhysiqueDto(){
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


    public BigDecimal getMinimumSize(){
        return this.minimumSize;
    }
    public void setMinimumSize(BigDecimal minimumSize){
        this.minimumSize = minimumSize;
    }


    public BigDecimal getMaximumSize(){
        return this.maximumSize;
    }
    public void setMaximumSize(BigDecimal maximumSize){
        this.maximumSize = maximumSize;
    }


    public BigDecimal getValeur(){
        return this.valeur;
    }
    public void setValeur(BigDecimal valeur){
        this.valeur = valeur;
    }


    public ProduitDto getProduit(){
        return this.produit;
    }

    public void setProduit(ProduitDto produit){
        this.produit = produit;
    }






}

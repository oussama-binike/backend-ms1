package  ma.zyn.app.ws.dto.referentiel;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class CoutStadeDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String description  ;
    private BigDecimal coutUnitaire  ;

    private StadeOperatoireDto stadeOperatoire ;
    private UniteDto unite ;



    public CoutStadeDto(){
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


    public BigDecimal getCoutUnitaire(){
        return this.coutUnitaire;
    }
    public void setCoutUnitaire(BigDecimal coutUnitaire){
        this.coutUnitaire = coutUnitaire;
    }


    public StadeOperatoireDto getStadeOperatoire(){
        return this.stadeOperatoire;
    }

    public void setStadeOperatoire(StadeOperatoireDto stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
    }
    public UniteDto getUnite(){
        return this.unite;
    }

    public void setUnite(UniteDto unite){
        this.unite = unite;
    }






}

package  ma.zyn.app.ws.dto.referentiel;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnginDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String style  ;
    private String description  ;
    private BigDecimal capacite  ;
    private BigDecimal rendement  ;

    private TypeEnginDto typeEngin ;
    private OperationStadeOperatoireDto operationStadeOperatoire ;
    private StadeOperatoireDto stadeOperatoire ;



    public EnginDto(){
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


    public String getStyle(){
        return this.style;
    }
    public void setStyle(String style){
        this.style = style;
    }


    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    public BigDecimal getCapacite(){
        return this.capacite;
    }
    public void setCapacite(BigDecimal capacite){
        this.capacite = capacite;
    }


    public BigDecimal getRendement(){
        return this.rendement;
    }
    public void setRendement(BigDecimal rendement){
        this.rendement = rendement;
    }


    public TypeEnginDto getTypeEngin(){
        return this.typeEngin;
    }

    public void setTypeEngin(TypeEnginDto typeEngin){
        this.typeEngin = typeEngin;
    }
    public OperationStadeOperatoireDto getOperationStadeOperatoire(){
        return this.operationStadeOperatoire;
    }

    public void setOperationStadeOperatoire(OperationStadeOperatoireDto operationStadeOperatoire){
        this.operationStadeOperatoire = operationStadeOperatoire;
    }
    public StadeOperatoireDto getStadeOperatoire(){
        return this.stadeOperatoire;
    }

    public void setStadeOperatoire(StadeOperatoireDto stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
    }






}

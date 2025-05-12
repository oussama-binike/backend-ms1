package  ma.zyn.app.ws.dto.referentiel;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsommableDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String description  ;
    private String style  ;

    private UniteDto unite ;

    private List<ConsommableStadeOperatoireDto> consommableStadeOperatoires ;


    public ConsommableDto(){
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


    public String getStyle(){
        return this.style;
    }
    public void setStyle(String style){
        this.style = style;
    }


    public UniteDto getUnite(){
        return this.unite;
    }

    public void setUnite(UniteDto unite){
        this.unite = unite;
    }



    public List<ConsommableStadeOperatoireDto> getConsommableStadeOperatoires(){
        return this.consommableStadeOperatoires;
    }

    public void setConsommableStadeOperatoires(List<ConsommableStadeOperatoireDto> consommableStadeOperatoires){
        this.consommableStadeOperatoires = consommableStadeOperatoires;
    }



}

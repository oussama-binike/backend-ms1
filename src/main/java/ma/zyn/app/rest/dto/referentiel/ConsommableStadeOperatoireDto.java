package  ma.zyn.app.ws.dto.referentiel;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsommableStadeOperatoireDto  extends AuditBaseDto {


    private StadeOperatoireDto stadeOperatoire ;
    private ConsommableDto consommable ;



    public ConsommableStadeOperatoireDto(){
        super();
    }




    public StadeOperatoireDto getStadeOperatoire(){
        return this.stadeOperatoire;
    }

    public void setStadeOperatoire(StadeOperatoireDto stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
    }
    public ConsommableDto getConsommable(){
        return this.consommable;
    }

    public void setConsommable(ConsommableDto consommable){
        this.consommable = consommable;
    }






}

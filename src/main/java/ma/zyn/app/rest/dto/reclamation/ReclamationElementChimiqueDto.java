package  ma.zyn.app.ws.dto.reclamation;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zyn.app.ws.dto.referentiel.ElementChimiqueDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReclamationElementChimiqueDto  extends AuditBaseDto {


    private ReclamationDto reclamation ;
    private ElementChimiqueDto elementChimique ;



    public ReclamationElementChimiqueDto(){
        super();
    }




    public ReclamationDto getReclamation(){
        return this.reclamation;
    }

    public void setReclamation(ReclamationDto reclamation){
        this.reclamation = reclamation;
    }
    public ElementChimiqueDto getElementChimique(){
        return this.elementChimique;
    }

    public void setElementChimique(ElementChimiqueDto elementChimique){
        this.elementChimique = elementChimique;
    }






}

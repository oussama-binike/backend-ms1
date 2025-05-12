package  ma.zyn.app.ws.dto.referentiel;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsommationSpecifiqueDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String style  ;
    private String description  ;
    private BigDecimal valeur  ;
    private String dateConsommationSpecifique ;

    private ConsommableDto consommable ;
    private StadeOperatoireDto stadeOperatoire ;
    private UniteDto unite ;



    public ConsommationSpecifiqueDto(){
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


    public BigDecimal getValeur(){
        return this.valeur;
    }
    public void setValeur(BigDecimal valeur){
        this.valeur = valeur;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateConsommationSpecifique(){
        return this.dateConsommationSpecifique;
    }
    public void setDateConsommationSpecifique(String dateConsommationSpecifique){
        this.dateConsommationSpecifique = dateConsommationSpecifique;
    }


    public ConsommableDto getConsommable(){
        return this.consommable;
    }

    public void setConsommable(ConsommableDto consommable){
        this.consommable = consommable;
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

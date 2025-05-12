package  ma.zyn.app.ws.dto.referentiel;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String description  ;
    private BigDecimal autonomie  ;
    private BigDecimal capacite  ;
    private Integer nombreRepere  = 0 ;
    private Integer repereDebut  = 0 ;
    private Integer repereFin  = 0 ;

    private StadeOperatoireDto stadeOperatoire ;
    private TypeStockDto typeStock ;
    private CategorieStockDto categorieStock ;



    public StockDto(){
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


    public BigDecimal getAutonomie(){
        return this.autonomie;
    }
    public void setAutonomie(BigDecimal autonomie){
        this.autonomie = autonomie;
    }


    public BigDecimal getCapacite(){
        return this.capacite;
    }
    public void setCapacite(BigDecimal capacite){
        this.capacite = capacite;
    }


    public Integer getNombreRepere(){
        return this.nombreRepere;
    }
    public void setNombreRepere(Integer nombreRepere){
        this.nombreRepere = nombreRepere;
    }


    public Integer getRepereDebut(){
        return this.repereDebut;
    }
    public void setRepereDebut(Integer repereDebut){
        this.repereDebut = repereDebut;
    }


    public Integer getRepereFin(){
        return this.repereFin;
    }
    public void setRepereFin(Integer repereFin){
        this.repereFin = repereFin;
    }


    public StadeOperatoireDto getStadeOperatoire(){
        return this.stadeOperatoire;
    }

    public void setStadeOperatoire(StadeOperatoireDto stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
    }
    public TypeStockDto getTypeStock(){
        return this.typeStock;
    }

    public void setTypeStock(TypeStockDto typeStock){
        this.typeStock = typeStock;
    }
    public CategorieStockDto getCategorieStock(){
        return this.categorieStock;
    }

    public void setCategorieStock(CategorieStockDto categorieStock){
        this.categorieStock = categorieStock;
    }






}

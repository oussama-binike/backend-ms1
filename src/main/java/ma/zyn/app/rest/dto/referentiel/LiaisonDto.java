package  ma.zyn.app.ws.dto.referentiel;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class LiaisonDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String description  ;

    private StockDto stockSource ;
    private StockDto stockDestination ;
    private EnginDto engin ;
    private OperationStadeOperatoireDto operationStadeOperatoire ;
    private ProduitDto prodduit ;



    public LiaisonDto(){
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


    public StockDto getStockSource(){
        return this.stockSource;
    }

    public void setStockSource(StockDto stockSource){
        this.stockSource = stockSource;
    }
    public StockDto getStockDestination(){
        return this.stockDestination;
    }

    public void setStockDestination(StockDto stockDestination){
        this.stockDestination = stockDestination;
    }
    public EnginDto getEngin(){
        return this.engin;
    }

    public void setEngin(EnginDto engin){
        this.engin = engin;
    }
    public OperationStadeOperatoireDto getOperationStadeOperatoire(){
        return this.operationStadeOperatoire;
    }

    public void setOperationStadeOperatoire(OperationStadeOperatoireDto operationStadeOperatoire){
        this.operationStadeOperatoire = operationStadeOperatoire;
    }
    public ProduitDto getProdduit(){
        return this.prodduit;
    }

    public void setProdduit(ProduitDto prodduit){
        this.prodduit = prodduit;
    }






}

package ma.zyn.app.bean.core.referentiel;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "liaison")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="liaison_seq",sequenceName="liaison_seq",allocationSize=1, initialValue = 1)
public class Liaison  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;

    private Stock stockSource ;
    private Stock stockDestination ;
    private Engin engin ;
    private OperationStadeOperatoire operationStadeOperatoire ;
    private Produit prodduit ;


    public Liaison(){
        super();
    }

    public Liaison(Long id){
        this.id = id;
    }

    public Liaison(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public Liaison(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="liaison_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
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
      @Column(columnDefinition="TEXT")
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_source")
    public Stock getStockSource(){
        return this.stockSource;
    }
    public void setStockSource(Stock stockSource){
        this.stockSource = stockSource;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_destination")
    public Stock getStockDestination(){
        return this.stockDestination;
    }
    public void setStockDestination(Stock stockDestination){
        this.stockDestination = stockDestination;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "engin")
    public Engin getEngin(){
        return this.engin;
    }
    public void setEngin(Engin engin){
        this.engin = engin;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operation_stade_operatoire")
    public OperationStadeOperatoire getOperationStadeOperatoire(){
        return this.operationStadeOperatoire;
    }
    public void setOperationStadeOperatoire(OperationStadeOperatoire operationStadeOperatoire){
        this.operationStadeOperatoire = operationStadeOperatoire;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prodduit")
    public Produit getProdduit(){
        return this.prodduit;
    }
    public void setProdduit(Produit prodduit){
        this.prodduit = prodduit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Liaison liaison = (Liaison) o;
        return id != null && id.equals(liaison.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


package ma.zyn.app.bean.core.referentiel;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "stock")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="stock_seq",sequenceName="stock_seq",allocationSize=1, initialValue = 1)
public class Stock  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;

    private BigDecimal autonomie = BigDecimal.ZERO;

    private BigDecimal capacite = BigDecimal.ZERO;

    private Integer nombreRepere = 0;

    private Integer repereDebut = 0;

    private Integer repereFin = 0;

    private StadeOperatoire stadeOperatoire ;
    private TypeStock typeStock ;
    private CategorieStock categorieStock ;


    public Stock(){
        super();
    }

    public Stock(Long id){
        this.id = id;
    }

    public Stock(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public Stock(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="stock_seq")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stade_operatoire")
    public StadeOperatoire getStadeOperatoire(){
        return this.stadeOperatoire;
    }
    public void setStadeOperatoire(StadeOperatoire stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_stock")
    public TypeStock getTypeStock(){
        return this.typeStock;
    }
    public void setTypeStock(TypeStock typeStock){
        this.typeStock = typeStock;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categorie_stock")
    public CategorieStock getCategorieStock(){
        return this.categorieStock;
    }
    public void setCategorieStock(CategorieStock categorieStock){
        this.categorieStock = categorieStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return id != null && id.equals(stock.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


package ma.zyn.app.bean.core.referentiel;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "charte_chimique")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="charte_chimique_seq",sequenceName="charte_chimique_seq",allocationSize=1, initialValue = 1)
public class CharteChimique  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;

    private BigDecimal minimum = BigDecimal.ZERO;

    private BigDecimal maximum = BigDecimal.ZERO;

    private BigDecimal average = BigDecimal.ZERO;

    @Column(length = 500)
    private String methodeAnalyse;

    @Column(length = 500)
    private String unite;

    private Produit produit ;
    private ElementChimique elementChimique ;


    public CharteChimique(){
        super();
    }

    public CharteChimique(Long id){
        this.id = id;
    }

    public CharteChimique(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public CharteChimique(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="charte_chimique_seq")
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
    @JoinColumn(name = "produit")
    public Produit getProduit(){
        return this.produit;
    }
    public void setProduit(Produit produit){
        this.produit = produit;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "element_chimique")
    public ElementChimique getElementChimique(){
        return this.elementChimique;
    }
    public void setElementChimique(ElementChimique elementChimique){
        this.elementChimique = elementChimique;
    }
    public BigDecimal getMinimum(){
        return this.minimum;
    }
    public void setMinimum(BigDecimal minimum){
        this.minimum = minimum;
    }
    public BigDecimal getMaximum(){
        return this.maximum;
    }
    public void setMaximum(BigDecimal maximum){
        this.maximum = maximum;
    }
    public BigDecimal getAverage(){
        return this.average;
    }
    public void setAverage(BigDecimal average){
        this.average = average;
    }
    public String getMethodeAnalyse(){
        return this.methodeAnalyse;
    }
    public void setMethodeAnalyse(String methodeAnalyse){
        this.methodeAnalyse = methodeAnalyse;
    }
    public String getUnite(){
        return this.unite;
    }
    public void setUnite(String unite){
        this.unite = unite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharteChimique charteChimique = (CharteChimique) o;
        return id != null && id.equals(charteChimique.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


package ma.zyn.app.bean.core.referentiel;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "charte_physique")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="charte_physique_seq",sequenceName="charte_physique_seq",allocationSize=1, initialValue = 1)
public class ChartePhysique  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;

    private BigDecimal minimumSize = BigDecimal.ZERO;

    private BigDecimal maximumSize = BigDecimal.ZERO;

    private BigDecimal valeur = BigDecimal.ZERO;

    private Produit produit ;


    public ChartePhysique(){
        super();
    }

    public ChartePhysique(Long id){
        this.id = id;
    }

    public ChartePhysique(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public ChartePhysique(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="charte_physique_seq")
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
    public BigDecimal getMinimumSize(){
        return this.minimumSize;
    }
    public void setMinimumSize(BigDecimal minimumSize){
        this.minimumSize = minimumSize;
    }
    public BigDecimal getMaximumSize(){
        return this.maximumSize;
    }
    public void setMaximumSize(BigDecimal maximumSize){
        this.maximumSize = maximumSize;
    }
    public BigDecimal getValeur(){
        return this.valeur;
    }
    public void setValeur(BigDecimal valeur){
        this.valeur = valeur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChartePhysique chartePhysique = (ChartePhysique) o;
        return id != null && id.equals(chartePhysique.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


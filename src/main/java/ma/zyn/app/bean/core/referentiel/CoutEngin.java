package ma.zyn.app.bean.core.referentiel;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "cout_engin")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="cout_engin_seq",sequenceName="cout_engin_seq",allocationSize=1, initialValue = 1)
public class CoutEngin  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;

    private BigDecimal coutUnitaire = BigDecimal.ZERO;

    private Engin engin ;
    private Unite unite ;


    public CoutEngin(){
        super();
    }

    public CoutEngin(Long id){
        this.id = id;
    }

    public CoutEngin(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public CoutEngin(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="cout_engin_seq")
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
    @JoinColumn(name = "engin")
    public Engin getEngin(){
        return this.engin;
    }
    public void setEngin(Engin engin){
        this.engin = engin;
    }
    public BigDecimal getCoutUnitaire(){
        return this.coutUnitaire;
    }
    public void setCoutUnitaire(BigDecimal coutUnitaire){
        this.coutUnitaire = coutUnitaire;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unite")
    public Unite getUnite(){
        return this.unite;
    }
    public void setUnite(Unite unite){
        this.unite = unite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoutEngin coutEngin = (CoutEngin) o;
        return id != null && id.equals(coutEngin.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


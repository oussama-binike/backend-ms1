package ma.zyn.app.bean.core.referentiel;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "cout_consommable")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="cout_consommable_seq",sequenceName="cout_consommable_seq",allocationSize=1, initialValue = 1)
public class CoutConsommable  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;

    private BigDecimal coutUnitaire = BigDecimal.ZERO;

    private Consommable consommable ;
    private Unite unite ;


    public CoutConsommable(){
        super();
    }

    public CoutConsommable(Long id){
        this.id = id;
    }

    public CoutConsommable(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public CoutConsommable(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="cout_consommable_seq")
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
    @JoinColumn(name = "consommable")
    public Consommable getConsommable(){
        return this.consommable;
    }
    public void setConsommable(Consommable consommable){
        this.consommable = consommable;
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
        CoutConsommable coutConsommable = (CoutConsommable) o;
        return id != null && id.equals(coutConsommable.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


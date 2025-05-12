package ma.zyn.app.bean.core.referentiel;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "niveau")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="niveau_seq",sequenceName="niveau_seq",allocationSize=1, initialValue = 1)
public class Niveau  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;

    private Tranchee tranchee ;


    public Niveau(){
        super();
    }

    public Niveau(Long id){
        this.id = id;
    }

    public Niveau(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public Niveau(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="niveau_seq")
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
    @JoinColumn(name = "tranchee")
    public Tranchee getTranchee(){
        return this.tranchee;
    }
    public void setTranchee(Tranchee tranchee){
        this.tranchee = tranchee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Niveau niveau = (Niveau) o;
        return id != null && id.equals(niveau.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


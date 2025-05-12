package ma.zyn.app.bean.core.referentiel;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "chaine_stade_operatoire")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="chaine_stade_operatoire_seq",sequenceName="chaine_stade_operatoire_seq",allocationSize=1, initialValue = 1)
public class ChaineStadeOperatoire  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String style;

    private String description;

    private StadeOperatoire stadeOperatoire ;


    public ChaineStadeOperatoire(){
        super();
    }

    public ChaineStadeOperatoire(Long id){
        this.id = id;
    }

    public ChaineStadeOperatoire(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public ChaineStadeOperatoire(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="chaine_stade_operatoire_seq")
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
    public String getStyle(){
        return this.style;
    }
    public void setStyle(String style){
        this.style = style;
    }
      @Column(columnDefinition="TEXT")
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stade_operatoire")
    public StadeOperatoire getStadeOperatoire(){
        return this.stadeOperatoire;
    }
    public void setStadeOperatoire(StadeOperatoire stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChaineStadeOperatoire chaineStadeOperatoire = (ChaineStadeOperatoire) o;
        return id != null && id.equals(chaineStadeOperatoire.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


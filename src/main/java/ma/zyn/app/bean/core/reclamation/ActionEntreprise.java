package ma.zyn.app.bean.core.reclamation;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "action_entreprise")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="action_entreprise_seq",sequenceName="action_entreprise_seq",allocationSize=1, initialValue = 1)
public class ActionEntreprise  extends BaseEntity     {




    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String style;

    private String description;



    public ActionEntreprise(){
        super();
    }

    public ActionEntreprise(Long id){
        this.id = id;
    }

    public ActionEntreprise(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public ActionEntreprise(String libelle){
        this.libelle = libelle ;
    }
    public ActionEntreprise(String libelle,String code){
        this.libelle=libelle;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="action_entreprise_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActionEntreprise actionEntreprise = (ActionEntreprise) o;
        return id != null && id.equals(actionEntreprise.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


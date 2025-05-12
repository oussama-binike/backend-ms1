package ma.zyn.app.bean.core.referentiel;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "panneau")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="panneau_seq",sequenceName="panneau_seq",allocationSize=1, initialValue = 1)
public class Panneau  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String style;

    private String description;

    private Entite entite ;


    public Panneau(){
        super();
    }

    public Panneau(Long id){
        this.id = id;
    }

    public Panneau(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public Panneau(String libelle){
        this.libelle = libelle ;
    }
    public Panneau(String libelle,String code){
        this.libelle=libelle;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="panneau_seq")
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
    @JoinColumn(name = "entite")
    public Entite getEntite(){
        return this.entite;
    }
    public void setEntite(Entite entite){
        this.entite = entite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Panneau panneau = (Panneau) o;
        return id != null && id.equals(panneau.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


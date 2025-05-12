package ma.zyn.app.bean.core.referentiel;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categorie_unite")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="categorie_unite_seq",sequenceName="categorie_unite_seq",allocationSize=1, initialValue = 1)
public class CategorieUnite  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;

    @Column(length = 500)
    private String style;



    public CategorieUnite(){
        super();
    }

    public CategorieUnite(Long id){
        this.id = id;
    }

    public CategorieUnite(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public CategorieUnite(String libelle){
        this.libelle = libelle ;
    }
    public CategorieUnite(String libelle,String code){
        this.libelle=libelle;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="categorie_unite_seq")
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
    public String getStyle(){
        return this.style;
    }
    public void setStyle(String style){
        this.style = style;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategorieUnite categorieUnite = (CategorieUnite) o;
        return id != null && id.equals(categorieUnite.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


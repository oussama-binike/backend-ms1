package ma.zyn.app.bean.core.referentiel;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categorie_stock")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="categorie_stock_seq",sequenceName="categorie_stock_seq",allocationSize=1, initialValue = 1)
public class CategorieStock  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String style;

    private String description;



    public CategorieStock(){
        super();
    }

    public CategorieStock(Long id){
        this.id = id;
    }

    public CategorieStock(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public CategorieStock(String libelle){
        this.libelle = libelle ;
    }
    public CategorieStock(String libelle,String code){
        this.libelle=libelle;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="categorie_stock_seq")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategorieStock categorieStock = (CategorieStock) o;
        return id != null && id.equals(categorieStock.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


package ma.zyn.app.bean.core.referentiel;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "point_controle")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="point_controle_seq",sequenceName="point_controle_seq",allocationSize=1, initialValue = 1)
public class PointControle  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;

    private Integer indice = 0;

    private StadeOperatoire stadeOperatoire ;


    public PointControle(){
        super();
    }

    public PointControle(Long id){
        this.id = id;
    }

    public PointControle(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public PointControle(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="point_controle_seq")
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
    public Integer getIndice(){
        return this.indice;
    }
    public void setIndice(Integer indice){
        this.indice = indice;
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
        PointControle pointControle = (PointControle) o;
        return id != null && id.equals(pointControle.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


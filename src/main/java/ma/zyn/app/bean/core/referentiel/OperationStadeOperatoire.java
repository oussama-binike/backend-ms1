package ma.zyn.app.bean.core.referentiel;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "operation_stade_operatoire")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="operation_stade_operatoire_seq",sequenceName="operation_stade_operatoire_seq",allocationSize=1, initialValue = 1)
public class OperationStadeOperatoire  extends BaseEntity     {




    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String style;

    private String description;



    public OperationStadeOperatoire(){
        super();
    }

    public OperationStadeOperatoire(Long id){
        this.id = id;
    }

    public OperationStadeOperatoire(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public OperationStadeOperatoire(String libelle){
        this.libelle = libelle ;
    }
    public OperationStadeOperatoire(String libelle,String code){
        this.libelle=libelle;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="operation_stade_operatoire_seq")
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
        OperationStadeOperatoire operationStadeOperatoire = (OperationStadeOperatoire) o;
        return id != null && id.equals(operationStadeOperatoire.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


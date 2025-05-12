package ma.zyn.app.bean.core.referentiel;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "engin")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="engin_seq",sequenceName="engin_seq",allocationSize=1, initialValue = 1)
public class Engin  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String style;

    private String description;

    private BigDecimal capacite = BigDecimal.ZERO;

    private BigDecimal rendement = BigDecimal.ZERO;

    private TypeEngin typeEngin ;
    private OperationStadeOperatoire operationStadeOperatoire ;
    private StadeOperatoire stadeOperatoire ;


    public Engin(){
        super();
    }

    public Engin(Long id){
        this.id = id;
    }

    public Engin(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public Engin(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="engin_seq")
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
    @JoinColumn(name = "type_engin")
    public TypeEngin getTypeEngin(){
        return this.typeEngin;
    }
    public void setTypeEngin(TypeEngin typeEngin){
        this.typeEngin = typeEngin;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operation_stade_operatoire")
    public OperationStadeOperatoire getOperationStadeOperatoire(){
        return this.operationStadeOperatoire;
    }
    public void setOperationStadeOperatoire(OperationStadeOperatoire operationStadeOperatoire){
        this.operationStadeOperatoire = operationStadeOperatoire;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stade_operatoire")
    public StadeOperatoire getStadeOperatoire(){
        return this.stadeOperatoire;
    }
    public void setStadeOperatoire(StadeOperatoire stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
    }
    public BigDecimal getCapacite(){
        return this.capacite;
    }
    public void setCapacite(BigDecimal capacite){
        this.capacite = capacite;
    }
    public BigDecimal getRendement(){
        return this.rendement;
    }
    public void setRendement(BigDecimal rendement){
        this.rendement = rendement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engin engin = (Engin) o;
        return id != null && id.equals(engin.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


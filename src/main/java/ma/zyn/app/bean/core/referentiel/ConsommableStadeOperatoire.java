package ma.zyn.app.bean.core.referentiel;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "consommable_stade_operatoire")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="consommable_stade_operatoire_seq",sequenceName="consommable_stade_operatoire_seq",allocationSize=1, initialValue = 1)
public class ConsommableStadeOperatoire  extends BaseEntity     {




    private StadeOperatoire stadeOperatoire ;
    private Consommable consommable ;


    public ConsommableStadeOperatoire(){
        super();
    }

    public ConsommableStadeOperatoire(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="consommable_stade_operatoire_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stade_operatoire")
    public StadeOperatoire getStadeOperatoire(){
        return this.stadeOperatoire;
    }
    public void setStadeOperatoire(StadeOperatoire stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consommable")
    public Consommable getConsommable(){
        return this.consommable;
    }
    public void setConsommable(Consommable consommable){
        this.consommable = consommable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsommableStadeOperatoire consommableStadeOperatoire = (ConsommableStadeOperatoire) o;
        return id != null && id.equals(consommableStadeOperatoire.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


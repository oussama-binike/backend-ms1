package ma.zyn.app.bean.core.reclamation;






import ma.zyn.app.bean.core.referentiel.ElementChimique;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "reclamation_element_chimique")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="reclamation_element_chimique_seq",sequenceName="reclamation_element_chimique_seq",allocationSize=1, initialValue = 1)
public class ReclamationElementChimique  extends BaseEntity     {




    private Reclamation reclamation ;
    private ElementChimique elementChimique ;


    public ReclamationElementChimique(){
        super();
    }

    public ReclamationElementChimique(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="reclamation_element_chimique_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reclamation")
    public Reclamation getReclamation(){
        return this.reclamation;
    }
    public void setReclamation(Reclamation reclamation){
        this.reclamation = reclamation;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "element_chimique")
    public ElementChimique getElementChimique(){
        return this.elementChimique;
    }
    public void setElementChimique(ElementChimique elementChimique){
        this.elementChimique = elementChimique;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReclamationElementChimique reclamationElementChimique = (ReclamationElementChimique) o;
        return id != null && id.equals(reclamationElementChimique.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


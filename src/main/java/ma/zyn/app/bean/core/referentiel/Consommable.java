package ma.zyn.app.bean.core.referentiel;

import java.util.List;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "consommable")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="consommable_seq",sequenceName="consommable_seq",allocationSize=1, initialValue = 1)
public class Consommable  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;

    @Column(length = 500)
    private String style;

    private Unite unite ;

    private List<ConsommableStadeOperatoire> consommableStadeOperatoires ;

    public Consommable(){
        super();
    }

    public Consommable(Long id){
        this.id = id;
    }

    public Consommable(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public Consommable(String libelle){
        this.libelle = libelle ;
    }
    public Consommable(String libelle,String code){
        this.libelle=libelle;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="consommable_seq")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unite")
    public Unite getUnite(){
        return this.unite;
    }
    public void setUnite(Unite unite){
        this.unite = unite;
    }
    @OneToMany(mappedBy = "consommable")
    public List<ConsommableStadeOperatoire> getConsommableStadeOperatoires(){
        return this.consommableStadeOperatoires;
    }

    public void setConsommableStadeOperatoires(List<ConsommableStadeOperatoire> consommableStadeOperatoires){
        this.consommableStadeOperatoires = consommableStadeOperatoires;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consommable consommable = (Consommable) o;
        return id != null && id.equals(consommable.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


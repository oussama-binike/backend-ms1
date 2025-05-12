package ma.zyn.app.bean.core.referentiel;


import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;




import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "consommation_specifique")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="consommation_specifique_seq",sequenceName="consommation_specifique_seq",allocationSize=1, initialValue = 1)
public class ConsommationSpecifique  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String style;

    private String description;

    private BigDecimal valeur = BigDecimal.ZERO;

    private LocalDateTime dateConsommationSpecifique ;

    private Consommable consommable ;
    private StadeOperatoire stadeOperatoire ;
    private Unite unite ;


    public ConsommationSpecifique(){
        super();
    }

    public ConsommationSpecifique(Long id){
        this.id = id;
    }

    public ConsommationSpecifique(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public ConsommationSpecifique(String libelle){
        this.libelle = libelle ;
    }
    public ConsommationSpecifique(String libelle,String code){
        this.libelle=libelle;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="consommation_specifique_seq")
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
    @JoinColumn(name = "consommable")
    public Consommable getConsommable(){
        return this.consommable;
    }
    public void setConsommable(Consommable consommable){
        this.consommable = consommable;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stade_operatoire")
    public StadeOperatoire getStadeOperatoire(){
        return this.stadeOperatoire;
    }
    public void setStadeOperatoire(StadeOperatoire stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
    }
    public BigDecimal getValeur(){
        return this.valeur;
    }
    public void setValeur(BigDecimal valeur){
        this.valeur = valeur;
    }
    public LocalDateTime getDateConsommationSpecifique(){
        return this.dateConsommationSpecifique;
    }
    public void setDateConsommationSpecifique(LocalDateTime dateConsommationSpecifique){
        this.dateConsommationSpecifique = dateConsommationSpecifique;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unite")
    public Unite getUnite(){
        return this.unite;
    }
    public void setUnite(Unite unite){
        this.unite = unite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsommationSpecifique consommationSpecifique = (ConsommationSpecifique) o;
        return id != null && id.equals(consommationSpecifique.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


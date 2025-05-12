package ma.zyn.app.bean.core.navire;






import ma.zyn.app.bean.core.referentiel.Produit;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "realisation_navire_produit")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="realisation_navire_produit_seq",sequenceName="realisation_navire_produit_seq",allocationSize=1, initialValue = 1)
public class RealisationNavireProduit  extends BaseEntity     {




    private BigDecimal tsm = BigDecimal.ZERO;

    private BigDecimal volume = BigDecimal.ZERO;

    private Produit produit ;
    private RealisationNavire realisationNavire ;


    public RealisationNavireProduit(){
        super();
    }

    public RealisationNavireProduit(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="realisation_navire_produit_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produit")
    public Produit getProduit(){
        return this.produit;
    }
    public void setProduit(Produit produit){
        this.produit = produit;
    }
    public BigDecimal getTsm(){
        return this.tsm;
    }
    public void setTsm(BigDecimal tsm){
        this.tsm = tsm;
    }
    public BigDecimal getVolume(){
        return this.volume;
    }
    public void setVolume(BigDecimal volume){
        this.volume = volume;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "realisation_navire")
    public RealisationNavire getRealisationNavire(){
        return this.realisationNavire;
    }
    public void setRealisationNavire(RealisationNavire realisationNavire){
        this.realisationNavire = realisationNavire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealisationNavireProduit realisationNavireProduit = (RealisationNavireProduit) o;
        return id != null && id.equals(realisationNavireProduit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


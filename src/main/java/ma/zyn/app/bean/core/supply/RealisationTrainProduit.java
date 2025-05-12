package ma.zyn.app.bean.core.supply;






import ma.zyn.app.bean.core.referentiel.Produit;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "realisation_train_produit")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="realisation_train_produit_seq",sequenceName="realisation_train_produit_seq",allocationSize=1, initialValue = 1)
public class RealisationTrainProduit  extends BaseEntity     {




    private BigDecimal volume = BigDecimal.ZERO;

    private BigDecimal tsm = BigDecimal.ZERO;

    private Produit produit ;
    private RealisationTrain realisationTrain ;


    public RealisationTrainProduit(){
        super();
    }

    public RealisationTrainProduit(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="realisation_train_produit_seq")
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
    public BigDecimal getVolume(){
        return this.volume;
    }
    public void setVolume(BigDecimal volume){
        this.volume = volume;
    }
    public BigDecimal getTsm(){
        return this.tsm;
    }
    public void setTsm(BigDecimal tsm){
        this.tsm = tsm;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "realisation_train")
    public RealisationTrain getRealisationTrain(){
        return this.realisationTrain;
    }
    public void setRealisationTrain(RealisationTrain realisationTrain){
        this.realisationTrain = realisationTrain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealisationTrainProduit realisationTrainProduit = (RealisationTrainProduit) o;
        return id != null && id.equals(realisationTrainProduit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


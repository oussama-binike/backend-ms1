package ma.zyn.app.bean.core.camion;






import ma.zyn.app.bean.core.referentiel.Produit;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "realisation_camion_produit")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="realisation_camion_produit_seq",sequenceName="realisation_camion_produit_seq",allocationSize=1, initialValue = 1)
public class RealisationCamionProduit  extends BaseEntity     {




    private BigDecimal tsm = BigDecimal.ZERO;

    private Produit produit ;
    private RealisationCamion realisationCamion ;


    public RealisationCamionProduit(){
        super();
    }

    public RealisationCamionProduit(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="realisation_camion_produit_seq")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "realisation_camion")
    public RealisationCamion getRealisationCamion(){
        return this.realisationCamion;
    }
    public void setRealisationCamion(RealisationCamion realisationCamion){
        this.realisationCamion = realisationCamion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealisationCamionProduit realisationCamionProduit = (RealisationCamionProduit) o;
        return id != null && id.equals(realisationCamionProduit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


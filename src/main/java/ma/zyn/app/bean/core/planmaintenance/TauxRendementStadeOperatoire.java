package ma.zyn.app.bean.core.planmaintenance;


import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.bean.core.scenario.ScenarioFlux;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "taux_rendement_stade_operatoire")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="taux_rendement_stade_operatoire_seq",sequenceName="taux_rendement_stade_operatoire_seq",allocationSize=1, initialValue = 1)
public class TauxRendementStadeOperatoire  extends BaseEntity     {




    private LocalDateTime jour ;

    private BigDecimal tauxRendementGlobal = BigDecimal.ZERO;

    private ScenarioFlux scenarioFlux ;


    public TauxRendementStadeOperatoire(){
        super();
    }

    public TauxRendementStadeOperatoire(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="taux_rendement_stade_operatoire_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public LocalDateTime getJour(){
        return this.jour;
    }
    public void setJour(LocalDateTime jour){
        this.jour = jour;
    }
    public BigDecimal getTauxRendementGlobal(){
        return this.tauxRendementGlobal;
    }
    public void setTauxRendementGlobal(BigDecimal tauxRendementGlobal){
        this.tauxRendementGlobal = tauxRendementGlobal;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scenario_flux")
    public ScenarioFlux getScenarioFlux(){
        return this.scenarioFlux;
    }
    public void setScenarioFlux(ScenarioFlux scenarioFlux){
        this.scenarioFlux = scenarioFlux;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TauxRendementStadeOperatoire tauxRendementStadeOperatoire = (TauxRendementStadeOperatoire) o;
        return id != null && id.equals(tauxRendementStadeOperatoire.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


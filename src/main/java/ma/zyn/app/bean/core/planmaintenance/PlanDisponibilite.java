package ma.zyn.app.bean.core.planmaintenance;


import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import ma.zyn.app.bean.core.scenario.ScenarioFlux;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "plan_disponibilite")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="plan_disponibilite_seq",sequenceName="plan_disponibilite_seq",allocationSize=1, initialValue = 1)
public class PlanDisponibilite  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;

    private BigDecimal nombreHeureArret = BigDecimal.ZERO;

    private LocalDateTime dateArretDebut ;

    private LocalDateTime dateArretFin ;

    private StadeOperatoire stadeOperatoire ;
    private ScenarioFlux scenarioFlux ;


    public PlanDisponibilite(){
        super();
    }

    public PlanDisponibilite(Long id){
        this.id = id;
    }

    public PlanDisponibilite(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public PlanDisponibilite(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="plan_disponibilite_seq")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stade_operatoire")
    public StadeOperatoire getStadeOperatoire(){
        return this.stadeOperatoire;
    }
    public void setStadeOperatoire(StadeOperatoire stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
    }
    public BigDecimal getNombreHeureArret(){
        return this.nombreHeureArret;
    }
    public void setNombreHeureArret(BigDecimal nombreHeureArret){
        this.nombreHeureArret = nombreHeureArret;
    }
    public LocalDateTime getDateArretDebut(){
        return this.dateArretDebut;
    }
    public void setDateArretDebut(LocalDateTime dateArretDebut){
        this.dateArretDebut = dateArretDebut;
    }
    public LocalDateTime getDateArretFin(){
        return this.dateArretFin;
    }
    public void setDateArretFin(LocalDateTime dateArretFin){
        this.dateArretFin = dateArretFin;
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
        PlanDisponibilite planDisponibilite = (PlanDisponibilite) o;
        return id != null && id.equals(planDisponibilite.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


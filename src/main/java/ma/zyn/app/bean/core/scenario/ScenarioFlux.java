package ma.zyn.app.bean.core.scenario;

import java.util.List;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.bean.core.referentiel.Liaison;
import ma.zyn.app.bean.core.referentiel.EtatDemande;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand;
import ma.zyn.app.bean.core.referentiel.TypeDemande;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import ma.zyn.app.bean.core.planmaintenance.TauxRendementStadeOperatoire;
import ma.zyn.app.bean.core.referentiel.StatusScenarioFlux;
import ma.zyn.app.bean.core.demande.Demande;
import ma.zyn.app.bean.core.planmaintenance.PlanDisponibilite;
import ma.zyn.app.bean.core.referentiel.Client;
import ma.zyn.app.bean.core.stock.SuiviStock;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "scenario_flux")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="scenario_flux_seq",sequenceName="scenario_flux_seq",allocationSize=1, initialValue = 1)
public class ScenarioFlux  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;

    private LocalDateTime dateEffet ;

    private Exercice exercice ;
    private StatusScenarioFlux statusScenarioFlux ;

    private List<Demande> demandes ;
    private List<PlanDisponibilite> planDisponibilites ;
    private List<TauxRendementStadeOperatoire> tauxRendementStadeOperatoires ;
    private List<SuiviStock> suiviStocks ;

    public ScenarioFlux(){
        super();
    }

    public ScenarioFlux(Long id){
        this.id = id;
    }

    public ScenarioFlux(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public ScenarioFlux(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="scenario_flux_seq")
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
    @JoinColumn(name = "exercice")
    public Exercice getExercice(){
        return this.exercice;
    }
    public void setExercice(Exercice exercice){
        this.exercice = exercice;
    }
    public LocalDateTime getDateEffet(){
        return this.dateEffet;
    }
    public void setDateEffet(LocalDateTime dateEffet){
        this.dateEffet = dateEffet;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_scenario_flux")
    public StatusScenarioFlux getStatusScenarioFlux(){
        return this.statusScenarioFlux;
    }
    public void setStatusScenarioFlux(StatusScenarioFlux statusScenarioFlux){
        this.statusScenarioFlux = statusScenarioFlux;
    }
    @OneToMany(mappedBy = "scenarioFlux")
    public List<Demande> getDemandes(){
        return this.demandes;
    }

    public void setDemandes(List<Demande> demandes){
        this.demandes = demandes;
    }
    @OneToMany(mappedBy = "scenarioFlux")
    public List<PlanDisponibilite> getPlanDisponibilites(){
        return this.planDisponibilites;
    }

    public void setPlanDisponibilites(List<PlanDisponibilite> planDisponibilites){
        this.planDisponibilites = planDisponibilites;
    }
    @OneToMany(mappedBy = "scenarioFlux")
    public List<TauxRendementStadeOperatoire> getTauxRendementStadeOperatoires(){
        return this.tauxRendementStadeOperatoires;
    }

    public void setTauxRendementStadeOperatoires(List<TauxRendementStadeOperatoire> tauxRendementStadeOperatoires){
        this.tauxRendementStadeOperatoires = tauxRendementStadeOperatoires;
    }
    @OneToMany(mappedBy = "scenarioFlux")
    public List<SuiviStock> getSuiviStocks(){
        return this.suiviStocks;
    }

    public void setSuiviStocks(List<SuiviStock> suiviStocks){
        this.suiviStocks = suiviStocks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScenarioFlux scenarioFlux = (ScenarioFlux) o;
        return id != null && id.equals(scenarioFlux.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


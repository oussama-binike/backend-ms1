package ma.zyn.app.bean.core.stock;


import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.bean.core.referentiel.Liaison;
import ma.zyn.app.bean.core.scenario.ScenarioFlux;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "suivi_stock")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="suivi_stock_seq",sequenceName="suivi_stock_seq",allocationSize=1, initialValue = 1)
public class SuiviStock  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;

    private BigDecimal volumeReel = BigDecimal.ZERO;

    private BigDecimal volumeEstime = BigDecimal.ZERO;

    private LocalDateTime dateFlux ;

    private Integer repereSourceDebut = 0;

    private Integer repereSourceFin = 0;

    private Integer repereDestinationDebut = 0;

    private Integer repereDestinationFin = 0;

    private Integer positionRouePelle = 0;

    private Integer positionStacker = 0;

    private Liaison liaison ;
    private ScenarioFlux scenarioFlux ;


    public SuiviStock(){
        super();
    }

    public SuiviStock(Long id){
        this.id = id;
    }

    public SuiviStock(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public SuiviStock(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="suivi_stock_seq")
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
    @JoinColumn(name = "liaison")
    public Liaison getLiaison(){
        return this.liaison;
    }
    public void setLiaison(Liaison liaison){
        this.liaison = liaison;
    }
    public BigDecimal getVolumeReel(){
        return this.volumeReel;
    }
    public void setVolumeReel(BigDecimal volumeReel){
        this.volumeReel = volumeReel;
    }
    public BigDecimal getVolumeEstime(){
        return this.volumeEstime;
    }
    public void setVolumeEstime(BigDecimal volumeEstime){
        this.volumeEstime = volumeEstime;
    }
    public LocalDateTime getDateFlux(){
        return this.dateFlux;
    }
    public void setDateFlux(LocalDateTime dateFlux){
        this.dateFlux = dateFlux;
    }
    public Integer getRepereSourceDebut(){
        return this.repereSourceDebut;
    }
    public void setRepereSourceDebut(Integer repereSourceDebut){
        this.repereSourceDebut = repereSourceDebut;
    }
    public Integer getRepereSourceFin(){
        return this.repereSourceFin;
    }
    public void setRepereSourceFin(Integer repereSourceFin){
        this.repereSourceFin = repereSourceFin;
    }
    public Integer getRepereDestinationDebut(){
        return this.repereDestinationDebut;
    }
    public void setRepereDestinationDebut(Integer repereDestinationDebut){
        this.repereDestinationDebut = repereDestinationDebut;
    }
    public Integer getRepereDestinationFin(){
        return this.repereDestinationFin;
    }
    public void setRepereDestinationFin(Integer repereDestinationFin){
        this.repereDestinationFin = repereDestinationFin;
    }
    public Integer getPositionRouePelle(){
        return this.positionRouePelle;
    }
    public void setPositionRouePelle(Integer positionRouePelle){
        this.positionRouePelle = positionRouePelle;
    }
    public Integer getPositionStacker(){
        return this.positionStacker;
    }
    public void setPositionStacker(Integer positionStacker){
        this.positionStacker = positionStacker;
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
        SuiviStock suiviStock = (SuiviStock) o;
        return id != null && id.equals(suiviStock.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


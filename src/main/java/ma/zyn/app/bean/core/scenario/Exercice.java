package ma.zyn.app.bean.core.scenario;


import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.bean.core.referentiel.StatusExercice;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "exercice")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="exercice_seq",sequenceName="exercice_seq",allocationSize=1, initialValue = 1)
public class Exercice  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;

    private Integer mois = 0;

    private Integer annee = 0;

    private LocalDateTime dateDebut ;

    private LocalDateTime dateFin ;

    private LocalDateTime dateRetrospective ;

    private StatusExercice statusExercice ;


    public Exercice(){
        super();
    }

    public Exercice(Long id){
        this.id = id;
    }

    public Exercice(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public Exercice(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="exercice_seq")
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
    public Integer getMois(){
        return this.mois;
    }
    public void setMois(Integer mois){
        this.mois = mois;
    }
    public Integer getAnnee(){
        return this.annee;
    }
    public void setAnnee(Integer annee){
        this.annee = annee;
    }
    public LocalDateTime getDateDebut(){
        return this.dateDebut;
    }
    public void setDateDebut(LocalDateTime dateDebut){
        this.dateDebut = dateDebut;
    }
    public LocalDateTime getDateFin(){
        return this.dateFin;
    }
    public void setDateFin(LocalDateTime dateFin){
        this.dateFin = dateFin;
    }
    public LocalDateTime getDateRetrospective(){
        return this.dateRetrospective;
    }
    public void setDateRetrospective(LocalDateTime dateRetrospective){
        this.dateRetrospective = dateRetrospective;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_exercice")
    public StatusExercice getStatusExercice(){
        return this.statusExercice;
    }
    public void setStatusExercice(StatusExercice statusExercice){
        this.statusExercice = statusExercice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercice exercice = (Exercice) o;
        return id != null && id.equals(exercice.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


package ma.zyn.app.bean.core.aleas;


import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import ma.zyn.app.bean.core.reclamation.ActionEntreprise;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "arret_non_planifie")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="arret_non_planifie_seq",sequenceName="arret_non_planifie_seq",allocationSize=1, initialValue = 1)
public class ArretNonPlanifie  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String commentaire;

    private BigDecimal dureeEstimee = BigDecimal.ZERO;

    private LocalDateTime dateArret ;

    private LocalDateTime dateDebut ;

    private LocalDateTime dateFin ;

    private StadeOperatoire stadeOperatoire ;
    private CauseArret causeArret ;
    private ActionEntreprise actionEntreprise ;


    public ArretNonPlanifie(){
        super();
    }

    public ArretNonPlanifie(Long id){
        this.id = id;
    }

    public ArretNonPlanifie(Long id,String code){
        this.id = id;
        this.code = code ;
    }
    public ArretNonPlanifie(String code){
        this.code = code ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="arret_non_planifie_seq")
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
    public String getCommentaire(){
        return this.commentaire;
    }
    public void setCommentaire(String commentaire){
        this.commentaire = commentaire;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stade_operatoire")
    public StadeOperatoire getStadeOperatoire(){
        return this.stadeOperatoire;
    }
    public void setStadeOperatoire(StadeOperatoire stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cause_arret")
    public CauseArret getCauseArret(){
        return this.causeArret;
    }
    public void setCauseArret(CauseArret causeArret){
        this.causeArret = causeArret;
    }
    public BigDecimal getDureeEstimee(){
        return this.dureeEstimee;
    }
    public void setDureeEstimee(BigDecimal dureeEstimee){
        this.dureeEstimee = dureeEstimee;
    }
    public LocalDateTime getDateArret(){
        return this.dateArret;
    }
    public void setDateArret(LocalDateTime dateArret){
        this.dateArret = dateArret;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_entreprise")
    public ActionEntreprise getActionEntreprise(){
        return this.actionEntreprise;
    }
    public void setActionEntreprise(ActionEntreprise actionEntreprise){
        this.actionEntreprise = actionEntreprise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArretNonPlanifie arretNonPlanifie = (ArretNonPlanifie) o;
        return id != null && id.equals(arretNonPlanifie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


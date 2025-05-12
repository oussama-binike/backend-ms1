package ma.zyn.app.bean.core.reclamation;

import java.util.List;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.bean.core.referentiel.ElementChimique;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand;
import ma.zyn.app.bean.core.referentiel.Entite;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "reclamation")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="reclamation_seq",sequenceName="reclamation_seq",allocationSize=1, initialValue = 1)
public class Reclamation  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String commentaire;

    private BigDecimal quantite = BigDecimal.ZERO;

    @Column(columnDefinition = "boolean default false")
    private Boolean fonde = false;

    @Column(length = 500)
    private String motifReclamation;

    private LocalDateTime dateOccurence ;

    private LocalDateTime dateReception ;

    @Column(length = 500)
    private String actionEntreprise;

    private Entite entiteEmettrice ;
    private Entite entiteDistinataire ;
    private ProduitMarchand produitMarchand ;
    private EtatReclamation etatReclamation ;

    private List<ReclamationElementChimique> reclamationElementChimiques ;

    public Reclamation(){
        super();
    }

    public Reclamation(Long id){
        this.id = id;
    }

    public Reclamation(Long id,String code){
        this.id = id;
        this.code = code ;
    }
    public Reclamation(String code){
        this.code = code ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="reclamation_seq")
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
    @JoinColumn(name = "entite_emettrice")
    public Entite getEntiteEmettrice(){
        return this.entiteEmettrice;
    }
    public void setEntiteEmettrice(Entite entiteEmettrice){
        this.entiteEmettrice = entiteEmettrice;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entite_distinataire")
    public Entite getEntiteDistinataire(){
        return this.entiteDistinataire;
    }
    public void setEntiteDistinataire(Entite entiteDistinataire){
        this.entiteDistinataire = entiteDistinataire;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produit_marchand")
    public ProduitMarchand getProduitMarchand(){
        return this.produitMarchand;
    }
    public void setProduitMarchand(ProduitMarchand produitMarchand){
        this.produitMarchand = produitMarchand;
    }
    public BigDecimal getQuantite(){
        return this.quantite;
    }
    public void setQuantite(BigDecimal quantite){
        this.quantite = quantite;
    }
    public Boolean  getFonde(){
        return this.fonde;
    }
    public void setFonde(Boolean fonde){
        this.fonde = fonde;
    }
    public String getMotifReclamation(){
        return this.motifReclamation;
    }
    public void setMotifReclamation(String motifReclamation){
        this.motifReclamation = motifReclamation;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etat_reclamation")
    public EtatReclamation getEtatReclamation(){
        return this.etatReclamation;
    }
    public void setEtatReclamation(EtatReclamation etatReclamation){
        this.etatReclamation = etatReclamation;
    }
    public LocalDateTime getDateOccurence(){
        return this.dateOccurence;
    }
    public void setDateOccurence(LocalDateTime dateOccurence){
        this.dateOccurence = dateOccurence;
    }
    public LocalDateTime getDateReception(){
        return this.dateReception;
    }
    public void setDateReception(LocalDateTime dateReception){
        this.dateReception = dateReception;
    }
    public String getActionEntreprise(){
        return this.actionEntreprise;
    }
    public void setActionEntreprise(String actionEntreprise){
        this.actionEntreprise = actionEntreprise;
    }
    @OneToMany(mappedBy = "reclamation")
    public List<ReclamationElementChimique> getReclamationElementChimiques(){
        return this.reclamationElementChimiques;
    }

    public void setReclamationElementChimiques(List<ReclamationElementChimique> reclamationElementChimiques){
        this.reclamationElementChimiques = reclamationElementChimiques;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reclamation reclamation = (Reclamation) o;
        return id != null && id.equals(reclamation.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}


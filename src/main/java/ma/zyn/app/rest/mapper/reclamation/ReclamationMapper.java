package  ma.zyn.app.rest.mapper.reclamation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.mapper.reclamation.ReclamationElementChimiqueMapper;
import ma.zyn.app.bean.core.reclamation.ReclamationElementChimique;
import ma.zyn.app.ws.mapper.referentiel.ElementChimiqueMapper;
import ma.zyn.app.bean.core.referentiel.ElementChimique;
import ma.zyn.app.ws.mapper.referentiel.ProduitMarchandMapper;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand;
import ma.zyn.app.ws.mapper.reclamation.EtatReclamationMapper;
import ma.zyn.app.bean.core.reclamation.EtatReclamation;
import ma.zyn.app.ws.mapper.referentiel.EntiteMapper;
import ma.zyn.app.bean.core.referentiel.Entite;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.reclamation.Reclamation;
import ma.zyn.app.ws.dto.reclamation.ReclamationDto;

@Component
public class ReclamationMapper {

    @Autowired
    private ReclamationElementChimiqueMapper reclamationElementChimiqueMapper ;
    @Autowired
    private ElementChimiqueMapper elementChimiqueMapper ;
    @Autowired
    private ProduitMarchandMapper produitMarchandMapper ;
    @Autowired
    private EtatReclamationMapper etatReclamationMapper ;
    @Autowired
    private EntiteMapper entiteMapper ;
    private boolean entiteEmettrice;
    private boolean entiteDistinataire;
    private boolean produitMarchand;
    private boolean etatReclamation;
    private boolean reclamationElementChimiques;

    public  ReclamationMapper() {
        init(true);
    }

    public Reclamation toItem(ReclamationDto dto) {
        if (dto == null) {
            return null;
        } else {
        Reclamation item = new Reclamation();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getCommentaire()))
                item.setCommentaire(dto.getCommentaire());
            if(StringUtil.isNotEmpty(dto.getQuantite()))
                item.setQuantite(dto.getQuantite());
            if(dto.getFonde() != null)
                item.setFonde(dto.getFonde());
            if(StringUtil.isNotEmpty(dto.getMotifReclamation()))
                item.setMotifReclamation(dto.getMotifReclamation());
            if(StringUtil.isNotEmpty(dto.getDateOccurence()))
                item.setDateOccurence(DateUtil.stringEnToDate(dto.getDateOccurence()));
            if(StringUtil.isNotEmpty(dto.getDateReception()))
                item.setDateReception(DateUtil.stringEnToDate(dto.getDateReception()));
            if(StringUtil.isNotEmpty(dto.getActionEntreprise()))
                item.setActionEntreprise(dto.getActionEntreprise());
            if(this.entiteEmettrice && dto.getEntiteEmettrice()!=null)
                item.setEntiteEmettrice(entiteMapper.toItem(dto.getEntiteEmettrice())) ;

            if(this.entiteDistinataire && dto.getEntiteDistinataire()!=null)
                item.setEntiteDistinataire(entiteMapper.toItem(dto.getEntiteDistinataire())) ;

            if(this.produitMarchand && dto.getProduitMarchand()!=null)
                item.setProduitMarchand(produitMarchandMapper.toItem(dto.getProduitMarchand())) ;

            if(this.etatReclamation && dto.getEtatReclamation()!=null)
                item.setEtatReclamation(etatReclamationMapper.toItem(dto.getEtatReclamation())) ;


            if(this.reclamationElementChimiques && ListUtil.isNotEmpty(dto.getReclamationElementChimiques()))
                item.setReclamationElementChimiques(reclamationElementChimiqueMapper.toItem(dto.getReclamationElementChimiques()));


        return item;
        }
    }


    public ReclamationDto toDto(Reclamation item) {
        if (item == null) {
            return null;
        } else {
            ReclamationDto dto = new ReclamationDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getCommentaire()))
                dto.setCommentaire(item.getCommentaire());
            if(StringUtil.isNotEmpty(item.getQuantite()))
                dto.setQuantite(item.getQuantite());
                dto.setFonde(item.getFonde());
            if(StringUtil.isNotEmpty(item.getMotifReclamation()))
                dto.setMotifReclamation(item.getMotifReclamation());
            if(item.getDateOccurence()!=null)
                dto.setDateOccurence(DateUtil.dateTimeToString(item.getDateOccurence()));
            if(item.getDateReception()!=null)
                dto.setDateReception(DateUtil.dateTimeToString(item.getDateReception()));
            if(StringUtil.isNotEmpty(item.getActionEntreprise()))
                dto.setActionEntreprise(item.getActionEntreprise());
            if(this.entiteEmettrice && item.getEntiteEmettrice()!=null) {
                dto.setEntiteEmettrice(entiteMapper.toDto(item.getEntiteEmettrice())) ;

            }
            if(this.entiteDistinataire && item.getEntiteDistinataire()!=null) {
                dto.setEntiteDistinataire(entiteMapper.toDto(item.getEntiteDistinataire())) ;

            }
            if(this.produitMarchand && item.getProduitMarchand()!=null) {
                dto.setProduitMarchand(produitMarchandMapper.toDto(item.getProduitMarchand())) ;

            }
            if(this.etatReclamation && item.getEtatReclamation()!=null) {
                dto.setEtatReclamation(etatReclamationMapper.toDto(item.getEtatReclamation())) ;

            }
        if(this.reclamationElementChimiques && ListUtil.isNotEmpty(item.getReclamationElementChimiques())){
            reclamationElementChimiqueMapper.init(true);
            reclamationElementChimiqueMapper.setReclamation(false);
            dto.setReclamationElementChimiques(reclamationElementChimiqueMapper.toDto(item.getReclamationElementChimiques()));
            reclamationElementChimiqueMapper.setReclamation(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.reclamationElementChimiques = value;
    }
    public void initObject(boolean value) {
        this.entiteEmettrice = value;
        this.entiteDistinataire = value;
        this.produitMarchand = value;
        this.etatReclamation = value;
    }

    public List<Reclamation> toItem(List<ReclamationDto> dtos) {
        List<Reclamation> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ReclamationDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ReclamationDto> toDto(List<Reclamation> items) {
        List<ReclamationDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Reclamation item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ReclamationDto dto, Reclamation t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getEntiteEmettrice() == null  && dto.getEntiteEmettrice() != null){
            t.setEntiteEmettrice(new Entite());
        }else if (t.getEntiteEmettrice() != null  && dto.getEntiteEmettrice() != null){
            t.setEntiteEmettrice(null);
            t.setEntiteEmettrice(new Entite());
        }
        if(t.getEntiteDistinataire() == null  && dto.getEntiteDistinataire() != null){
            t.setEntiteDistinataire(new Entite());
        }else if (t.getEntiteDistinataire() != null  && dto.getEntiteDistinataire() != null){
            t.setEntiteDistinataire(null);
            t.setEntiteDistinataire(new Entite());
        }
        if(t.getProduitMarchand() == null  && dto.getProduitMarchand() != null){
            t.setProduitMarchand(new ProduitMarchand());
        }else if (t.getProduitMarchand() != null  && dto.getProduitMarchand() != null){
            t.setProduitMarchand(null);
            t.setProduitMarchand(new ProduitMarchand());
        }
        if(t.getEtatReclamation() == null  && dto.getEtatReclamation() != null){
            t.setEtatReclamation(new EtatReclamation());
        }else if (t.getEtatReclamation() != null  && dto.getEtatReclamation() != null){
            t.setEtatReclamation(null);
            t.setEtatReclamation(new EtatReclamation());
        }
        if (dto.getEntiteEmettrice() != null)
        entiteMapper.copy(dto.getEntiteEmettrice(), t.getEntiteEmettrice());
        if (dto.getEntiteDistinataire() != null)
        entiteMapper.copy(dto.getEntiteDistinataire(), t.getEntiteDistinataire());
        if (dto.getProduitMarchand() != null)
        produitMarchandMapper.copy(dto.getProduitMarchand(), t.getProduitMarchand());
        if (dto.getEtatReclamation() != null)
        etatReclamationMapper.copy(dto.getEtatReclamation(), t.getEtatReclamation());
        if (dto.getReclamationElementChimiques() != null)
            t.setReclamationElementChimiques(reclamationElementChimiqueMapper.copy(dto.getReclamationElementChimiques()));
    }

    public List<Reclamation> copy(List<ReclamationDto> dtos) {
        List<Reclamation> result = new ArrayList<>();
        if (dtos != null) {
            for (ReclamationDto dto : dtos) {
                Reclamation instance = new Reclamation();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ReclamationElementChimiqueMapper getReclamationElementChimiqueMapper(){
        return this.reclamationElementChimiqueMapper;
    }
    public void setReclamationElementChimiqueMapper(ReclamationElementChimiqueMapper reclamationElementChimiqueMapper ){
        this.reclamationElementChimiqueMapper = reclamationElementChimiqueMapper;
    }
    public ElementChimiqueMapper getElementChimiqueMapper(){
        return this.elementChimiqueMapper;
    }
    public void setElementChimiqueMapper(ElementChimiqueMapper elementChimiqueMapper ){
        this.elementChimiqueMapper = elementChimiqueMapper;
    }
    public ProduitMarchandMapper getProduitMarchandMapper(){
        return this.produitMarchandMapper;
    }
    public void setProduitMarchandMapper(ProduitMarchandMapper produitMarchandMapper ){
        this.produitMarchandMapper = produitMarchandMapper;
    }
    public EtatReclamationMapper getEtatReclamationMapper(){
        return this.etatReclamationMapper;
    }
    public void setEtatReclamationMapper(EtatReclamationMapper etatReclamationMapper ){
        this.etatReclamationMapper = etatReclamationMapper;
    }
    public EntiteMapper getEntiteMapper(){
        return this.entiteMapper;
    }
    public void setEntiteMapper(EntiteMapper entiteMapper ){
        this.entiteMapper = entiteMapper;
    }
    public boolean  isEntiteEmettrice(){
        return this.entiteEmettrice;
    }
    public void  setEntiteEmettrice(boolean entiteEmettrice){
        this.entiteEmettrice = entiteEmettrice;
    }
    public boolean  isEntiteDistinataire(){
        return this.entiteDistinataire;
    }
    public void  setEntiteDistinataire(boolean entiteDistinataire){
        this.entiteDistinataire = entiteDistinataire;
    }
    public boolean  isProduitMarchand(){
        return this.produitMarchand;
    }
    public void  setProduitMarchand(boolean produitMarchand){
        this.produitMarchand = produitMarchand;
    }
    public boolean  isEtatReclamation(){
        return this.etatReclamation;
    }
    public void  setEtatReclamation(boolean etatReclamation){
        this.etatReclamation = etatReclamation;
    }
    public boolean  isReclamationElementChimiques(){
        return this.reclamationElementChimiques ;
    }
    public void  setReclamationElementChimiques(boolean reclamationElementChimiques ){
        this.reclamationElementChimiques  = reclamationElementChimiques ;
    }
}

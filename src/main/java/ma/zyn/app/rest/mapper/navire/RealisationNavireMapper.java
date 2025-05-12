package  ma.zyn.app.rest.mapper.navire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.mapper.navire.DestinationNavireMapper;
import ma.zyn.app.bean.core.navire.DestinationNavire;
import ma.zyn.app.ws.mapper.navire.RealisationNavireQualiteMapper;
import ma.zyn.app.bean.core.navire.RealisationNavireQualite;
import ma.zyn.app.ws.mapper.referentiel.ProduitMarchandMapper;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand;
import ma.zyn.app.ws.mapper.navire.RealisationNavireProduitMapper;
import ma.zyn.app.bean.core.navire.RealisationNavireProduit;
import ma.zyn.app.ws.mapper.referentiel.ProduitMapper;
import ma.zyn.app.bean.core.referentiel.Produit;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.navire.RealisationNavire;
import ma.zyn.app.ws.dto.navire.RealisationNavireDto;

@Component
public class RealisationNavireMapper {

    @Autowired
    private DestinationNavireMapper destinationNavireMapper ;
    @Autowired
    private RealisationNavireQualiteMapper realisationNavireQualiteMapper ;
    @Autowired
    private ProduitMarchandMapper produitMarchandMapper ;
    @Autowired
    private RealisationNavireProduitMapper realisationNavireProduitMapper ;
    @Autowired
    private ProduitMapper produitMapper ;
    private boolean destinationNavire;
    private boolean realisationNavireProduits;
    private boolean realisationNavireQualites;

    public  RealisationNavireMapper() {
        init(true);
    }

    public RealisationNavire toItem(RealisationNavireDto dto) {
        if (dto == null) {
            return null;
        } else {
        RealisationNavire item = new RealisationNavire();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getNumeroNavire()))
                item.setNumeroNavire(dto.getNumeroNavire());
            if(StringUtil.isNotEmpty(dto.getNumeroExpedition()))
                item.setNumeroExpedition(dto.getNumeroExpedition());
            if(StringUtil.isNotEmpty(dto.getJour()))
                item.setJour(DateUtil.stringEnToDate(dto.getJour()));
            if(StringUtil.isNotEmpty(dto.getTauxCompletude()))
                item.setTauxCompletude(dto.getTauxCompletude());
            if(StringUtil.isNotEmpty(dto.getTauxRemplissage()))
                item.setTauxRemplissage(dto.getTauxRemplissage());
            if(StringUtil.isNotEmpty(dto.getDateChargement()))
                item.setDateChargement(DateUtil.stringEnToDate(dto.getDateChargement()));
            if(StringUtil.isNotEmpty(dto.getDateFinChargement()))
                item.setDateFinChargement(DateUtil.stringEnToDate(dto.getDateFinChargement()));
            if(this.destinationNavire && dto.getDestinationNavire()!=null)
                item.setDestinationNavire(destinationNavireMapper.toItem(dto.getDestinationNavire())) ;


            if(this.realisationNavireProduits && ListUtil.isNotEmpty(dto.getRealisationNavireProduits()))
                item.setRealisationNavireProduits(realisationNavireProduitMapper.toItem(dto.getRealisationNavireProduits()));
            if(this.realisationNavireQualites && ListUtil.isNotEmpty(dto.getRealisationNavireQualites()))
                item.setRealisationNavireQualites(realisationNavireQualiteMapper.toItem(dto.getRealisationNavireQualites()));


        return item;
        }
    }


    public RealisationNavireDto toDto(RealisationNavire item) {
        if (item == null) {
            return null;
        } else {
            RealisationNavireDto dto = new RealisationNavireDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getNumeroNavire()))
                dto.setNumeroNavire(item.getNumeroNavire());
            if(StringUtil.isNotEmpty(item.getNumeroExpedition()))
                dto.setNumeroExpedition(item.getNumeroExpedition());
            if(item.getJour()!=null)
                dto.setJour(DateUtil.dateTimeToString(item.getJour()));
            if(StringUtil.isNotEmpty(item.getTauxCompletude()))
                dto.setTauxCompletude(item.getTauxCompletude());
            if(StringUtil.isNotEmpty(item.getTauxRemplissage()))
                dto.setTauxRemplissage(item.getTauxRemplissage());
            if(item.getDateChargement()!=null)
                dto.setDateChargement(DateUtil.dateTimeToString(item.getDateChargement()));
            if(item.getDateFinChargement()!=null)
                dto.setDateFinChargement(DateUtil.dateTimeToString(item.getDateFinChargement()));
            if(this.destinationNavire && item.getDestinationNavire()!=null) {
                dto.setDestinationNavire(destinationNavireMapper.toDto(item.getDestinationNavire())) ;

            }
        if(this.realisationNavireProduits && ListUtil.isNotEmpty(item.getRealisationNavireProduits())){
            realisationNavireProduitMapper.init(true);
            realisationNavireProduitMapper.setRealisationNavire(false);
            dto.setRealisationNavireProduits(realisationNavireProduitMapper.toDto(item.getRealisationNavireProduits()));
            realisationNavireProduitMapper.setRealisationNavire(true);

        }
        if(this.realisationNavireQualites && ListUtil.isNotEmpty(item.getRealisationNavireQualites())){
            realisationNavireQualiteMapper.init(true);
            realisationNavireQualiteMapper.setRealisationNavire(false);
            dto.setRealisationNavireQualites(realisationNavireQualiteMapper.toDto(item.getRealisationNavireQualites()));
            realisationNavireQualiteMapper.setRealisationNavire(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.realisationNavireProduits = value;
        this.realisationNavireQualites = value;
    }
    public void initObject(boolean value) {
        this.destinationNavire = value;
    }

    public List<RealisationNavire> toItem(List<RealisationNavireDto> dtos) {
        List<RealisationNavire> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (RealisationNavireDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<RealisationNavireDto> toDto(List<RealisationNavire> items) {
        List<RealisationNavireDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (RealisationNavire item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(RealisationNavireDto dto, RealisationNavire t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getDestinationNavire() == null  && dto.getDestinationNavire() != null){
            t.setDestinationNavire(new DestinationNavire());
        }else if (t.getDestinationNavire() != null  && dto.getDestinationNavire() != null){
            t.setDestinationNavire(null);
            t.setDestinationNavire(new DestinationNavire());
        }
        if (dto.getDestinationNavire() != null)
        destinationNavireMapper.copy(dto.getDestinationNavire(), t.getDestinationNavire());
        if (dto.getRealisationNavireProduits() != null)
            t.setRealisationNavireProduits(realisationNavireProduitMapper.copy(dto.getRealisationNavireProduits()));
        if (dto.getRealisationNavireQualites() != null)
            t.setRealisationNavireQualites(realisationNavireQualiteMapper.copy(dto.getRealisationNavireQualites()));
    }

    public List<RealisationNavire> copy(List<RealisationNavireDto> dtos) {
        List<RealisationNavire> result = new ArrayList<>();
        if (dtos != null) {
            for (RealisationNavireDto dto : dtos) {
                RealisationNavire instance = new RealisationNavire();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public DestinationNavireMapper getDestinationNavireMapper(){
        return this.destinationNavireMapper;
    }
    public void setDestinationNavireMapper(DestinationNavireMapper destinationNavireMapper ){
        this.destinationNavireMapper = destinationNavireMapper;
    }
    public RealisationNavireQualiteMapper getRealisationNavireQualiteMapper(){
        return this.realisationNavireQualiteMapper;
    }
    public void setRealisationNavireQualiteMapper(RealisationNavireQualiteMapper realisationNavireQualiteMapper ){
        this.realisationNavireQualiteMapper = realisationNavireQualiteMapper;
    }
    public ProduitMarchandMapper getProduitMarchandMapper(){
        return this.produitMarchandMapper;
    }
    public void setProduitMarchandMapper(ProduitMarchandMapper produitMarchandMapper ){
        this.produitMarchandMapper = produitMarchandMapper;
    }
    public RealisationNavireProduitMapper getRealisationNavireProduitMapper(){
        return this.realisationNavireProduitMapper;
    }
    public void setRealisationNavireProduitMapper(RealisationNavireProduitMapper realisationNavireProduitMapper ){
        this.realisationNavireProduitMapper = realisationNavireProduitMapper;
    }
    public ProduitMapper getProduitMapper(){
        return this.produitMapper;
    }
    public void setProduitMapper(ProduitMapper produitMapper ){
        this.produitMapper = produitMapper;
    }
    public boolean  isDestinationNavire(){
        return this.destinationNavire;
    }
    public void  setDestinationNavire(boolean destinationNavire){
        this.destinationNavire = destinationNavire;
    }
    public boolean  isRealisationNavireProduits(){
        return this.realisationNavireProduits ;
    }
    public void  setRealisationNavireProduits(boolean realisationNavireProduits ){
        this.realisationNavireProduits  = realisationNavireProduits ;
    }
    public boolean  isRealisationNavireQualites(){
        return this.realisationNavireQualites ;
    }
    public void  setRealisationNavireQualites(boolean realisationNavireQualites ){
        this.realisationNavireQualites  = realisationNavireQualites ;
    }
}

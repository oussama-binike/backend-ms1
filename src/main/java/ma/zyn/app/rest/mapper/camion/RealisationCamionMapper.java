package  ma.zyn.app.rest.mapper.camion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.mapper.camion.RealisationCamionProduitMapper;
import ma.zyn.app.bean.core.camion.RealisationCamionProduit;
import ma.zyn.app.ws.mapper.camion.ProvennanceCamionMapper;
import ma.zyn.app.bean.core.camion.ProvennanceCamion;
import ma.zyn.app.ws.mapper.camion.DestinationCamionMapper;
import ma.zyn.app.bean.core.camion.DestinationCamion;
import ma.zyn.app.ws.mapper.referentiel.ProduitMapper;
import ma.zyn.app.bean.core.referentiel.Produit;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.camion.RealisationCamion;
import ma.zyn.app.ws.dto.camion.RealisationCamionDto;

@Component
public class RealisationCamionMapper {

    @Autowired
    private RealisationCamionProduitMapper realisationCamionProduitMapper ;
    @Autowired
    private ProvennanceCamionMapper provennanceCamionMapper ;
    @Autowired
    private DestinationCamionMapper destinationCamionMapper ;
    @Autowired
    private ProduitMapper produitMapper ;
    private boolean provennanceCamion;
    private boolean destinationCamion;
    private boolean realisationCamionProduits;

    public  RealisationCamionMapper() {
        init(true);
    }

    public RealisationCamion toItem(RealisationCamionDto dto) {
        if (dto == null) {
            return null;
        } else {
        RealisationCamion item = new RealisationCamion();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getJour()))
                item.setJour(DateUtil.stringEnToDate(dto.getJour()));
            if(StringUtil.isNotEmpty(dto.getNombreCamions()))
                item.setNombreCamions(dto.getNombreCamions());
            if(StringUtil.isNotEmpty(dto.getDureeMoyenneTransport()))
                item.setDureeMoyenneTransport(dto.getDureeMoyenneTransport());
            if(StringUtil.isNotEmpty(dto.getTotalTms()))
                item.setTotalTms(dto.getTotalTms());
            if(this.provennanceCamion && dto.getProvennanceCamion()!=null)
                item.setProvennanceCamion(provennanceCamionMapper.toItem(dto.getProvennanceCamion())) ;

            if(this.destinationCamion && dto.getDestinationCamion()!=null)
                item.setDestinationCamion(destinationCamionMapper.toItem(dto.getDestinationCamion())) ;


            if(this.realisationCamionProduits && ListUtil.isNotEmpty(dto.getRealisationCamionProduits()))
                item.setRealisationCamionProduits(realisationCamionProduitMapper.toItem(dto.getRealisationCamionProduits()));


        return item;
        }
    }


    public RealisationCamionDto toDto(RealisationCamion item) {
        if (item == null) {
            return null;
        } else {
            RealisationCamionDto dto = new RealisationCamionDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(item.getJour()!=null)
                dto.setJour(DateUtil.dateTimeToString(item.getJour()));
            if(StringUtil.isNotEmpty(item.getNombreCamions()))
                dto.setNombreCamions(item.getNombreCamions());
            if(StringUtil.isNotEmpty(item.getDureeMoyenneTransport()))
                dto.setDureeMoyenneTransport(item.getDureeMoyenneTransport());
            if(StringUtil.isNotEmpty(item.getTotalTms()))
                dto.setTotalTms(item.getTotalTms());
            if(this.provennanceCamion && item.getProvennanceCamion()!=null) {
                dto.setProvennanceCamion(provennanceCamionMapper.toDto(item.getProvennanceCamion())) ;

            }
            if(this.destinationCamion && item.getDestinationCamion()!=null) {
                dto.setDestinationCamion(destinationCamionMapper.toDto(item.getDestinationCamion())) ;

            }
        if(this.realisationCamionProduits && ListUtil.isNotEmpty(item.getRealisationCamionProduits())){
            realisationCamionProduitMapper.init(true);
            realisationCamionProduitMapper.setRealisationCamion(false);
            dto.setRealisationCamionProduits(realisationCamionProduitMapper.toDto(item.getRealisationCamionProduits()));
            realisationCamionProduitMapper.setRealisationCamion(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.realisationCamionProduits = value;
    }
    public void initObject(boolean value) {
        this.provennanceCamion = value;
        this.destinationCamion = value;
    }

    public List<RealisationCamion> toItem(List<RealisationCamionDto> dtos) {
        List<RealisationCamion> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (RealisationCamionDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<RealisationCamionDto> toDto(List<RealisationCamion> items) {
        List<RealisationCamionDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (RealisationCamion item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(RealisationCamionDto dto, RealisationCamion t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getProvennanceCamion() == null  && dto.getProvennanceCamion() != null){
            t.setProvennanceCamion(new ProvennanceCamion());
        }else if (t.getProvennanceCamion() != null  && dto.getProvennanceCamion() != null){
            t.setProvennanceCamion(null);
            t.setProvennanceCamion(new ProvennanceCamion());
        }
        if(t.getDestinationCamion() == null  && dto.getDestinationCamion() != null){
            t.setDestinationCamion(new DestinationCamion());
        }else if (t.getDestinationCamion() != null  && dto.getDestinationCamion() != null){
            t.setDestinationCamion(null);
            t.setDestinationCamion(new DestinationCamion());
        }
        if (dto.getProvennanceCamion() != null)
        provennanceCamionMapper.copy(dto.getProvennanceCamion(), t.getProvennanceCamion());
        if (dto.getDestinationCamion() != null)
        destinationCamionMapper.copy(dto.getDestinationCamion(), t.getDestinationCamion());
        if (dto.getRealisationCamionProduits() != null)
            t.setRealisationCamionProduits(realisationCamionProduitMapper.copy(dto.getRealisationCamionProduits()));
    }

    public List<RealisationCamion> copy(List<RealisationCamionDto> dtos) {
        List<RealisationCamion> result = new ArrayList<>();
        if (dtos != null) {
            for (RealisationCamionDto dto : dtos) {
                RealisationCamion instance = new RealisationCamion();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public RealisationCamionProduitMapper getRealisationCamionProduitMapper(){
        return this.realisationCamionProduitMapper;
    }
    public void setRealisationCamionProduitMapper(RealisationCamionProduitMapper realisationCamionProduitMapper ){
        this.realisationCamionProduitMapper = realisationCamionProduitMapper;
    }
    public ProvennanceCamionMapper getProvennanceCamionMapper(){
        return this.provennanceCamionMapper;
    }
    public void setProvennanceCamionMapper(ProvennanceCamionMapper provennanceCamionMapper ){
        this.provennanceCamionMapper = provennanceCamionMapper;
    }
    public DestinationCamionMapper getDestinationCamionMapper(){
        return this.destinationCamionMapper;
    }
    public void setDestinationCamionMapper(DestinationCamionMapper destinationCamionMapper ){
        this.destinationCamionMapper = destinationCamionMapper;
    }
    public ProduitMapper getProduitMapper(){
        return this.produitMapper;
    }
    public void setProduitMapper(ProduitMapper produitMapper ){
        this.produitMapper = produitMapper;
    }
    public boolean  isProvennanceCamion(){
        return this.provennanceCamion;
    }
    public void  setProvennanceCamion(boolean provennanceCamion){
        this.provennanceCamion = provennanceCamion;
    }
    public boolean  isDestinationCamion(){
        return this.destinationCamion;
    }
    public void  setDestinationCamion(boolean destinationCamion){
        this.destinationCamion = destinationCamion;
    }
    public boolean  isRealisationCamionProduits(){
        return this.realisationCamionProduits ;
    }
    public void  setRealisationCamionProduits(boolean realisationCamionProduits ){
        this.realisationCamionProduits  = realisationCamionProduits ;
    }
}

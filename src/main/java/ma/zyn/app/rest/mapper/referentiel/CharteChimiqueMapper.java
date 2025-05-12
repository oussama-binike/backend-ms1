package  ma.zyn.app.rest.mapper.referentiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.mapper.referentiel.ElementChimiqueMapper;
import ma.zyn.app.bean.core.referentiel.ElementChimique;
import ma.zyn.app.ws.mapper.referentiel.ProduitMapper;
import ma.zyn.app.bean.core.referentiel.Produit;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.referentiel.CharteChimique;
import ma.zyn.app.ws.dto.referentiel.CharteChimiqueDto;

@Component
public class CharteChimiqueMapper {

    @Autowired
    private ElementChimiqueMapper elementChimiqueMapper ;
    @Autowired
    private ProduitMapper produitMapper ;
    private boolean produit;
    private boolean elementChimique;

    public  CharteChimiqueMapper() {
        initObject(true);
    }

    public CharteChimique toItem(CharteChimiqueDto dto) {
        if (dto == null) {
            return null;
        } else {
        CharteChimique item = new CharteChimique();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getMinimum()))
                item.setMinimum(dto.getMinimum());
            if(StringUtil.isNotEmpty(dto.getMaximum()))
                item.setMaximum(dto.getMaximum());
            if(StringUtil.isNotEmpty(dto.getAverage()))
                item.setAverage(dto.getAverage());
            if(StringUtil.isNotEmpty(dto.getMethodeAnalyse()))
                item.setMethodeAnalyse(dto.getMethodeAnalyse());
            if(StringUtil.isNotEmpty(dto.getUnite()))
                item.setUnite(dto.getUnite());
            if(this.produit && dto.getProduit()!=null)
                item.setProduit(produitMapper.toItem(dto.getProduit())) ;

            if(this.elementChimique && dto.getElementChimique()!=null)
                item.setElementChimique(elementChimiqueMapper.toItem(dto.getElementChimique())) ;




        return item;
        }
    }


    public CharteChimiqueDto toDto(CharteChimique item) {
        if (item == null) {
            return null;
        } else {
            CharteChimiqueDto dto = new CharteChimiqueDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getMinimum()))
                dto.setMinimum(item.getMinimum());
            if(StringUtil.isNotEmpty(item.getMaximum()))
                dto.setMaximum(item.getMaximum());
            if(StringUtil.isNotEmpty(item.getAverage()))
                dto.setAverage(item.getAverage());
            if(StringUtil.isNotEmpty(item.getMethodeAnalyse()))
                dto.setMethodeAnalyse(item.getMethodeAnalyse());
            if(StringUtil.isNotEmpty(item.getUnite()))
                dto.setUnite(item.getUnite());
            if(this.produit && item.getProduit()!=null) {
                dto.setProduit(produitMapper.toDto(item.getProduit())) ;

            }
            if(this.elementChimique && item.getElementChimique()!=null) {
                dto.setElementChimique(elementChimiqueMapper.toDto(item.getElementChimique())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.produit = value;
        this.elementChimique = value;
    }

    public List<CharteChimique> toItem(List<CharteChimiqueDto> dtos) {
        List<CharteChimique> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CharteChimiqueDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CharteChimiqueDto> toDto(List<CharteChimique> items) {
        List<CharteChimiqueDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (CharteChimique item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CharteChimiqueDto dto, CharteChimique t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getProduit() == null  && dto.getProduit() != null){
            t.setProduit(new Produit());
        }else if (t.getProduit() != null  && dto.getProduit() != null){
            t.setProduit(null);
            t.setProduit(new Produit());
        }
        if(t.getElementChimique() == null  && dto.getElementChimique() != null){
            t.setElementChimique(new ElementChimique());
        }else if (t.getElementChimique() != null  && dto.getElementChimique() != null){
            t.setElementChimique(null);
            t.setElementChimique(new ElementChimique());
        }
        if (dto.getProduit() != null)
        produitMapper.copy(dto.getProduit(), t.getProduit());
        if (dto.getElementChimique() != null)
        elementChimiqueMapper.copy(dto.getElementChimique(), t.getElementChimique());
    }

    public List<CharteChimique> copy(List<CharteChimiqueDto> dtos) {
        List<CharteChimique> result = new ArrayList<>();
        if (dtos != null) {
            for (CharteChimiqueDto dto : dtos) {
                CharteChimique instance = new CharteChimique();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ElementChimiqueMapper getElementChimiqueMapper(){
        return this.elementChimiqueMapper;
    }
    public void setElementChimiqueMapper(ElementChimiqueMapper elementChimiqueMapper ){
        this.elementChimiqueMapper = elementChimiqueMapper;
    }
    public ProduitMapper getProduitMapper(){
        return this.produitMapper;
    }
    public void setProduitMapper(ProduitMapper produitMapper ){
        this.produitMapper = produitMapper;
    }
    public boolean  isProduit(){
        return this.produit;
    }
    public void  setProduit(boolean produit){
        this.produit = produit;
    }
    public boolean  isElementChimique(){
        return this.elementChimique;
    }
    public void  setElementChimique(boolean elementChimique){
        this.elementChimique = elementChimique;
    }
}

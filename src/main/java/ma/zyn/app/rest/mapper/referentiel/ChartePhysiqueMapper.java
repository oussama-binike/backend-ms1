package  ma.zyn.app.rest.mapper.referentiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.mapper.referentiel.ProduitMapper;
import ma.zyn.app.bean.core.referentiel.Produit;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.referentiel.ChartePhysique;
import ma.zyn.app.ws.dto.referentiel.ChartePhysiqueDto;

@Component
public class ChartePhysiqueMapper {

    @Autowired
    private ProduitMapper produitMapper ;
    private boolean produit;

    public  ChartePhysiqueMapper() {
        initObject(true);
    }

    public ChartePhysique toItem(ChartePhysiqueDto dto) {
        if (dto == null) {
            return null;
        } else {
        ChartePhysique item = new ChartePhysique();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getMinimumSize()))
                item.setMinimumSize(dto.getMinimumSize());
            if(StringUtil.isNotEmpty(dto.getMaximumSize()))
                item.setMaximumSize(dto.getMaximumSize());
            if(StringUtil.isNotEmpty(dto.getValeur()))
                item.setValeur(dto.getValeur());
            if(this.produit && dto.getProduit()!=null)
                item.setProduit(produitMapper.toItem(dto.getProduit())) ;




        return item;
        }
    }


    public ChartePhysiqueDto toDto(ChartePhysique item) {
        if (item == null) {
            return null;
        } else {
            ChartePhysiqueDto dto = new ChartePhysiqueDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getMinimumSize()))
                dto.setMinimumSize(item.getMinimumSize());
            if(StringUtil.isNotEmpty(item.getMaximumSize()))
                dto.setMaximumSize(item.getMaximumSize());
            if(StringUtil.isNotEmpty(item.getValeur()))
                dto.setValeur(item.getValeur());
            if(this.produit && item.getProduit()!=null) {
                dto.setProduit(produitMapper.toDto(item.getProduit())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.produit = value;
    }

    public List<ChartePhysique> toItem(List<ChartePhysiqueDto> dtos) {
        List<ChartePhysique> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ChartePhysiqueDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ChartePhysiqueDto> toDto(List<ChartePhysique> items) {
        List<ChartePhysiqueDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ChartePhysique item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ChartePhysiqueDto dto, ChartePhysique t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getProduit() == null  && dto.getProduit() != null){
            t.setProduit(new Produit());
        }else if (t.getProduit() != null  && dto.getProduit() != null){
            t.setProduit(null);
            t.setProduit(new Produit());
        }
        if (dto.getProduit() != null)
        produitMapper.copy(dto.getProduit(), t.getProduit());
    }

    public List<ChartePhysique> copy(List<ChartePhysiqueDto> dtos) {
        List<ChartePhysique> result = new ArrayList<>();
        if (dtos != null) {
            for (ChartePhysiqueDto dto : dtos) {
                ChartePhysique instance = new ChartePhysique();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
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
}

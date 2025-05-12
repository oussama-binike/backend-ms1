package  ma.zyn.app.rest.mapper.referentiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.mapper.referentiel.SiteMapper;
import ma.zyn.app.bean.core.referentiel.Site;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.referentiel.Axe;
import ma.zyn.app.ws.dto.referentiel.AxeDto;

@Component
public class AxeMapper {

    @Autowired
    private SiteMapper siteMapper ;
    private boolean site;

    public  AxeMapper() {
        initObject(true);
    }

    public Axe toItem(AxeDto dto) {
        if (dto == null) {
            return null;
        } else {
        Axe item = new Axe();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getStyle()))
                item.setStyle(dto.getStyle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(this.site && dto.getSite()!=null)
                item.setSite(siteMapper.toItem(dto.getSite())) ;




        return item;
        }
    }


    public AxeDto toDto(Axe item) {
        if (item == null) {
            return null;
        } else {
            AxeDto dto = new AxeDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getStyle()))
                dto.setStyle(item.getStyle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(this.site && item.getSite()!=null) {
                dto.setSite(siteMapper.toDto(item.getSite())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.site = value;
    }

    public List<Axe> toItem(List<AxeDto> dtos) {
        List<Axe> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (AxeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<AxeDto> toDto(List<Axe> items) {
        List<AxeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Axe item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(AxeDto dto, Axe t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getSite() == null  && dto.getSite() != null){
            t.setSite(new Site());
        }else if (t.getSite() != null  && dto.getSite() != null){
            t.setSite(null);
            t.setSite(new Site());
        }
        if (dto.getSite() != null)
        siteMapper.copy(dto.getSite(), t.getSite());
    }

    public List<Axe> copy(List<AxeDto> dtos) {
        List<Axe> result = new ArrayList<>();
        if (dtos != null) {
            for (AxeDto dto : dtos) {
                Axe instance = new Axe();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public SiteMapper getSiteMapper(){
        return this.siteMapper;
    }
    public void setSiteMapper(SiteMapper siteMapper ){
        this.siteMapper = siteMapper;
    }
    public boolean  isSite(){
        return this.site;
    }
    public void  setSite(boolean site){
        this.site = site;
    }
}

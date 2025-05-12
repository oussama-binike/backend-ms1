package  ma.zyn.app.rest.mapper.referentiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.referentiel.Site;
import ma.zyn.app.ws.dto.referentiel.SiteDto;

@Component
public class SiteMapper {



    public Site toItem(SiteDto dto) {
        if (dto == null) {
            return null;
        } else {
        Site item = new Site();
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



        return item;
        }
    }


    public SiteDto toDto(Site item) {
        if (item == null) {
            return null;
        } else {
            SiteDto dto = new SiteDto();
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


        return dto;
        }
    }



    public List<Site> toItem(List<SiteDto> dtos) {
        List<Site> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (SiteDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<SiteDto> toDto(List<Site> items) {
        List<SiteDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Site item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(SiteDto dto, Site t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Site> copy(List<SiteDto> dtos) {
        List<Site> result = new ArrayList<>();
        if (dtos != null) {
            for (SiteDto dto : dtos) {
                Site instance = new Site();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}

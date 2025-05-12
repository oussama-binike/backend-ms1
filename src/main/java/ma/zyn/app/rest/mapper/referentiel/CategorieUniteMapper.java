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
import ma.zyn.app.bean.core.referentiel.CategorieUnite;
import ma.zyn.app.ws.dto.referentiel.CategorieUniteDto;

@Component
public class CategorieUniteMapper {



    public CategorieUnite toItem(CategorieUniteDto dto) {
        if (dto == null) {
            return null;
        } else {
        CategorieUnite item = new CategorieUnite();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getStyle()))
                item.setStyle(dto.getStyle());



        return item;
        }
    }


    public CategorieUniteDto toDto(CategorieUnite item) {
        if (item == null) {
            return null;
        } else {
            CategorieUniteDto dto = new CategorieUniteDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getStyle()))
                dto.setStyle(item.getStyle());


        return dto;
        }
    }



    public List<CategorieUnite> toItem(List<CategorieUniteDto> dtos) {
        List<CategorieUnite> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CategorieUniteDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CategorieUniteDto> toDto(List<CategorieUnite> items) {
        List<CategorieUniteDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (CategorieUnite item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CategorieUniteDto dto, CategorieUnite t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<CategorieUnite> copy(List<CategorieUniteDto> dtos) {
        List<CategorieUnite> result = new ArrayList<>();
        if (dtos != null) {
            for (CategorieUniteDto dto : dtos) {
                CategorieUnite instance = new CategorieUnite();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}

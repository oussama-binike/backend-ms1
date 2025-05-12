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
import ma.zyn.app.bean.core.referentiel.TypeEngin;
import ma.zyn.app.ws.dto.referentiel.TypeEnginDto;

@Component
public class TypeEnginMapper {



    public TypeEngin toItem(TypeEnginDto dto) {
        if (dto == null) {
            return null;
        } else {
        TypeEngin item = new TypeEngin();
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


    public TypeEnginDto toDto(TypeEngin item) {
        if (item == null) {
            return null;
        } else {
            TypeEnginDto dto = new TypeEnginDto();
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



    public List<TypeEngin> toItem(List<TypeEnginDto> dtos) {
        List<TypeEngin> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TypeEnginDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TypeEnginDto> toDto(List<TypeEngin> items) {
        List<TypeEnginDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (TypeEngin item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TypeEnginDto dto, TypeEngin t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<TypeEngin> copy(List<TypeEnginDto> dtos) {
        List<TypeEngin> result = new ArrayList<>();
        if (dtos != null) {
            for (TypeEnginDto dto : dtos) {
                TypeEngin instance = new TypeEngin();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}

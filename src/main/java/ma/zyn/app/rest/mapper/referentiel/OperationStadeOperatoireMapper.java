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
import ma.zyn.app.bean.core.referentiel.OperationStadeOperatoire;
import ma.zyn.app.ws.dto.referentiel.OperationStadeOperatoireDto;

@Component
public class OperationStadeOperatoireMapper {



    public OperationStadeOperatoire toItem(OperationStadeOperatoireDto dto) {
        if (dto == null) {
            return null;
        } else {
        OperationStadeOperatoire item = new OperationStadeOperatoire();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getStyle()))
                item.setStyle(dto.getStyle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());



        return item;
        }
    }


    public OperationStadeOperatoireDto toDto(OperationStadeOperatoire item) {
        if (item == null) {
            return null;
        } else {
            OperationStadeOperatoireDto dto = new OperationStadeOperatoireDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getStyle()))
                dto.setStyle(item.getStyle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());


        return dto;
        }
    }



    public List<OperationStadeOperatoire> toItem(List<OperationStadeOperatoireDto> dtos) {
        List<OperationStadeOperatoire> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (OperationStadeOperatoireDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<OperationStadeOperatoireDto> toDto(List<OperationStadeOperatoire> items) {
        List<OperationStadeOperatoireDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (OperationStadeOperatoire item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(OperationStadeOperatoireDto dto, OperationStadeOperatoire t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<OperationStadeOperatoire> copy(List<OperationStadeOperatoireDto> dtos) {
        List<OperationStadeOperatoire> result = new ArrayList<>();
        if (dtos != null) {
            for (OperationStadeOperatoireDto dto : dtos) {
                OperationStadeOperatoire instance = new OperationStadeOperatoire();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}

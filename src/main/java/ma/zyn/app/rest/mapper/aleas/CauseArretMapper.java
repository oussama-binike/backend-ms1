package  ma.zyn.app.rest.mapper.aleas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.aleas.CauseArret;
import ma.zyn.app.ws.dto.aleas.CauseArretDto;

@Component
public class CauseArretMapper {



    public CauseArret toItem(CauseArretDto dto) {
        if (dto == null) {
            return null;
        } else {
        CauseArret item = new CauseArret();
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


    public CauseArretDto toDto(CauseArret item) {
        if (item == null) {
            return null;
        } else {
            CauseArretDto dto = new CauseArretDto();
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



    public List<CauseArret> toItem(List<CauseArretDto> dtos) {
        List<CauseArret> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CauseArretDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CauseArretDto> toDto(List<CauseArret> items) {
        List<CauseArretDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (CauseArret item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CauseArretDto dto, CauseArret t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<CauseArret> copy(List<CauseArretDto> dtos) {
        List<CauseArret> result = new ArrayList<>();
        if (dtos != null) {
            for (CauseArretDto dto : dtos) {
                CauseArret instance = new CauseArret();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}

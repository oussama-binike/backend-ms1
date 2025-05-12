package  ma.zyn.app.rest.mapper.referentiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.mapper.referentiel.TrancheeMapper;
import ma.zyn.app.bean.core.referentiel.Tranchee;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.referentiel.Niveau;
import ma.zyn.app.ws.dto.referentiel.NiveauDto;

@Component
public class NiveauMapper {

    @Autowired
    private TrancheeMapper trancheeMapper ;
    private boolean tranchee;

    public  NiveauMapper() {
        initObject(true);
    }

    public Niveau toItem(NiveauDto dto) {
        if (dto == null) {
            return null;
        } else {
        Niveau item = new Niveau();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(this.tranchee && dto.getTranchee()!=null)
                item.setTranchee(trancheeMapper.toItem(dto.getTranchee())) ;




        return item;
        }
    }


    public NiveauDto toDto(Niveau item) {
        if (item == null) {
            return null;
        } else {
            NiveauDto dto = new NiveauDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(this.tranchee && item.getTranchee()!=null) {
                dto.setTranchee(trancheeMapper.toDto(item.getTranchee())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.tranchee = value;
    }

    public List<Niveau> toItem(List<NiveauDto> dtos) {
        List<Niveau> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (NiveauDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<NiveauDto> toDto(List<Niveau> items) {
        List<NiveauDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Niveau item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(NiveauDto dto, Niveau t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getTranchee() == null  && dto.getTranchee() != null){
            t.setTranchee(new Tranchee());
        }else if (t.getTranchee() != null  && dto.getTranchee() != null){
            t.setTranchee(null);
            t.setTranchee(new Tranchee());
        }
        if (dto.getTranchee() != null)
        trancheeMapper.copy(dto.getTranchee(), t.getTranchee());
    }

    public List<Niveau> copy(List<NiveauDto> dtos) {
        List<Niveau> result = new ArrayList<>();
        if (dtos != null) {
            for (NiveauDto dto : dtos) {
                Niveau instance = new Niveau();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public TrancheeMapper getTrancheeMapper(){
        return this.trancheeMapper;
    }
    public void setTrancheeMapper(TrancheeMapper trancheeMapper ){
        this.trancheeMapper = trancheeMapper;
    }
    public boolean  isTranchee(){
        return this.tranchee;
    }
    public void  setTranchee(boolean tranchee){
        this.tranchee = tranchee;
    }
}

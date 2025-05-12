package  ma.zyn.app.rest.mapper.aleas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.mapper.referentiel.StadeOperatoireMapper;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import ma.zyn.app.ws.mapper.reclamation.ActionEntrepriseMapper;
import ma.zyn.app.bean.core.reclamation.ActionEntreprise;
import ma.zyn.app.ws.mapper.aleas.CauseArretMapper;
import ma.zyn.app.bean.core.aleas.CauseArret;

import ma.zyn.app.bean.core.referentiel.StadeOperatoire;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.aleas.ArretNonPlanifie;
import ma.zyn.app.ws.dto.aleas.ArretNonPlanifieDto;

@Component
public class ArretNonPlanifieMapper {

    @Autowired
    private StadeOperatoireMapper stadeOperatoireMapper ;
    @Autowired
    private ActionEntrepriseMapper actionEntrepriseMapper ;
    @Autowired
    private CauseArretMapper causeArretMapper ;
    private boolean stadeOperatoire;
    private boolean causeArret;
    private boolean actionEntreprise;

    public  ArretNonPlanifieMapper() {
        initObject(true);
    }

    public ArretNonPlanifie toItem(ArretNonPlanifieDto dto) {
        if (dto == null) {
            return null;
        } else {
        ArretNonPlanifie item = new ArretNonPlanifie();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getCommentaire()))
                item.setCommentaire(dto.getCommentaire());
            if(StringUtil.isNotEmpty(dto.getDureeEstimee()))
                item.setDureeEstimee(dto.getDureeEstimee());
            if(StringUtil.isNotEmpty(dto.getDateArret()))
                item.setDateArret(DateUtil.stringEnToDate(dto.getDateArret()));
            if(StringUtil.isNotEmpty(dto.getDateDebut()))
                item.setDateDebut(DateUtil.stringEnToDate(dto.getDateDebut()));
            if(StringUtil.isNotEmpty(dto.getDateFin()))
                item.setDateFin(DateUtil.stringEnToDate(dto.getDateFin()));
            if(dto.getStadeOperatoire() != null && dto.getStadeOperatoire().getId() != null){
                item.setStadeOperatoire(new StadeOperatoire());
                item.getStadeOperatoire().setId(dto.getStadeOperatoire().getId());
                item.getStadeOperatoire().setLibelle(dto.getStadeOperatoire().getLibelle());
            }

            if(this.causeArret && dto.getCauseArret()!=null)
                item.setCauseArret(causeArretMapper.toItem(dto.getCauseArret())) ;

            if(this.actionEntreprise && dto.getActionEntreprise()!=null)
                item.setActionEntreprise(actionEntrepriseMapper.toItem(dto.getActionEntreprise())) ;




        return item;
        }
    }


    public ArretNonPlanifieDto toDto(ArretNonPlanifie item) {
        if (item == null) {
            return null;
        } else {
            ArretNonPlanifieDto dto = new ArretNonPlanifieDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getCommentaire()))
                dto.setCommentaire(item.getCommentaire());
            if(StringUtil.isNotEmpty(item.getDureeEstimee()))
                dto.setDureeEstimee(item.getDureeEstimee());
            if(item.getDateArret()!=null)
                dto.setDateArret(DateUtil.dateTimeToString(item.getDateArret()));
            if(item.getDateDebut()!=null)
                dto.setDateDebut(DateUtil.dateTimeToString(item.getDateDebut()));
            if(item.getDateFin()!=null)
                dto.setDateFin(DateUtil.dateTimeToString(item.getDateFin()));
            if(this.stadeOperatoire && item.getStadeOperatoire()!=null) {
                dto.setStadeOperatoire(stadeOperatoireMapper.toDto(item.getStadeOperatoire())) ;

            }
            if(this.causeArret && item.getCauseArret()!=null) {
                dto.setCauseArret(causeArretMapper.toDto(item.getCauseArret())) ;

            }
            if(this.actionEntreprise && item.getActionEntreprise()!=null) {
                dto.setActionEntreprise(actionEntrepriseMapper.toDto(item.getActionEntreprise())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.stadeOperatoire = value;
        this.causeArret = value;
        this.actionEntreprise = value;
    }

    public List<ArretNonPlanifie> toItem(List<ArretNonPlanifieDto> dtos) {
        List<ArretNonPlanifie> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ArretNonPlanifieDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ArretNonPlanifieDto> toDto(List<ArretNonPlanifie> items) {
        List<ArretNonPlanifieDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ArretNonPlanifie item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ArretNonPlanifieDto dto, ArretNonPlanifie t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getStadeOperatoire() == null  && dto.getStadeOperatoire() != null){
            t.setStadeOperatoire(new StadeOperatoire());
        }else if (t.getStadeOperatoire() != null  && dto.getStadeOperatoire() != null){
            t.setStadeOperatoire(null);
            t.setStadeOperatoire(new StadeOperatoire());
        }
        if(t.getCauseArret() == null  && dto.getCauseArret() != null){
            t.setCauseArret(new CauseArret());
        }else if (t.getCauseArret() != null  && dto.getCauseArret() != null){
            t.setCauseArret(null);
            t.setCauseArret(new CauseArret());
        }
        if(t.getActionEntreprise() == null  && dto.getActionEntreprise() != null){
            t.setActionEntreprise(new ActionEntreprise());
        }else if (t.getActionEntreprise() != null  && dto.getActionEntreprise() != null){
            t.setActionEntreprise(null);
            t.setActionEntreprise(new ActionEntreprise());
        }
        if (dto.getStadeOperatoire() != null)
        stadeOperatoireMapper.copy(dto.getStadeOperatoire(), t.getStadeOperatoire());
        if (dto.getCauseArret() != null)
        causeArretMapper.copy(dto.getCauseArret(), t.getCauseArret());
        if (dto.getActionEntreprise() != null)
        actionEntrepriseMapper.copy(dto.getActionEntreprise(), t.getActionEntreprise());
    }

    public List<ArretNonPlanifie> copy(List<ArretNonPlanifieDto> dtos) {
        List<ArretNonPlanifie> result = new ArrayList<>();
        if (dtos != null) {
            for (ArretNonPlanifieDto dto : dtos) {
                ArretNonPlanifie instance = new ArretNonPlanifie();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public StadeOperatoireMapper getStadeOperatoireMapper(){
        return this.stadeOperatoireMapper;
    }
    public void setStadeOperatoireMapper(StadeOperatoireMapper stadeOperatoireMapper ){
        this.stadeOperatoireMapper = stadeOperatoireMapper;
    }
    public ActionEntrepriseMapper getActionEntrepriseMapper(){
        return this.actionEntrepriseMapper;
    }
    public void setActionEntrepriseMapper(ActionEntrepriseMapper actionEntrepriseMapper ){
        this.actionEntrepriseMapper = actionEntrepriseMapper;
    }
    public CauseArretMapper getCauseArretMapper(){
        return this.causeArretMapper;
    }
    public void setCauseArretMapper(CauseArretMapper causeArretMapper ){
        this.causeArretMapper = causeArretMapper;
    }
    public boolean  isStadeOperatoire(){
        return this.stadeOperatoire;
    }
    public void  setStadeOperatoire(boolean stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
    }
    public boolean  isCauseArret(){
        return this.causeArret;
    }
    public void  setCauseArret(boolean causeArret){
        this.causeArret = causeArret;
    }
    public boolean  isActionEntreprise(){
        return this.actionEntreprise;
    }
    public void  setActionEntreprise(boolean actionEntreprise){
        this.actionEntreprise = actionEntreprise;
    }
}

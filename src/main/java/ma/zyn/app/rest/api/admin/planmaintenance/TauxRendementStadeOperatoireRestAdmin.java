package  ma.zyn.app.api.facade.admin.planmaintenance;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.planmaintenance.TauxRendementStadeOperatoire;
import ma.zyn.app.service.facade.admin.planmaintenance.TauxRendementStadeOperatoireAdminService;
import ma.zyn.app.ws.mapper.planmaintenance.TauxRendementStadeOperatoireMapper;
import ma.zyn.app.ws.dto.planmaintenance.TauxRendementStadeOperatoireDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/tauxRendementStadeOperatoire/")
public class TauxRendementStadeOperatoireRestAdmin {




    @Operation(summary = "Finds a list of all tauxRendementStadeOperatoires")
    @GetMapping("")
    public ResponseEntity<List<TauxRendementStadeOperatoireDto>> findAll(){
        ResponseEntity<List<TauxRendementStadeOperatoireDto>> res = null;
        List<TauxRendementStadeOperatoire> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<TauxRendementStadeOperatoireDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a tauxRendementStadeOperatoire by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TauxRendementStadeOperatoireDto> findById(@PathVariable Long id) {
        TauxRendementStadeOperatoire t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            TauxRendementStadeOperatoireDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  tauxRendementStadeOperatoire")
    @PostMapping("")
    public ResponseEntity<TauxRendementStadeOperatoireDto> save(@RequestBody TauxRendementStadeOperatoireDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            TauxRendementStadeOperatoire myT = mapper.toItem(dto);
            TauxRendementStadeOperatoire t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                TauxRendementStadeOperatoireDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  tauxRendementStadeOperatoire")
    @PutMapping("")
    public ResponseEntity<TauxRendementStadeOperatoireDto> update(@RequestBody TauxRendementStadeOperatoireDto dto){
        ResponseEntity<TauxRendementStadeOperatoireDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TauxRendementStadeOperatoire t = service.findById(dto.getId());
            mapper.copy(dto,t);
            TauxRendementStadeOperatoire updated = service.update(t);
            TauxRendementStadeOperatoireDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of tauxRendementStadeOperatoire")
    @PostMapping("multiple")
    public ResponseEntity<List<TauxRendementStadeOperatoireDto>> delete(@RequestBody List<TauxRendementStadeOperatoireDto> dtos){
        ResponseEntity<List<TauxRendementStadeOperatoireDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<TauxRendementStadeOperatoire> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified tauxRendementStadeOperatoire")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id){
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }


    @Operation(summary = "Finds a tauxRendementStadeOperatoire and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TauxRendementStadeOperatoireDto> findWithAssociatedLists(@PathVariable Long id) {
        TauxRendementStadeOperatoire loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        TauxRendementStadeOperatoireDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<TauxRendementStadeOperatoireDto> findDtos(List<TauxRendementStadeOperatoire> list){
        mapper.initObject(true);
        List<TauxRendementStadeOperatoireDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<TauxRendementStadeOperatoireDto> getDtoResponseEntity(TauxRendementStadeOperatoireDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public TauxRendementStadeOperatoireRestAdmin(TauxRendementStadeOperatoireAdminService service, TauxRendementStadeOperatoireMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final TauxRendementStadeOperatoireAdminService service;
    private final TauxRendementStadeOperatoireMapper mapper;





}

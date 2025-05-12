package  ma.zyn.app.api.facade.admin.referentiel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.referentiel.StadeOperatoireProduit;
import ma.zyn.app.service.facade.admin.referentiel.StadeOperatoireProduitAdminService;
import ma.zyn.app.ws.mapper.referentiel.StadeOperatoireProduitMapper;
import ma.zyn.app.ws.dto.referentiel.StadeOperatoireProduitDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/stadeOperatoireProduit/")
public class StadeOperatoireProduitRestAdmin {




    @Operation(summary = "Finds a list of all stadeOperatoireProduits")
    @GetMapping("")
    public ResponseEntity<List<StadeOperatoireProduitDto>> findAll(){
        ResponseEntity<List<StadeOperatoireProduitDto>> res = null;
        List<StadeOperatoireProduit> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<StadeOperatoireProduitDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a stadeOperatoireProduit by id")
    @GetMapping("id/{id}")
    public ResponseEntity<StadeOperatoireProduitDto> findById(@PathVariable Long id) {
        StadeOperatoireProduit t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            StadeOperatoireProduitDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  stadeOperatoireProduit")
    @PostMapping("")
    public ResponseEntity<StadeOperatoireProduitDto> save(@RequestBody StadeOperatoireProduitDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            StadeOperatoireProduit myT = mapper.toItem(dto);
            StadeOperatoireProduit t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                StadeOperatoireProduitDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  stadeOperatoireProduit")
    @PutMapping("")
    public ResponseEntity<StadeOperatoireProduitDto> update(@RequestBody StadeOperatoireProduitDto dto){
        ResponseEntity<StadeOperatoireProduitDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            StadeOperatoireProduit t = service.findById(dto.getId());
            mapper.copy(dto,t);
            StadeOperatoireProduit updated = service.update(t);
            StadeOperatoireProduitDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of stadeOperatoireProduit")
    @PostMapping("multiple")
    public ResponseEntity<List<StadeOperatoireProduitDto>> delete(@RequestBody List<StadeOperatoireProduitDto> dtos){
        ResponseEntity<List<StadeOperatoireProduitDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<StadeOperatoireProduit> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified stadeOperatoireProduit")
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


    @Operation(summary = "Finds a stadeOperatoireProduit and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<StadeOperatoireProduitDto> findWithAssociatedLists(@PathVariable Long id) {
        StadeOperatoireProduit loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        StadeOperatoireProduitDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<StadeOperatoireProduitDto> findDtos(List<StadeOperatoireProduit> list){
        mapper.initObject(true);
        List<StadeOperatoireProduitDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<StadeOperatoireProduitDto> getDtoResponseEntity(StadeOperatoireProduitDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public StadeOperatoireProduitRestAdmin(StadeOperatoireProduitAdminService service, StadeOperatoireProduitMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final StadeOperatoireProduitAdminService service;
    private final StadeOperatoireProduitMapper mapper;





}

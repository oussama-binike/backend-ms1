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

import ma.zyn.app.bean.core.referentiel.CoutEngin;
import ma.zyn.app.service.facade.admin.referentiel.CoutEnginAdminService;
import ma.zyn.app.ws.mapper.referentiel.CoutEnginMapper;
import ma.zyn.app.ws.dto.referentiel.CoutEnginDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/coutEngin/")
public class CoutEnginRestAdmin {




    @Operation(summary = "Finds a list of all coutEngins")
    @GetMapping("")
    public ResponseEntity<List<CoutEnginDto>> findAll(){
        ResponseEntity<List<CoutEnginDto>> res = null;
        List<CoutEngin> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<CoutEnginDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all coutEngins")
    @GetMapping("optimized")
    public ResponseEntity<List<CoutEnginDto>> findAllOptimized(){
        ResponseEntity<List<CoutEnginDto>> res = null;
        List<CoutEngin> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initObject(true);
        List<CoutEnginDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a coutEngin by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CoutEnginDto> findById(@PathVariable Long id) {
        CoutEngin t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            CoutEnginDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a coutEngin by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<CoutEnginDto> findByLibelle(@PathVariable String libelle) {
	    CoutEngin t = service.findByReferenceEntity(new CoutEngin(libelle));
        if (t != null) {
            mapper.init(true);
            CoutEnginDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  coutEngin")
    @PostMapping("")
    public ResponseEntity<CoutEnginDto> save(@RequestBody CoutEnginDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            CoutEngin myT = mapper.toItem(dto);
            CoutEngin t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                CoutEnginDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  coutEngin")
    @PutMapping("")
    public ResponseEntity<CoutEnginDto> update(@RequestBody CoutEnginDto dto){
        ResponseEntity<CoutEnginDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            CoutEngin t = service.findById(dto.getId());
            mapper.copy(dto,t);
            CoutEngin updated = service.update(t);
            CoutEnginDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of coutEngin")
    @PostMapping("multiple")
    public ResponseEntity<List<CoutEnginDto>> delete(@RequestBody List<CoutEnginDto> dtos){
        ResponseEntity<List<CoutEnginDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<CoutEngin> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified coutEngin")
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


    @Operation(summary = "Finds a coutEngin and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CoutEnginDto> findWithAssociatedLists(@PathVariable Long id) {
        CoutEngin loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        CoutEnginDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<CoutEnginDto> findDtos(List<CoutEngin> list){
        mapper.initObject(true);
        List<CoutEnginDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<CoutEnginDto> getDtoResponseEntity(CoutEnginDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public CoutEnginRestAdmin(CoutEnginAdminService service, CoutEnginMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final CoutEnginAdminService service;
    private final CoutEnginMapper mapper;





}

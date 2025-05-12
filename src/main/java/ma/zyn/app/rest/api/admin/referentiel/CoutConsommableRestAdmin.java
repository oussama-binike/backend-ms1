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

import ma.zyn.app.bean.core.referentiel.CoutConsommable;
import ma.zyn.app.service.facade.admin.referentiel.CoutConsommableAdminService;
import ma.zyn.app.ws.mapper.referentiel.CoutConsommableMapper;
import ma.zyn.app.ws.dto.referentiel.CoutConsommableDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/coutConsommable/")
public class CoutConsommableRestAdmin {




    @Operation(summary = "Finds a list of all coutConsommables")
    @GetMapping("")
    public ResponseEntity<List<CoutConsommableDto>> findAll(){
        ResponseEntity<List<CoutConsommableDto>> res = null;
        List<CoutConsommable> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<CoutConsommableDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all coutConsommables")
    @GetMapping("optimized")
    public ResponseEntity<List<CoutConsommableDto>> findAllOptimized(){
        ResponseEntity<List<CoutConsommableDto>> res = null;
        List<CoutConsommable> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initObject(true);
        List<CoutConsommableDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a coutConsommable by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CoutConsommableDto> findById(@PathVariable Long id) {
        CoutConsommable t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            CoutConsommableDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a coutConsommable by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<CoutConsommableDto> findByLibelle(@PathVariable String libelle) {
	    CoutConsommable t = service.findByReferenceEntity(new CoutConsommable(libelle));
        if (t != null) {
            mapper.init(true);
            CoutConsommableDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  coutConsommable")
    @PostMapping("")
    public ResponseEntity<CoutConsommableDto> save(@RequestBody CoutConsommableDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            CoutConsommable myT = mapper.toItem(dto);
            CoutConsommable t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                CoutConsommableDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  coutConsommable")
    @PutMapping("")
    public ResponseEntity<CoutConsommableDto> update(@RequestBody CoutConsommableDto dto){
        ResponseEntity<CoutConsommableDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            CoutConsommable t = service.findById(dto.getId());
            mapper.copy(dto,t);
            CoutConsommable updated = service.update(t);
            CoutConsommableDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of coutConsommable")
    @PostMapping("multiple")
    public ResponseEntity<List<CoutConsommableDto>> delete(@RequestBody List<CoutConsommableDto> dtos){
        ResponseEntity<List<CoutConsommableDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<CoutConsommable> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified coutConsommable")
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


    @Operation(summary = "Finds a coutConsommable and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CoutConsommableDto> findWithAssociatedLists(@PathVariable Long id) {
        CoutConsommable loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        CoutConsommableDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<CoutConsommableDto> findDtos(List<CoutConsommable> list){
        mapper.initObject(true);
        List<CoutConsommableDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<CoutConsommableDto> getDtoResponseEntity(CoutConsommableDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public CoutConsommableRestAdmin(CoutConsommableAdminService service, CoutConsommableMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final CoutConsommableAdminService service;
    private final CoutConsommableMapper mapper;





}

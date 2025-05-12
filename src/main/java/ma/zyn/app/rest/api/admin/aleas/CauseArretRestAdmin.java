package  ma.zyn.app.api.facade.admin.aleas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.aleas.CauseArret;
import ma.zyn.app.service.facade.admin.aleas.CauseArretAdminService;
import ma.zyn.app.ws.mapper.aleas.CauseArretMapper;
import ma.zyn.app.ws.dto.aleas.CauseArretDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/causeArret/")
public class CauseArretRestAdmin {




    @Operation(summary = "Finds a list of all causeArrets")
    @GetMapping("")
    public ResponseEntity<List<CauseArretDto>> findAll(){
        ResponseEntity<List<CauseArretDto>> res = null;
        List<CauseArret> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CauseArretDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all causeArrets")
    @GetMapping("optimized")
    public ResponseEntity<List<CauseArretDto>> findAllOptimized(){
        ResponseEntity<List<CauseArretDto>> res = null;
        List<CauseArret> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CauseArretDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a causeArret by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CauseArretDto> findById(@PathVariable Long id) {
        CauseArret t = service.findById(id);
        if (t != null) {
            CauseArretDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a causeArret by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<CauseArretDto> findByLibelle(@PathVariable String libelle) {
	    CauseArret t = service.findByReferenceEntity(new CauseArret(libelle));
        if (t != null) {
            CauseArretDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  causeArret")
    @PostMapping("")
    public ResponseEntity<CauseArretDto> save(@RequestBody CauseArretDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            CauseArret myT = mapper.toItem(dto);
            CauseArret t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                CauseArretDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  causeArret")
    @PutMapping("")
    public ResponseEntity<CauseArretDto> update(@RequestBody CauseArretDto dto){
        ResponseEntity<CauseArretDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            CauseArret t = service.findById(dto.getId());
            mapper.copy(dto,t);
            CauseArret updated = service.update(t);
            CauseArretDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of causeArret")
    @PostMapping("multiple")
    public ResponseEntity<List<CauseArretDto>> delete(@RequestBody List<CauseArretDto> dtos){
        ResponseEntity<List<CauseArretDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<CauseArret> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified causeArret")
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


    @Operation(summary = "Finds a causeArret and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CauseArretDto> findWithAssociatedLists(@PathVariable Long id) {
        CauseArret loaded =  service.findWithAssociatedLists(id);
        CauseArretDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<CauseArretDto> findDtos(List<CauseArret> list){
        List<CauseArretDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<CauseArretDto> getDtoResponseEntity(CauseArretDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public CauseArretRestAdmin(CauseArretAdminService service, CauseArretMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final CauseArretAdminService service;
    private final CauseArretMapper mapper;





}

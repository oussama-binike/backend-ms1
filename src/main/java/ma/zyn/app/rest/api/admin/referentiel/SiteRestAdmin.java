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

import ma.zyn.app.bean.core.referentiel.Site;
import ma.zyn.app.service.facade.admin.referentiel.SiteAdminService;
import ma.zyn.app.ws.mapper.referentiel.SiteMapper;
import ma.zyn.app.ws.dto.referentiel.SiteDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/site/")
public class SiteRestAdmin {




    @Operation(summary = "Finds a list of all sites")
    @GetMapping("")
    public ResponseEntity<List<SiteDto>> findAll(){
        ResponseEntity<List<SiteDto>> res = null;
        List<Site> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<SiteDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all sites")
    @GetMapping("optimized")
    public ResponseEntity<List<SiteDto>> findAllOptimized(){
        ResponseEntity<List<SiteDto>> res = null;
        List<Site> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<SiteDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a site by id")
    @GetMapping("id/{id}")
    public ResponseEntity<SiteDto> findById(@PathVariable Long id) {
        Site t = service.findById(id);
        if (t != null) {
            SiteDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a site by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<SiteDto> findByLibelle(@PathVariable String libelle) {
	    Site t = service.findByReferenceEntity(new Site(libelle));
        if (t != null) {
            SiteDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  site")
    @PostMapping("")
    public ResponseEntity<SiteDto> save(@RequestBody SiteDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            Site myT = mapper.toItem(dto);
            Site t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                SiteDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  site")
    @PutMapping("")
    public ResponseEntity<SiteDto> update(@RequestBody SiteDto dto){
        ResponseEntity<SiteDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Site t = service.findById(dto.getId());
            mapper.copy(dto,t);
            Site updated = service.update(t);
            SiteDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of site")
    @PostMapping("multiple")
    public ResponseEntity<List<SiteDto>> delete(@RequestBody List<SiteDto> dtos){
        ResponseEntity<List<SiteDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Site> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified site")
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


    @Operation(summary = "Finds a site and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<SiteDto> findWithAssociatedLists(@PathVariable Long id) {
        Site loaded =  service.findWithAssociatedLists(id);
        SiteDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<SiteDto> findDtos(List<Site> list){
        List<SiteDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<SiteDto> getDtoResponseEntity(SiteDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public SiteRestAdmin(SiteAdminService service, SiteMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final SiteAdminService service;
    private final SiteMapper mapper;





}

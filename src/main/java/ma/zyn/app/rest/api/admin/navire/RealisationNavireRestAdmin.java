package  ma.zyn.app.api.facade.admin.navire;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.navire.RealisationNavire;
import ma.zyn.app.service.facade.admin.navire.RealisationNavireAdminService;
import ma.zyn.app.ws.mapper.navire.RealisationNavireMapper;
import ma.zyn.app.ws.dto.navire.RealisationNavireDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/realisationNavire/")
public class RealisationNavireRestAdmin {




    @Operation(summary = "Finds a list of all realisationNavires")
    @GetMapping("")
    public ResponseEntity<List<RealisationNavireDto>> findAll(){
        ResponseEntity<List<RealisationNavireDto>> res = null;
        List<RealisationNavire> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initList(false);
            mapper.initObject(true);
        List<RealisationNavireDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all realisationNavires")
    @GetMapping("optimized")
    public ResponseEntity<List<RealisationNavireDto>> findAllOptimized(){
        ResponseEntity<List<RealisationNavireDto>> res = null;
        List<RealisationNavire> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initList(false);
        mapper.initObject(true);
        List<RealisationNavireDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a realisationNavire by id")
    @GetMapping("id/{id}")
    public ResponseEntity<RealisationNavireDto> findById(@PathVariable Long id) {
        RealisationNavire t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            RealisationNavireDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a realisationNavire by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<RealisationNavireDto> findByLibelle(@PathVariable String libelle) {
	    RealisationNavire t = service.findByReferenceEntity(new RealisationNavire(libelle));
        if (t != null) {
            mapper.init(true);
            RealisationNavireDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  realisationNavire")
    @PostMapping("")
    public ResponseEntity<RealisationNavireDto> save(@RequestBody RealisationNavireDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            RealisationNavire myT = mapper.toItem(dto);
            RealisationNavire t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                RealisationNavireDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  realisationNavire")
    @PutMapping("")
    public ResponseEntity<RealisationNavireDto> update(@RequestBody RealisationNavireDto dto){
        ResponseEntity<RealisationNavireDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            RealisationNavire t = service.findById(dto.getId());
            mapper.copy(dto,t);
            RealisationNavire updated = service.update(t);
            RealisationNavireDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of realisationNavire")
    @PostMapping("multiple")
    public ResponseEntity<List<RealisationNavireDto>> delete(@RequestBody List<RealisationNavireDto> dtos){
        ResponseEntity<List<RealisationNavireDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<RealisationNavire> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified realisationNavire")
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


    @Operation(summary = "Finds a realisationNavire and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<RealisationNavireDto> findWithAssociatedLists(@PathVariable Long id) {
        RealisationNavire loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        RealisationNavireDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<RealisationNavireDto> findDtos(List<RealisationNavire> list){
        mapper.initList(false);
        mapper.initObject(true);
        List<RealisationNavireDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<RealisationNavireDto> getDtoResponseEntity(RealisationNavireDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public RealisationNavireRestAdmin(RealisationNavireAdminService service, RealisationNavireMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final RealisationNavireAdminService service;
    private final RealisationNavireMapper mapper;





}

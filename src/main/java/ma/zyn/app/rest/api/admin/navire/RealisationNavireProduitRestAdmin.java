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

import ma.zyn.app.bean.core.navire.RealisationNavireProduit;
import ma.zyn.app.service.facade.admin.navire.RealisationNavireProduitAdminService;
import ma.zyn.app.ws.mapper.navire.RealisationNavireProduitMapper;
import ma.zyn.app.ws.dto.navire.RealisationNavireProduitDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/realisationNavireProduit/")
public class RealisationNavireProduitRestAdmin {




    @Operation(summary = "Finds a list of all realisationNavireProduits")
    @GetMapping("")
    public ResponseEntity<List<RealisationNavireProduitDto>> findAll(){
        ResponseEntity<List<RealisationNavireProduitDto>> res = null;
        List<RealisationNavireProduit> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<RealisationNavireProduitDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a realisationNavireProduit by id")
    @GetMapping("id/{id}")
    public ResponseEntity<RealisationNavireProduitDto> findById(@PathVariable Long id) {
        RealisationNavireProduit t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            RealisationNavireProduitDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  realisationNavireProduit")
    @PostMapping("")
    public ResponseEntity<RealisationNavireProduitDto> save(@RequestBody RealisationNavireProduitDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            RealisationNavireProduit myT = mapper.toItem(dto);
            RealisationNavireProduit t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                RealisationNavireProduitDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  realisationNavireProduit")
    @PutMapping("")
    public ResponseEntity<RealisationNavireProduitDto> update(@RequestBody RealisationNavireProduitDto dto){
        ResponseEntity<RealisationNavireProduitDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            RealisationNavireProduit t = service.findById(dto.getId());
            mapper.copy(dto,t);
            RealisationNavireProduit updated = service.update(t);
            RealisationNavireProduitDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of realisationNavireProduit")
    @PostMapping("multiple")
    public ResponseEntity<List<RealisationNavireProduitDto>> delete(@RequestBody List<RealisationNavireProduitDto> dtos){
        ResponseEntity<List<RealisationNavireProduitDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<RealisationNavireProduit> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified realisationNavireProduit")
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

    @Operation(summary = "find by realisationNavire id")
    @GetMapping("realisationNavire/id/{id}")
    public List<RealisationNavireProduitDto> findByRealisationNavireId(@PathVariable Long id){
        return findDtos(service.findByRealisationNavireId(id));
    }
    @Operation(summary = "delete by realisationNavire id")
    @DeleteMapping("realisationNavire/id/{id}")
    public int deleteByRealisationNavireId(@PathVariable Long id){
        return service.deleteByRealisationNavireId(id);
    }

    @Operation(summary = "Finds a realisationNavireProduit and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<RealisationNavireProduitDto> findWithAssociatedLists(@PathVariable Long id) {
        RealisationNavireProduit loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        RealisationNavireProduitDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<RealisationNavireProduitDto> findDtos(List<RealisationNavireProduit> list){
        mapper.initObject(true);
        List<RealisationNavireProduitDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<RealisationNavireProduitDto> getDtoResponseEntity(RealisationNavireProduitDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public RealisationNavireProduitRestAdmin(RealisationNavireProduitAdminService service, RealisationNavireProduitMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final RealisationNavireProduitAdminService service;
    private final RealisationNavireProduitMapper mapper;





}

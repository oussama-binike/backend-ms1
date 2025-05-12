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

import ma.zyn.app.bean.core.navire.RealisationNavireQualite;
import ma.zyn.app.service.facade.admin.navire.RealisationNavireQualiteAdminService;
import ma.zyn.app.ws.mapper.navire.RealisationNavireQualiteMapper;
import ma.zyn.app.ws.dto.navire.RealisationNavireQualiteDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/realisationNavireQualite/")
public class RealisationNavireQualiteRestAdmin {




    @Operation(summary = "Finds a list of all realisationNavireQualites")
    @GetMapping("")
    public ResponseEntity<List<RealisationNavireQualiteDto>> findAll(){
        ResponseEntity<List<RealisationNavireQualiteDto>> res = null;
        List<RealisationNavireQualite> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<RealisationNavireQualiteDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a realisationNavireQualite by id")
    @GetMapping("id/{id}")
    public ResponseEntity<RealisationNavireQualiteDto> findById(@PathVariable Long id) {
        RealisationNavireQualite t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            RealisationNavireQualiteDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  realisationNavireQualite")
    @PostMapping("")
    public ResponseEntity<RealisationNavireQualiteDto> save(@RequestBody RealisationNavireQualiteDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            RealisationNavireQualite myT = mapper.toItem(dto);
            RealisationNavireQualite t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                RealisationNavireQualiteDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  realisationNavireQualite")
    @PutMapping("")
    public ResponseEntity<RealisationNavireQualiteDto> update(@RequestBody RealisationNavireQualiteDto dto){
        ResponseEntity<RealisationNavireQualiteDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            RealisationNavireQualite t = service.findById(dto.getId());
            mapper.copy(dto,t);
            RealisationNavireQualite updated = service.update(t);
            RealisationNavireQualiteDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of realisationNavireQualite")
    @PostMapping("multiple")
    public ResponseEntity<List<RealisationNavireQualiteDto>> delete(@RequestBody List<RealisationNavireQualiteDto> dtos){
        ResponseEntity<List<RealisationNavireQualiteDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<RealisationNavireQualite> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified realisationNavireQualite")
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

    @Operation(summary = "find by produitMarchand code")
    @GetMapping("produitMarchand/code/{code}")
    public List<RealisationNavireQualiteDto> findByProduitMarchandCode(@PathVariable String code){
        return findDtos(service.findByProduitMarchandCode(code));
    }
    @Operation(summary = "delete by produitMarchand code")
    @DeleteMapping("produitMarchand/code/{code}")
    public int deleteByProduitMarchandCode(@PathVariable String code){
        return service.deleteByProduitMarchandCode(code);
    }
    @Operation(summary = "find by realisationNavire id")
    @GetMapping("realisationNavire/id/{id}")
    public List<RealisationNavireQualiteDto> findByRealisationNavireId(@PathVariable Long id){
        return findDtos(service.findByRealisationNavireId(id));
    }
    @Operation(summary = "delete by realisationNavire id")
    @DeleteMapping("realisationNavire/id/{id}")
    public int deleteByRealisationNavireId(@PathVariable Long id){
        return service.deleteByRealisationNavireId(id);
    }

    @Operation(summary = "Finds a realisationNavireQualite and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<RealisationNavireQualiteDto> findWithAssociatedLists(@PathVariable Long id) {
        RealisationNavireQualite loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        RealisationNavireQualiteDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<RealisationNavireQualiteDto> findDtos(List<RealisationNavireQualite> list){
        mapper.initObject(true);
        List<RealisationNavireQualiteDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<RealisationNavireQualiteDto> getDtoResponseEntity(RealisationNavireQualiteDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public RealisationNavireQualiteRestAdmin(RealisationNavireQualiteAdminService service, RealisationNavireQualiteMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final RealisationNavireQualiteAdminService service;
    private final RealisationNavireQualiteMapper mapper;





}

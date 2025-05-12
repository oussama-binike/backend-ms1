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

import ma.zyn.app.bean.core.referentiel.Moyen;
import ma.zyn.app.service.facade.admin.referentiel.MoyenAdminService;
import ma.zyn.app.ws.mapper.referentiel.MoyenMapper;
import ma.zyn.app.ws.dto.referentiel.MoyenDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/moyen/")
public class MoyenRestAdmin {




    @Operation(summary = "Finds a list of all moyens")
    @GetMapping("")
    public ResponseEntity<List<MoyenDto>> findAll(){
        ResponseEntity<List<MoyenDto>> res = null;
        List<Moyen> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<MoyenDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all moyens")
    @GetMapping("optimized")
    public ResponseEntity<List<MoyenDto>> findAllOptimized(){
        ResponseEntity<List<MoyenDto>> res = null;
        List<Moyen> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<MoyenDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a moyen by id")
    @GetMapping("id/{id}")
    public ResponseEntity<MoyenDto> findById(@PathVariable Long id) {
        Moyen t = service.findById(id);
        if (t != null) {
            MoyenDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a moyen by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<MoyenDto> findByLibelle(@PathVariable String libelle) {
	    Moyen t = service.findByReferenceEntity(new Moyen(libelle));
        if (t != null) {
            MoyenDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  moyen")
    @PostMapping("")
    public ResponseEntity<MoyenDto> save(@RequestBody MoyenDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            Moyen myT = mapper.toItem(dto);
            Moyen t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                MoyenDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  moyen")
    @PutMapping("")
    public ResponseEntity<MoyenDto> update(@RequestBody MoyenDto dto){
        ResponseEntity<MoyenDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Moyen t = service.findById(dto.getId());
            mapper.copy(dto,t);
            Moyen updated = service.update(t);
            MoyenDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of moyen")
    @PostMapping("multiple")
    public ResponseEntity<List<MoyenDto>> delete(@RequestBody List<MoyenDto> dtos){
        ResponseEntity<List<MoyenDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Moyen> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified moyen")
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


    @Operation(summary = "Finds a moyen and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<MoyenDto> findWithAssociatedLists(@PathVariable Long id) {
        Moyen loaded =  service.findWithAssociatedLists(id);
        MoyenDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<MoyenDto> findDtos(List<Moyen> list){
        List<MoyenDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<MoyenDto> getDtoResponseEntity(MoyenDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public MoyenRestAdmin(MoyenAdminService service, MoyenMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final MoyenAdminService service;
    private final MoyenMapper mapper;





}

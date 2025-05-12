package  ma.zyn.app.api.facade.admin.reclamation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.reclamation.ActionEntreprise;
import ma.zyn.app.service.facade.admin.reclamation.ActionEntrepriseAdminService;
import ma.zyn.app.ws.mapper.reclamation.ActionEntrepriseMapper;
import ma.zyn.app.ws.dto.reclamation.ActionEntrepriseDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/actionEntreprise/")
public class ActionEntrepriseRestAdmin {




    @Operation(summary = "Finds a list of all actionEntreprises")
    @GetMapping("")
    public ResponseEntity<List<ActionEntrepriseDto>> findAll(){
        ResponseEntity<List<ActionEntrepriseDto>> res = null;
        List<ActionEntreprise> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ActionEntrepriseDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all actionEntreprises")
    @GetMapping("optimized")
    public ResponseEntity<List<ActionEntrepriseDto>> findAllOptimized(){
        ResponseEntity<List<ActionEntrepriseDto>> res = null;
        List<ActionEntreprise> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ActionEntrepriseDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a actionEntreprise by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ActionEntrepriseDto> findById(@PathVariable Long id) {
        ActionEntreprise t = service.findById(id);
        if (t != null) {
            ActionEntrepriseDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a actionEntreprise by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ActionEntrepriseDto> findByLibelle(@PathVariable String libelle) {
	    ActionEntreprise t = service.findByReferenceEntity(new ActionEntreprise(libelle));
        if (t != null) {
            ActionEntrepriseDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  actionEntreprise")
    @PostMapping("")
    public ResponseEntity<ActionEntrepriseDto> save(@RequestBody ActionEntrepriseDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            ActionEntreprise myT = mapper.toItem(dto);
            ActionEntreprise t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                ActionEntrepriseDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  actionEntreprise")
    @PutMapping("")
    public ResponseEntity<ActionEntrepriseDto> update(@RequestBody ActionEntrepriseDto dto){
        ResponseEntity<ActionEntrepriseDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ActionEntreprise t = service.findById(dto.getId());
            mapper.copy(dto,t);
            ActionEntreprise updated = service.update(t);
            ActionEntrepriseDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of actionEntreprise")
    @PostMapping("multiple")
    public ResponseEntity<List<ActionEntrepriseDto>> delete(@RequestBody List<ActionEntrepriseDto> dtos){
        ResponseEntity<List<ActionEntrepriseDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<ActionEntreprise> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified actionEntreprise")
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


    @Operation(summary = "Finds a actionEntreprise and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ActionEntrepriseDto> findWithAssociatedLists(@PathVariable Long id) {
        ActionEntreprise loaded =  service.findWithAssociatedLists(id);
        ActionEntrepriseDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<ActionEntrepriseDto> findDtos(List<ActionEntreprise> list){
        List<ActionEntrepriseDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<ActionEntrepriseDto> getDtoResponseEntity(ActionEntrepriseDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public ActionEntrepriseRestAdmin(ActionEntrepriseAdminService service, ActionEntrepriseMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final ActionEntrepriseAdminService service;
    private final ActionEntrepriseMapper mapper;





}

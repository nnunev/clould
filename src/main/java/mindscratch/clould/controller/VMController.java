package mindscratch.clould.controller;

import jakarta.validation.Valid;
import mindscratch.clould.dto.VMRequest;
import mindscratch.clould.dto.VMResponse;
import mindscratch.clould.service.VMService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping({"/vm", "/vm/"})
public class VMController {

    private final VMService service;

    public VMController(VMService service) {
        this.service = service;
    }
    
    @Operation(summary = "Get all virtual machines")
    @GetMapping
    public List<VMResponse> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Get virtual machine by ID")
    @GetMapping("/{id}")
    public VMResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @Operation(summary = "Create a new virtual machine")
    @PostMapping
    public VMResponse create(@Valid @RequestBody VMRequest request) {
        return service.create(request);
    }

    @Operation(summary = "Update a virtual machine")
    @PutMapping("/{id}")
    public VMResponse update(
            @PathVariable Long id,
            @Valid @RequestBody VMRequest request) {

        return service.update(id, request);
    }

    @Operation(summary = "Delete a virtual machine")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

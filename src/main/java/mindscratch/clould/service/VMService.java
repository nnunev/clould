package mindscratch.clould.service;

import mindscratch.clould.dto.VMRequest;
import mindscratch.clould.dto.VMResponse;
import mindscratch.clould.entity.VirtualMachine;
import mindscratch.clould.exception.VMNotFoundException;
import mindscratch.clould.repository.VMRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
// import org.springframework.cache.annotation.CacheEvict;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class VMService {
    @PersistenceContext
    private EntityManager em;
    private final VMRepository repository;

    public VMService(VMRepository repository) {
        this.repository = repository;
    }

    @Cacheable("vms")
    
    public List<VMResponse> getAll() {
        List<VirtualMachine> vms = repository.findAll();
        System.out.println("Loading from PostgreSQL...");
        System.out.println("================================");
        System.out.println("VM count = " + vms.size());
            for (VirtualMachine vm : vms) {
                System.out.println(
                    vm.getId() + " | " +
                    vm.getName() + " | " +
                    vm.getStatus()
                );
            }

            System.out.println("================================");

        return vms.stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
        
        // return repository.findAll()
        //         .stream()
        //         .map(this::mapToResponse)
        //         .collect(Collectors.toList());
    }

    public VMResponse create(VMRequest request) {

        VirtualMachine vm = new VirtualMachine();

        vm.setName(request.getName());
        vm.setStatus(request.getStatus());
        vm.setCpu(request.getCpu());
        vm.setRam(request.getRam());

        VirtualMachine saved = repository.save(vm);
        System.out.println("Saved VM id = " + saved.getId());
        return mapToResponse(saved);
    }

    private VMResponse mapToResponse(VirtualMachine vm) {

        VMResponse response = new VMResponse();

        response.setId(vm.getId());
        response.setName(vm.getName());
        response.setStatus(vm.getStatus());
        response.setCpu(vm.getCpu());
        response.setRam(vm.getRam());

        return response;
    }

    public VMResponse getById(Long id) {

        VirtualMachine vm = repository.findById(id)
                .orElseThrow(() -> new VMNotFoundException(id));

        return mapToResponse(vm);
    }
    
    public VMResponse update(Long id, VMRequest request) {

        VirtualMachine vm = repository.findById(id)
                .orElseThrow(() -> new VMNotFoundException(id));

        vm.setName(request.getName());
        vm.setStatus(request.getStatus());
        vm.setCpu(request.getCpu());
        vm.setRam(request.getRam());

        return mapToResponse(repository.save(vm));
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }
    
}

///////////////////////////////////////////

// package mindscratch.clould.service;

// import mindscratch.clould.entity.VirtualMachine;
// import mindscratch.clould.repository.VMRepository;
// import org.springframework.cache.annotation.Cacheable;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class VMService {

//     private final VMRepository repository;

//     public VMService(VMRepository repository) {
//         this.repository = repository;
//     }

//     @Cacheable("vms")
//     public List<VirtualMachine> getAll() {

//         System.out.println("Loading from PostgreSQL...");

//         return repository.findAll();
//     }

//     public VirtualMachine create(VirtualMachine vm) {
//         return repository.save(vm);
//     }
// }

// // package mindscratch.clould.service;

// // public class VMService {
    
// // }

package mindscratch.clould.dto;

import java.io.Serializable;

public class VMResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Integer cpu;
    private Integer ram;
    private String status;

    public VMResponse() {
    }

    public VMResponse(Long id, String name, Integer cpu, Integer ram, String status) {
        this.id = id;
        this.name = name;
        this.cpu = cpu;
        this.ram = ram;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCpu() {
        return cpu;
    }

    public void setCpu(Integer cpu) {
        this.cpu = cpu;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


// package mindscratch.clould.dto;
// import java.io.Serializable;

// public class VMResponse implements Serializable {
//     private Long id;
//     private String name;
//     private String status;
//     private Integer cpu;
//     private Integer ram;
    
//     // getters/setters
//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public String getStatus() {
//         return status;
//     }

//     public void setStatus(String status) {
//         this.status = status;
//     }

//     public Integer getCpu() {
//         return cpu;
//     }

//     public void setCpu(Integer cpu) {
//         this.cpu = cpu;
//     }

//     public Integer getRam() {
//         return ram;
//     }

//     public void setRam(Integer ram) {
//         this.ram = ram;
//     }
    
// }

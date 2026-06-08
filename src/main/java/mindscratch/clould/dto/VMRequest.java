package mindscratch.clould.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VMRequest {

    @NotBlank(message = "VM name is required")
    private String name;

    @NotBlank(message = "VM status is required")
    private String status;

    @NotNull(message = "CPU is required")
    @Min(value = 1, message = "CPU must be at least 1")
    private Integer cpu;

    @NotNull(message = "RAM is required")
    @Min(value = 1, message = "RAM must be at least 1 GB")
    private Integer ram;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}

// package mindscratch.clould.dto;

// public class VMRequest {

//     private String name;

//     private String status;

//     private Integer cpu;

//     private Integer ram;

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

// // package mindscratch.clould.dto;

// // public class VMRequest {
    
// // }

package com.alejandrorea.netadmin.dtos.laptop;

import com.alejandrorea.netadmin.models.Estado;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class DetalleLaptopDTO extends LaptopRequest{
    private Long id;
    private Integer posicionEnOrden;
    private Estado estado;

}